#include <stdio.h>
#include <stdlib.h>
#include <pcap.h> /* if this gives you an error try pcap/pcap.h */
#include <errno.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netinet/if_ether.h> /* includes net/ethernet.h */


void got_packet(u_char *args, const struct pcap_pkthdr *header, const u_char *packet){ 
    struct ether_header *eptr;
    eptr = (struct ether_ethernet *)(packet);
    
    /* Do a couple of checks to see what packet type we have..*/
    if (ntohs (eptr->ether_type) == ETHERTYPE_IP)
    {
        printf("Ethernet type hex:%x dec:%d is an IP packet\n",
                ntohs(eptr->ether_type),
                ntohs(eptr->ether_type));
    }else  if (ntohs (eptr->ether_type) == ETHERTYPE_ARP)
    {
        printf("Ethernet type hex:%x dec:%d is an ARP packet\n",
                ntohs(eptr->ether_type),
                ntohs(eptr->ether_type));
    }else {
        printf("Ethernet type %x not IP", ntohs(eptr->ether_type));
        exit(1);
    }
//------------
    ip = (struct sniff_ip *)(packet + SIZE_ETHERNET);
    size_ip = IP_HL(ip) * 4;
    if (size_ip < 20)
    {
        printf("   * Invalid IP header length: %u bytes\n", size_ip);
        return;
    }
    tcp = (struct sniff_tcp *)(packet + SIZE_ETHERNET + size_ip);
    size_tcp = TH_OFF(tcp) * 4;
    if (size_tcp < 20)
    {
        printf("   * Invalid TCP header length: %u bytes\n", size_tcp);
        return;
    }
    payload = (u_char *)(packet + SIZE_ETHERNET + size_ip + size_tcp);

    char source_ip[128];
    char dest_ip[128];

    strncpy(source_ip, inet_ntoa(source.sin_addr), sizeof(source_ip));
    strncpy(dest_ip, inet_ntoa(dest.sin_addr), sizeof(dest_ip));


    printf("Ip protocol: %x\n",ip->ip_p);
    printf("Ip life time: %x\n",ip->ip_ttl);
    printf("Ip from: %d\n",ip->ip_src);
    printf("Ip to: %d\n",ip->ip_dst);
    printf("Ethernet addres source: %x\n",ethernet->ether_shost);
    printf("Ethernet addres target: %x\n",ethernet->ether_dhost);
}

int main(int argc,char **argv){
    pcap_t* handle;
    char *dev="enp2s0";  /* name of the device to use */
    char errbuf[PCAP_ERRBUF_SIZE];
    bpf_u_int32 mask;
    bpf_u_int32 net;
    struct pcap_pkthdr header;
    const u_char *packet;

    dev = pcap_lookupdev(errbuf); /* error checking */
    if (dev == NULL)
    {
        printf("%s\n", errbuf);
        return 1;
    } 
    if(pcap_lookupnet(dev,&net,&mask,errbuf)==-1){
        fprintf(stderr,"Couldn't get mask for device %s: %s\n",dev,errbuf);
        return 2;
    }

    handle=pcap_open_live(dev,BUFSIZ,1,1000,errbuf);
    if(handle==NULL){
        fprintf(stderr,"Couldn't open device %s: %s\n",dev,errbuf);
        return 2;
    }

    pcap_loop(handle, -1, got_packet, NULL);
    
    return 0;
}