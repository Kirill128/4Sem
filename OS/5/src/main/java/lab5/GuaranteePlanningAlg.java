package lab5;

import javafx.application.Platform;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GuaranteePlanningAlg implements Runnable {
    private final long timerDelayTime=1000;

    private List<Process> processList;
    private long processorWorkingTime;
    private Process currentProcess;
    private long qwant;
    private XYChart.Series barChartSeries;
    private BarChart barChart;

    private long workingTime;

    public GuaranteePlanningAlg(List<Process> processList, long processorWorkingTime, Process currentProcess, long qwant, XYChart.Series barChartSeries) {
        this.processList = processList;
        this.processorWorkingTime = processorWorkingTime;
        this.currentProcess = currentProcess;
        this.qwant=qwant;
        this.barChartSeries=barChartSeries;
    }



    public void startNext(){
        Process processToStart=selectNextProcess();
        if(processToStart==null)return;
        this.currentProcess=processToStart;
        System.out.println("Process "+processToStart.getPid()+" Started!!!");
        System.out.println(processToStart);
        processWorking(processToStart);
        processEnd(processToStart);
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
        this.processorWorkingTime+=sleepTime;
    }
    public void processEnd(Process process){
        System.out.println(process);
        System.out.println("Process " + process.getPid()+ "   ended !!!" );
        if(process.remainedTime()==0)this.processList.remove(process);
    }
    public Process selectNextProcess(){
        if(processList.isEmpty())return null;
        Process minProcess=this.processList.get(0);
        for(Process selectProcess : processList){
            if(1.0*minProcess.getWorkingTime()/processorWorkingTime > 1.0*selectProcess.getWorkingTime()/processorWorkingTime){
                minProcess=selectProcess;
            }
        }
        return minProcess;
    }

    @Override
    public void run() {
        new Timer().schedule(new MyTimer(),0,timerDelayTime);
        while(processList.size()!=0){
            startNext();
        }
    }

    public void setStat(){
        this.processList.stream().forEach(Process::setStat);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                barChartSeries.setName(String.valueOf(workingTime));
            }
        });
    }

    public class MyTimer extends TimerTask {
        @Override
        public void run() {
            workingTime+=timerDelayTime;
            setStat();

        }
    }




    public List<Process> getProcessList() {
        return processList;
    }

    public void setProcessList(List<Process> processList) {
        this.processList = processList;
    }

    public long getProcessorWorkingTime() {
        return processorWorkingTime;
    }

    public void setProcessorWorkingTime(long processorWorkingTime) {
        this.processorWorkingTime = processorWorkingTime;
    }

    public Process getCurrentProcess() {
        return currentProcess;
    }

    public void setCurrentProcess(Process currentProcess) {
        this.currentProcess = currentProcess;
    }

    public long getQwant() {
        return qwant;
    }

    public void setQwant(long qwant) {
        this.qwant = qwant;
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
