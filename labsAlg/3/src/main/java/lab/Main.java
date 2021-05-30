package some;

import java.util.Scanner;

public class Main {
//    public static char[] arrayI=new char[]{'1','1','0','0','1','1'};
//    public static char[] arrayJ=new char[]{'A','A','A','B','B','A','A','A','B','B','B'};
    public static char[] arrayI;
    public static char[] arrayJ;

//    public static char[] arrayI=new char[]{'0','0','1','1','0'};
//    public static char[] arrayJ=new char[]{'A','A','A','A','B','B','A','A'};
    public static int globalI=-1;

    public static void main(String[] args) {
        System.out.println("Input STR 1 (0101010...)");
        arrayI=inputString();
        System.out.println("Input STR 2 (ABABABA...)");
        arrayJ=inputString();

        char[] postArray=new char[arrayJ.length];
        for(int i=0;i<postArray.length;i++)postArray[i]=' ';
        System.out.printf("Can: "+canConvert(postArray,-1,-1));
    }

    public static char[] inputString(){
        Scanner scanner=new Scanner(System.in);
        return scanner.next().toCharArray();
    }
//T(n)=T(n-1)+40+k*6
    public static boolean canConvert(char[] receiveArray,int firstXPositionOfReceiveArray,int lastXPositionOfReceiveArray){
        globalI++;
        char[] postArray=new char[receiveArray.length];
        for(int i=0;i<postArray.length;i++)postArray[i]=' ';

        int j=firstXPositionOfReceiveArray+1;
        int startOfNewInterval=j;
        boolean startFound=false;
        boolean wasInInterval=false;
        if(globalI>=arrayI.length)return true;
        if(arrayI[globalI]=='0'){
            while(j<arrayJ.length){
                if(arrayJ[j]=='A'){
                    postArray[j]='x';//++
                    if(!startFound){
                        startOfNewInterval=j;
                        startFound=true;
                    }
                    if(j<=lastXPositionOfReceiveArray+1){
                        wasInInterval=true;
                    }
                    else if(!wasInInterval)return false;
                }else{
                    if(j>lastXPositionOfReceiveArray+1){
                        break;
                    }
                }
                j++;
            }
            if(globalI==arrayI.length-1 && j==arrayJ.length-1)return true;
            return canConvert(postArray,startOfNewInterval,j-1);
        }else{
            char lastChar=' ';
            while(j<arrayJ.length){
                if(j<=lastXPositionOfReceiveArray+1){
                    lastChar=arrayJ[j];
                }else{
                    if(arrayJ[j]!=lastChar){
                        break;
                    }
                }
                postArray[j]='x';
                j++;
            }
            if(globalI==arrayI.length-1 && j==arrayJ.length-1)return true;
            return canConvert(postArray,firstXPositionOfReceiveArray+1,j-1);
        }
    }
}
