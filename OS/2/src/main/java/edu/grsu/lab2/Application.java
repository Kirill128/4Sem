package edu.grsu.lab2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        arrayProcess(makeArray(10),10);
    }
    public static void arrayProcess(List<Double> array,int k){
        List<Double> finalArr=new ArrayList<>();
        double currentEl;
        long startTime=System.nanoTime();
        for(int i=0;i<array.size();i++){
            currentEl=array.get(i);
            for(int j=0;j<k;j++){
                currentEl+=Math.pow(array.get(i),1.789);
            }
            finalArr.add(currentEl);
        }
        long endTime=System.nanoTime();
        System.out.println("Res of time(Nano sek.):"+(endTime-startTime));
    }
    public static void splitTo
    public static List<Double> makeArray(int length){
        List<Double> arr=new ArrayList<>(length);
        for(int i=0;i<length;i++) {
            arr.add((double)(i+20));
        }
        return arr;
    }
}
