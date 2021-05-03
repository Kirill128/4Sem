package lab5;

import javafx.application.Platform;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CyclePlanningAlg implements Runnable{
    private final long timerDelayTime=1000;

    private long qwant;
    private List<Process> queue;
    private Process currentProcess;
    private XYChart.Series barChartSeries;
    private BarChart barChart;

    private long workingTime;

    public CyclePlanningAlg(long qwant, List<Process> queue, XYChart.Series barChartSeries) {
        this.qwant = qwant;
        this.queue = queue;
        this.barChartSeries=barChartSeries;
    }

    public void addProcessToQueue(Process process){
        queue.add(process);
    }

    public void startNextProcess(){
        Process process=this.queue.get(0);
        this.queue.remove(process);

        this.currentProcess=process;
        System.out.println("Process " + process.getPid()+ " started !!!" );
        processWorking(process);
        endProcess(process);
    }


    public void processWorking(Process process){

        long remainedTimeForProcess=process.remainedTime();
        long sleepTime= Math.min(remainedTimeForProcess, this.qwant);
        try {
                Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        process.setWorkingTime(sleepTime+process.getWorkingTime());

    }

    public void endProcess(Process process){
        System.out.println(process);
        System.out.println("Process " + process.getPid()+ "   ended !!!" );
        if(process.getTimeToWork()>process.getWorkingTime()){
            queue.add(process);
        }
    }

    @Override
    public void run() {
        new Timer().schedule(new MyTimer(),0,timerDelayTime);
        while(!this.queue.isEmpty()){
            startNextProcess();
        }
    }

    public void setStat(){
        this.queue.stream().forEach(Process::setStat);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                barChartSeries.setName(String.valueOf(workingTime));
            }
        });
    }

    public class MyTimer extends TimerTask{
        @Override
        public void run() {
            workingTime+=timerDelayTime;
            setStat();

        }
    }




    public long getQwant() {
        return qwant;
    }

    public void setQwant(long qwant) {
        this.qwant = qwant;
    }

    public long getTimerDelayTime() {
        return timerDelayTime;
    }

    public Process getCurrentProcess() {
        return currentProcess;
    }

    public void setCurrentProcess(Process currentProcess) {
        this.currentProcess = currentProcess;
    }

    public XYChart.Series getBarChartSeries() {
        return barChartSeries;
    }

    public void setBarChartSeries(XYChart.Series barChartSeries) {
        this.barChartSeries = barChartSeries;
    }

    public BarChart getBarChart() {
        return barChart;
    }

    public void setBarChart(BarChart barChart) {
        this.barChart = barChart;
    }


}
