package lab;

public class Main {
    public static char[] arrayI=new char[]{'0','0','1','1','0'};
    public static char[] arrayJ=new char[]{'A','A','A','A','B','B','A','A'};
    public static int globalI=-1;

    public static void main(String[] args) {
        System.out.printf("Can: "+canConvert(new char[]{' ',' ',' ',' ',' ',' ',' ',' '},-1,-1));
    }
    public static boolean canConvert(char[] receiveArray,int firstXPositionOfReceiveArray,int lastXPositionOfReceiveArray){
        globalI++;
        char[] postArray=new char[]{' ',' ',' ',' ',' ',' ',' ',' '};

        int j=firstXPositionOfReceiveArray+1;
        int startOfNewInterval=j;
        boolean startFound=false;
        boolean wasInInterval=false;
        if(arrayI[globalI]=='0'){
            while(j<arrayJ.length){
                if(arrayJ[j]=='A'){
                    postArray[j++]='x';
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
            }
            return canConvert(postArray,startOfNewInterval,j) ;
        }else{
            int wasA=0;
            int wasB=0;
            char lastChar=' ';
            while(j<arrayJ.length){
                if(j<=lastXPositionOfReceiveArray+1){

                }else{

                }
            }

        }
        return false;
    }
}
