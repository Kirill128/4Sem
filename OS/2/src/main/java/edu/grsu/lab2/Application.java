package edu.grsu.lab2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        int k=1000;
        int iMax=10;
        List<List<Double>> times=new ArrayList<>();
        long middleTime;

        for(int threadsCount = 1;threadsCount<100;threadsCount++) {
            middleTime = 0;
            for (int i = 0; i < iMax; i++) {
                List<List<Double>> lists = diviteToArrays(makeArray(10), threadsCount);
                middleTime += threadsExec(lists, k);
            }
            List<Double> time=new ArrayList<>(2);
            time.add((double)threadsCount);
            time.add(1.0*middleTime/iMax);
            times.add(time);
        }

        middleTime = 0;
        for (int i = 0; i < iMax; i++) {
            List<List<Double>> lists = diviteToArrays(makeArray(10), 1);
            middleTime += threadsExec(lists, k);
        }
        middleTime /= iMax;
        System.out.println("Threads count: 1, Middle time:"+(middleTime));


        List<Double> theMostFast=times.get(0);
        for(List<Double> one:times){
            System.out.println("Threads count: "+one.get(0)+", Middle time: "+one.get(1)+". Faster then 1 thread: "+middleTime/one.get(1));
            if(){

            }
        }

    }

    public static long threadsExec(List<List<Double>> arrays,int k) throws InterruptedException {
        long startTime=System.nanoTime();
        Thread[]threads=new Thread[arrays.size()];
        for(int i=0;i<threads.length;i++){
            int indx=i;
            threads[i]=new Thread(new Runnable() {
                @Override
                public void run() {
//                    System.out.println(indx+" Started");
                    arrayProcess(arrays.get(indx),k);
//                    System.out.println(indx+" Ended");
                }
            });
        }
        for(int i=0;i<threads.length;i++){
            threads[i].start();
        }
        for(int i=0;i<threads.length;i++){
            threads[i].join();
        }
        return System.nanoTime()-startTime;
    }

    public static void arrayProcess(List<Double> array,int k){
        List<Double> finalArr=new ArrayList<>();
        double currentEl;
        for(int i=0;i<array.size();i++){
            currentEl=array.get(i);
            for(int j=0;j<k;j++){
                currentEl+=Math.pow(array.get(i),1.789);
            }
            finalArr.add(currentEl);
        }
    }
    public static List<List<Double>> diviteToArrays(List<Double> mainArr,int subArrCount) {
        List<List<Double>> result = new ArrayList<>();
        if (subArrCount <= mainArr.size()){
            int elInArr = mainArr.size() / subArrCount;
            int elIndx = 0;
            for (int subArrIndx = 0; subArrIndx < subArrCount - 1; subArrIndx++) {
                List<Double> one = mainArr.subList(elIndx, elIndx = (elIndx + elInArr));
                result.add(one);
            }
            result.add(mainArr.subList(elIndx, mainArr.size()));
        }else
        {
            int i;
            for(i=0;i<mainArr.size();i++){
                List<Double> one=new ArrayList<Double>();
                one.add(mainArr.get(i));
                result.add(one);
            }
            for(;i<subArrCount;i++)
                result.add(new ArrayList<>());
        }
        return  result;
    }
    public static List<Double> makeArray(int length){
        List<Double> arr=new ArrayList<>(length);
        for(int i=0;i<length;i++) {
            arr.add((double)(i+20));
        }
        return arr;
    }
}
