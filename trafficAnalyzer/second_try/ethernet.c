// #include "ethernet.h"
#include<pcap.h>
#include<stdio.h>
#include<stdlib.h> // for exit()
#include<string.h> //for memset
#include <unistd.h>
#include <time.h>

#include<sys/socket.h>
#include<arpa/inet.h> // for inet_ntoa()
#include<net/ethernet.h>
#include<netinet/ip_icmp.h>	//Provides declarations for icmp header
#include<netinet/udp.h>	//Provides declarations for udp header
#include<netinet/tcp.h>	//Provides declarations for tcp header
#include<netinet/ip.h>	//Provides declarations for ip header
/* Заголовки Ethernet всегда состоят из 14 байтов */
#define SIZE_ETHERNET 14

FILE *logfile;
int tcp=0,udp=0,others=0,icmp=0,igmp=0,total=0,broadcast=0;
u_int all_pocket_length=0;
u_int all_time=10;
time_t start_time;
time_t current_time;
double interval;


void print_data (unsigned char* data , int Size,FILE *logfile)
{
	int i , j;
	for(i=0 ; i < Size ; i++)
	{
		if( i!=0 && i%16==0)   //if one line of hex printing is complete...
		{
			fprintf(logfile , "         ");
			for(j=i-16 ; j<i ; j++)
			{
				if(data[j]>=32 && data[j]<=128)
					fprintf(logfile , "%c",(unsigned char)data[j]); //if its a number or alphabet
				
				else fprintf(logfile , "."); //otherwise print a dot
			}
			fprintf(logfile , "\n");
		} 
		
		if(i%16==0) 
			fprintf(logfile , "   ");

		fprintf(logfile , " %02X",(unsigned int)data[i]);
		if( i==Size-1)  //print the last spaces
		{
			for(j=0;j<15-i%16;j++) 
			{
			  fprintf(logfile , "   "); //extra spaces
			}
			
			fprintf(logfile , "         ");
			
			for(j=i-i%16 ; j<=i ; j++)
			{
				if(data[j]>=32 && data[j]<=128) 
				{
				  fprintf(logfile , "%c",(unsigned char)data[j]);
				}
				else 
				{
				  fprintf(logfile , ".");
				}
			}
			
			fprintf(logfile ,  "\n" );
		}
	}
}

void print_ethernet_header(const struct ethhdr *eth,FILE *file)
{	
	u_char eth_des[200];
	fprintf(file , "Ethernet Header\n");
	sprintf(eth_des , "%.2X-%.2X-%.2X-%.2X-%.2X-%.2X", 
                            eth->h_dest[0] ,
                            eth->h_dest[1] , 
                            eth->h_dest[2] , 
                            eth->h_dest[3] , 
                            eth->h_dest[4] , 
                            eth->h_dest[5] );
	fprintf(file , "   |-Destination Address : %s \n",eth_des);
	fprintf(file , "   |-Source Address      : %.2X-%.2X-%.2X-%.2X-%.2X-%.2X \n", 
                            eth->h_source[0] , 
                            eth->h_source[1] , 
                            eth->h_source[2] , 
                            eth->h_source[3] , 
                            eth->h_source[4] , 
                            eth->h_source[5] );
	fprintf(file , "   |-Protocol            : %u \n",(unsigned short)eth->h_proto);
	if(strcmp(eth_des,"FF-FF-FF-FF-FF-FF")==0)broadcast++;
}
void print_ip_header(const struct iphdr *ip,FILE *file){
	struct sockaddr_in source,dest;
	source.sin_addr.s_addr=ip->saddr;
	dest.sin_addr.s_addr=ip->daddr;
	fprintf(file , "IP Header\n");
    fprintf(file , "   |-Source Address : %s\n",inet_ntoa(source.sin_addr));
    fprintf(file , "   |-Destination Address : %s\n",inet_ntoa(dest.sin_addr));
    fprintf(file , "   |-Time to live : %u\n",ip->ttl);
	fprintf(file,  "   |-Protocol: %u\n",ip->protocol);
}

void print_tcp_packet(const u_char * Buffer,int size,FILE *logfile)
{
	struct tcphdr *tcph= (struct tcphdr*)Buffer;
	int header_size =   tcph->doff*4;
	
	fprintf(logfile , "TCP Header\n");
	fprintf(logfile , "   |-Source Port      : %u\n",ntohs(tcph->source));
	fprintf(logfile , "   |-Destination Port : %u\n",ntohs(tcph->dest));
	fprintf(logfile , "   |-Header Length      : %d DWORDS or %d BYTES\n" ,(unsigned int)tcph->doff,(unsigned int)tcph->doff*4);
	fprintf(logfile , "\n");
	fprintf(logfile , "Data Payload\n");	
	print_data(Buffer + header_size , size-header_size,logfile);
	fprintf(logfile,"\n");
}

void print_udp_packet(const u_char *Buffer , int size,FILE *logfile)
{	
	struct udphdr *udph = (struct udphdr*)Buffer;
	int header_size =  sizeof(udph);
	
	fprintf(logfile , "\nUDP Header\n");
	fprintf(logfile , "   |-Source Port      : %d\n" , ntohs(udph->source));
	fprintf(logfile , "   |-Destination Port : %d\n" , ntohs(udph->dest));
	fprintf(logfile , "   |-UDP Length       : %d\n" , ntohs(udph->len));
	fprintf(logfile , "\n");
	fprintf(logfile , "Data Payload\n");	
	print_data(Buffer + header_size , size - header_size,logfile);
	fprintf(logfile,"\n");
}
void print_stat(FILE *outFile,double time){
	double min=1.0*time/60;
	fprintf(outFile,"Time left: %f\n",time);
	fprintf(outFile,"Middle pocket length: %f\n",1.0*all_pocket_length/total);
	fprintf(outFile,"Pocket count in min: %f\n",1.0*total/min);
	
}
void got_packet(u_char *args, const struct pcap_pkthdr *header, const u_char *packet)
{
	int size=header->len;
	all_pocket_length+=size;

    FILE* outFile=fopen("stat.pcap","a");

    fprintf(outFile,"\n//--------------------------Packet %d--------------------------\n",++total);
    const struct ethhdr *ethernet; /* Заголовок Ethernet */
    const struct iphdr *ip;        /* Заголовок IP */

    ethernet = (struct ethhdr *)(packet);
    ip = (struct iphdr *)(packet + sizeof(struct ethhdr));
	unsigned short iphdrlen=ip->ihl * 4;
	
	fprintf(outFile,"Packet size: %d\n",size);
	print_ethernet_header(ethernet,outFile);
	print_ip_header(ip,outFile);

	const u_char* protocol_start=(u_char*)(ip+iphdrlen);

	switch (ip->protocol) //Check the Protocol and do accordingly...
	{
		case 6:  //TCP Protocol
			++tcp;
			print_tcp_packet(protocol_start,size-sizeof(struct ethhdr)-iphdrlen,outFile);
			break;
		
		case 17: //UDP Protocol
			++udp;
			print_udp_packet(protocol_start , size-sizeof(struct ethhdr)-iphdrlen,outFile);
			break;
		
		default: //Some Other Protocol like ARP etc.
			++others;
			break;
	}
	fprintf(outFile,"\nTCP : %d   UDP : %d   Others : %d   Total : %d\r\n", tcp , udp , others , total);
	fprintf(outFile,"\nBroadCast : %d\n", broadcast);

	double diff_time=difftime(current_time,start_time);
	if(diff_time>=1.0) print_stat(outFile,diff_time);
	time(&current_time);

	fclose(outFile);	
}
