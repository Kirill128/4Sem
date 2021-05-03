package lab5;

import javafx.application.Platform;
import javafx.scene.chart.XYChart;

public class Process {

    private int pid;

    private long workingTime;
    private long timeToWork;
    private XYChart.Data data;

    public Process(int pid, long workingTime, long timeToWork, XYChart.Data data) {
        this.pid = pid;
        this.workingTime = workingTime;
        this.timeToWork = timeToWork;
        this.data = data;
    }
    public Process(int pid, long timeToWork, XYChart.Data data) {
        this.pid = pid;
        this.timeToWork = timeToWork;
        this.data=data;
    }



    public long remainedTime(){
        long remained=timeToWork-workingTime;
        return (remained>=0) ? remained : 0;
    }

    public void setStat(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                data.setYValue(workingTime);

            }
        });
    }


    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public long getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(long workingTime) {
        this.workingTime = workingTime;
    }

    public long getTimeToWork() {
        return timeToWork;
    }

    public void setTimeToWork(long timeToWork) {
        this.timeToWork = timeToWork;
    }

    @Override
    public String toString() {
        return "Process{" +
                "pid=" + pid +
                ", workingTime=" + workingTime +
                ", timeToWork=" + timeToWork +
                '}';
    }

    public XYChart.Data getData() {
        return data;
    }

    public void setData(XYChart.Data data) {
        this.data = data;
    }


}
