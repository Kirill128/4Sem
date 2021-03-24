package by.grsu.os.lab2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        int k=100000;
        int iMax=100;
        List<List<Double>> times=new ArrayList<>();
        long middleTime;

        for(int threadsCount = 1;threadsCount<20;threadsCount++) {
            middleTime = 0;
            for (int i = 0; i < iMax; i++) {
                List<List<Double>> lists = diviteToArrays(makeArray(100), threadsCount);
                middleTime += threadsExec(lists, k);
            }
            List<Double> time=new ArrayList<>(2);
            time.add((double)threadsCount);
            time.add(1.0*middleTime/iMax);
            times.add(time);
        }

        middleTime = 0;
        for (int i = 0; i < iMax; i++) {
            List<List<Double>> lists = diviteToArrays(makeArray(100), 1);
            middleTime += threadsExec(lists, k);
        }
        middleTime /= iMax;
        System.out.println("Threads count: 1, Middle time:"+(middleTime));

        times.get(0).set(1,1.0*middleTime);
//---------------javafx work------------------------

        CategoryAxis categoryAxis=new CategoryAxis();
        categoryAxis.setLabel("Threads Count");

        NumberAxis numberAxis=new NumberAxis();
        numberAxis.setLabel("Work time(nano sec.)");

        XYChart.Series series=new XYChart.Series();
        for(List<Double> one:times){
            System.out.println("Threads count: "+one.get(0)+", Middle time: "+one.get(1)+". Faster then 1 thread: "+middleTime/one.get(1));
            series.getData().add(new XYChart.Data(String.valueOf(one.get(0)),one.get(1)));
        }

        BarChart barChart=new BarChart(categoryAxis,numberAxis);

        barChart.getData().add(series);


        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(barChart, 500, 500));
        primaryStage.show();


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
        List<List<Double>> result = new ArrayList<>(subArrCount);
        if (subArrCount <= mainArr.size()){

//            double calcElInArr=1.0*mainArr.size() / subArrCount;
//            int calc=(int)calcElInArr;
//            if(calcElInArr+0.51>=calc+1)calc++;
//            int elInArr = calc;
//
//            int elIndx = 0;
//            for (int subArrIndx = 0; subArrIndx < subArrCount - 1; subArrIndx++) {
//                List<Double> one = mainArr.subList(elIndx, elIndx = (elIndx + elInArr));
//                result.add(one);
//            }
//            result.add(mainArr.subList(elIndx, mainArr.size()));
            //------------------
            for(int i=0;i<subArrCount;i++){result.add(new ArrayList<>());}
            int subListCount=subArrCount;
            int elIndex=0;
            for(int i=0;i<=subArrCount;i++){
                if(i==subArrCount){
                    i=0;
                }
                if(mainArr.size()<=elIndex){
                    break;
                }

                result.get(i).add(mainArr.get(elIndex++));
            }
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

    public static void main(String[] args) {
        launch(args);
    }
}
