#include "ethernet.c"
//header
// Время захвата
// длина заголовка
// длина пакета

int main(int argc, char **argv)
{
    FILE * s=fopen("stat.pcap","w");
    fclose(s);
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
    time(&start_time);
    time(&current_time);
    pcap_loop(handle, -1, got_packet, NULL);

    return 0;
}