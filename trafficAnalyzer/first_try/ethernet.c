#include "ethernet.h"
/* Заголовки Ethernet всегда состоят из 14 байтов */
#define SIZE_ETHERNET 14

FILE *logfile;
int tcp=0,udp=0,others=0,total=0;

void print_data(const u_char *buffer,int size){

}

void print_ethernet_header(const struct sniff_ethernet *eth,FILE *file)
{	
	fprintf(file , "Ethernet Header\n");
	fprintf(file , "   |-Destination Address : %.2X-%.2X-%.2X-%.2X-%.2X-%.2X \n", 
                            eth->ether_dhost[0] ,
                            eth->ether_dhost[1] , 
                            eth->ether_dhost[2] , 
                            eth->ether_dhost[3] , 
                            eth->ether_dhost[4] , 
                            eth->ether_dhost[5] );
	fprintf(file , "   |-Source Address      : %.2X-%.2X-%.2X-%.2X-%.2X-%.2X \n", 
                            eth->ether_shost[0] , 
                            eth->ether_shost[1] , 
                            eth->ether_shost[2] , 
                            eth->ether_shost[3] , 
                            eth->ether_shost[4] , 
                            eth->ether_shost[5] );
	fprintf(file , "   |-Protocol            : %u \n",(unsigned short)eth->ether_type);
}
void print_ip_header(const struct sniff_ip *ip,FILE *file){
	fprintf(file , "IP Header\n");
    fprintf(file , "   |-Source Address : %s\n",inet_ntoa(ip->ip_src));
    fprintf(file , "   |-Destination Address : %s\n",inet_ntoa(ip->ip_dst));
    fprintf(file , "   |-Time to live : %u\n",ip->ip_ttl);

    
}
void print_tcp_packet(const u_char * Buffer, int Size)
{
	unsigned short iphdrlen;
	
	struct iphdr *iph = (struct iphdr *)( Buffer  + sizeof(struct ethhdr) );
	iphdrlen = iph->ihl*4;
	
	struct tcphdr *tcph=(struct tcphdr*)(Buffer + iphdrlen + sizeof(struct ethhdr));
			
	int header_size =  sizeof(struct ethhdr) + iphdrlen + tcph->doff*4;
	
	fprintf(logfile , "\n\n***********************TCP Packet*************************\n");	
		
	print_ip_header(Buffer,Size);
		
	fprintf(logfile , "\n");
	fprintf(logfile , "TCP Header\n");
	fprintf(logfile , "   |-Source Port      : %u\n",ntohs(tcph->source));
	fprintf(logfile , "   |-Destination Port : %u\n",ntohs(tcph->dest));
	fprintf(logfile , "   |-Sequence Number    : %u\n",ntohl(tcph->seq));
	fprintf(logfile , "   |-Acknowledge Number : %u\n",ntohl(tcph->ack_seq));
	fprintf(logfile , "   |-Header Length      : %d DWORDS or %d BYTES\n" ,(unsigned int)tcph->doff,(unsigned int)tcph->doff*4);
	fprintf(logfile , "   |-Urgent Flag          : %d\n",(unsigned int)tcph->urg);
	fprintf(logfile , "   |-Acknowledgement Flag : %d\n",(unsigned int)tcph->ack);
	fprintf(logfile , "   |-Push Flag            : %d\n",(unsigned int)tcph->psh);
	fprintf(logfile , "   |-Reset Flag           : %d\n",(unsigned int)tcph->rst);
	fprintf(logfile , "   |-Synchronise Flag     : %d\n",(unsigned int)tcph->syn);
	fprintf(logfile , "   |-Finish Flag          : %d\n",(unsigned int)tcph->fin);
	fprintf(logfile , "   |-Window         : %d\n",ntohs(tcph->window));
	fprintf(logfile , "   |-Checksum       : %d\n",ntohs(tcph->check));
	fprintf(logfile , "   |-Urgent Pointer : %d\n",tcph->urg_ptr);
	fprintf(logfile , "\n");
	fprintf(logfile , "                        DATA Dump                         ");
	fprintf(logfile , "\n");
		
	fprintf(logfile , "IP Header\n");
	PrintData(Buffer,iphdrlen);
		
	fprintf(logfile , "TCP Header\n");
	PrintData(Buffer+iphdrlen,tcph->doff*4);
		
	fprintf(logfile , "Data Payload\n");	
	PrintData(Buffer + header_size , Size - header_size );
						
	fprintf(logfile , "\n###########################################################");
}

