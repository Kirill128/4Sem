package lab5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;

/*
* 4. Циклическое планирование
Каждому процессу назначается определенный интервал времени,
называемый его квантом, в течение которого ему предоставляется
возможность выполнения. Если процесс к завершению кванта времени все
еще выполняется, то ресурс центрального процессора у него отбирается и
передается другому процессу.
При реализации необходимо предусмотреть следующие функции:
 выбор количества заданий в очереди (от 2 до 10);
 запуск и остановка процесса выполнения;
 выбор размера кванта времени выполнения;
 назначение времени выполнения для каждого задания;
 постановка нового задания в очередь;
*
*
* 4. Цилическое планирование
Каждому процессу назначается определенный интервал времени,
называемый его квантом, в течение которого ему предоставляется
возможность выполнения. Если процесс к завершению кванта времени все
еще выполняется, то ресурс центрального процессора у него отбирается и
передается другому процессу.
При реализации необходимо предусмотреть следующие функции:
 выбор количества заданий в очереди (от 2 до 10);
 запуск и остановка процесса выполнения;
 выбор размера кванта времени выполнения;
 назначение времени выполнения для каждого задания;
 постановка нового задания в очередь;
* */
public class Main extends Application {
    public final int PROCESS_COUNT=9;
    public final long TIME_TO_WORK=500000;
    public final long DEFAULT_QWANT=1000;
    public final long PROCESSOR_WORKING_TIME=1;


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        VBox vbox=new VBox();
        CyclePlanningAlg cyclePlanningAlg=cyclePlanningAlgFactory();
        GuaranteePlanningAlg guaranteePlanningAlg=guaranteePlanningAlgFactory();

        vbox.getChildren().add(cyclePlanningAlg.getBarChart());
        vbox.getChildren().add(guaranteePlanningAlg.getBarChart());

        Scene scene=new Scene(vbox);
        stage.setScene(scene);
        stage.show();
        startCyclePlanningAlg(cyclePlanningAlg);
        startGuaranteePlanningAlg(guaranteePlanningAlg);
    }
    //---------------------------------------
    public void startCyclePlanningAlg( CyclePlanningAlg cyclePlanningAlg){
        Thread cyclePlanningAlgThread=new Thread(cyclePlanningAlg);
        cyclePlanningAlgThread.start();
    }

    public CyclePlanningAlg cyclePlanningAlgFactory(){
        CategoryAxis xAxis1    = new CategoryAxis();
        xAxis1.setLabel("Threads num");
        NumberAxis yAxis1 = new NumberAxis();
        yAxis1.setLabel("Time in sec");
        BarChart barChart1 = new BarChart(xAxis1, yAxis1);
        XYChart.Series dataSeries1 = new XYChart.Series();
        barChart1.setStyle("-fx-background-color:  aqua;");

        List<Process> queue=new LinkedList<>();
        for(int i=0;i<PROCESS_COUNT;i++){
            XYChart.Data chartData= new XYChart.Data(String.valueOf(i),0);
            queue.add(new Process(i,TIME_TO_WORK,chartData));
            dataSeries1.getData().add(chartData);
        }
        barChart1.getData().add(dataSeries1);

        CyclePlanningAlg cyclePlanningAlg=new CyclePlanningAlg(DEFAULT_QWANT,queue,dataSeries1);
        cyclePlanningAlg.setBarChart(barChart1);
        return cyclePlanningAlg;
    }


    //------------------------------------------

    public void startGuaranteePlanningAlg(GuaranteePlanningAlg guaranteePlanningAlg){
        Thread guaranteePlanningAlgThread=new Thread(guaranteePlanningAlg);
        guaranteePlanningAlgThread.start();
    }
    public GuaranteePlanningAlg guaranteePlanningAlgFactory(){
        CategoryAxis xAxis2    = new CategoryAxis();
        xAxis2.setLabel("Threads num");
        NumberAxis yAxis2 = new NumberAxis();
        yAxis2.setLabel("Time in sec");
        BarChart barChart2 = new BarChart(xAxis2, yAxis2);
        XYChart.Series dataSeries1 = new XYChart.Series();


        List<Process> processList=new LinkedList<>();
        for(int i=0;i<PROCESS_COUNT;i++){
            XYChart.Data chartData= new XYChart.Data(String.valueOf(i),0);
            processList.add(new Process(i,getRandomWorkingTime(),TIME_TO_WORK,chartData));
            dataSeries1.getData().add(chartData);
        }
        barChart2.getData().add(dataSeries1);
        processList.stream().forEach(e-> System.out.println(e.getPid()+"  ; Working :"+e.getWorkingTime()+" ; Ratio :"+1.0*e.getWorkingTime()/PROCESSOR_WORKING_TIME ));

        GuaranteePlanningAlg guaranteePlanningAlg=new GuaranteePlanningAlg(processList,PROCESSOR_WORKING_TIME,processList.get(0),DEFAULT_QWANT,dataSeries1);
        guaranteePlanningAlg.setBarChart(barChart2);
        return guaranteePlanningAlg;
    }
    public long getRandomWorkingTime(){
        Random random=new Random();
        return random.nextInt(500)+1000;
    }

}