void print_udp_packet(const u_char *Buffer , int Size)
{
	
	unsigned short iphdrlen;
	
	struct iphdr *iph = (struct iphdr *)(Buffer +  sizeof(struct ethhdr));
	iphdrlen = iph->ihl*4;
	
	struct udphdr *udph = (struct udphdr*)(Buffer + iphdrlen  + sizeof(struct ethhdr));
	
	int header_size =  sizeof(struct ethhdr) + iphdrlen + sizeof udph;
	
	fprintf(logfile , "\n\n***********************UDP Packet*************************\n");
	
	print_ip_header(Buffer,Size);			
	
	fprintf(logfile , "\nUDP Header\n");
	fprintf(logfile , "   |-Source Port      : %d\n" , ntohs(udph->source));
	fprintf(logfile , "   |-Destination Port : %d\n" , ntohs(udph->dest));
	fprintf(logfile , "   |-UDP Length       : %d\n" , ntohs(udph->len));
	fprintf(logfile , "   |-UDP Checksum     : %d\n" , ntohs(udph->check));
	
	fprintf(logfile , "\n");
	fprintf(logfile , "IP Header\n");
	PrintData(Buffer , iphdrlen);
		
	fprintf(logfile , "UDP Header\n");
	PrintData(Buffer+iphdrlen , sizeof udph);
		
	fprintf(logfile , "Data Payload\n");	
	
	//Move the pointer ahead and reduce the size of string
	PrintData(Buffer + header_size , Size - header_size);
	
	fprintf(logfile , "\n###########################################################");
}

void print_icmp_packet(const u_char * Buffer , int Size)
{
	unsigned short iphdrlen;
	
	struct iphdr *iph = (struct iphdr *)(Buffer  + sizeof(struct ethhdr));
	iphdrlen = iph->ihl * 4;
	
	struct icmphdr *icmph = (struct icmphdr *)(Buffer + iphdrlen  + sizeof(struct ethhdr));
	
	int header_size =  sizeof(struct ethhdr) + iphdrlen + sizeof icmph;
	
	fprintf(logfile , "\n\n***********************ICMP Packet*************************\n");	
	
	print_ip_header(Buffer , Size);
			
	fprintf(logfile , "\n");
		
	fprintf(logfile , "ICMP Header\n");
	fprintf(logfile , "   |-Type : %d",(unsigned int)(icmph->type));
			
	if((unsigned int)(icmph->type) == 11)
	{
		fprintf(logfile , "  (TTL Expired)\n");
	}
	else if((unsigned int)(icmph->type) == ICMP_ECHOREPLY)
	{
		fprintf(logfile , "  (ICMP Echo Reply)\n");
	}
	
	fprintf(logfile , "   |-Code : %d\n",(unsigned int)(icmph->code));
	fprintf(logfile , "   |-Checksum : %d\n",ntohs(icmph->checksum));
	//fprintf(logfile , "   |-ID       : %d\n",ntohs(icmph->id));
	//fprintf(logfile , "   |-Sequence : %d\n",ntohs(icmph->sequence));
	fprintf(logfile , "\n");

	fprintf(logfile , "IP Header\n");
	PrintData(Buffer,iphdrlen);
		
	fprintf(logfile , "UDP Header\n");
	PrintData(Buffer + iphdrlen , sizeof icmph);
		
	fprintf(logfile , "Data Payload\n");	
	
	//Move the pointer ahead and reduce the size of string
	PrintData(Buffer + header_size , (Size - header_size) );
	
	fprintf(logfile , "\n###########################################################");
}


void got_packet(u_char *args, const struct pcap_pkthdr *header, const u_char *packet)
{
    FILE* outFile=stdout;
    fprintf(outFile,"\n//----------Packet %d----------\n",total++);
    const struct sniff_ethernet *ethernet; /* Заголовок Ethernet */
    const struct sniff_ip *ip;             /* Заголовок IP */
    const struct sniff_tcp *tcp;           /* Заголовок TCP */
    const char *payload;                   /* Данные пакета */

    u_int size_ip;
    u_int size_tcp;


    ethernet = (struct sniff_ethernet *)(packet);
    ip = (struct sniff_ip *)(packet + SIZE_ETHERNET);
    size_ip = IP_HL(ip) * 4;
    if (size_ip < 20)
    {
        printf("   * Invalid IP header length: %u bytes\n", size_ip);
        return;
    }
	switch (ip->ip_p) //Check the Protocol and do accordingly...
	{
		case 6:  //TCP Protocol
			++tcp;
			print_tcp_packet(buffer , size);
			break;
		
		case 17: //UDP Protocol
			++udp;
			print_udp_packet(buffer , size);
			break;
		
		default: //Some Other Protocol like ARP etc.
			++others;
			break;
	}
	printf("TCP : %d   UDP : %d   ICMP : %d   IGMP : %d   Others : %d   Total : %d\r", tcp , udp , icmp , igmp , others , total);




    tcp = (struct sniff_tcp *)(packet + SIZE_ETHERNET + size_ip);
    size_tcp = TH_OFF(tcp) * 4;
    if (size_tcp < 20)
    {
        printf("   * Invalid TCP header length: %u bytes\n", size_tcp);
        return;
    }
    payload = (u_char *)(packet + SIZE_ETHERNET + size_ip + size_tcp);

    print_ethernet_header(ethernet,stdout);
    print_ip_header(ip,stdout);

    for(int i=0;i<header->len;i++) { 
            
            
            if(isprint(packet[i]))            /* Проверка, является ли символ печатаемым */
                printf("%c ",packet[i]);       /* Печать символа */
            else 
                printf(" . ",packet[i]);       /* Если символ непечатаемый, вывод . */
            if((i%16==0 && i!=0) || i==header->len-1) 
                printf("\n"); 
    }
}