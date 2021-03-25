package lab3;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GridWorker extends Application {
    private volatile MyGrid grid;
    private ITask task1;
    private ITask task2;

    private Thread task1Thread;
    private Thread task2Thread;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.grid=GridFabric.makeGrid();
        this.task1=new Task1(MyNodeBoxFabric.makeMyNodeBox("Task 1",0,1));
        this.task2=new Task2(MyNodeBoxFabric.makeMyNodeBox("Task 2",2,1));

        this.grid.getGrid().add(this.task1.getMyNodeBox().getvBox(), this.task1.getMyNodeBox().getStartColumn(),this.task1.getMyNodeBox().getStartRow());
        this.grid.getGrid().add(this.task2.getMyNodeBox().getvBox(), this.task2.getMyNodeBox().getStartColumn(),this.task2.getMyNodeBox().getStartRow());

        setThreads();
        setHandlers();

        primaryStage.setScene(new Scene(grid.getGrid(),800,600));
        primaryStage.show();

        this.task1Thread.start();
        this.task2Thread.start();

    }
    private void setThreads(){
        this.task1Thread=new Thread(new Runnable() {
            @Override
            public void run() {
                strictAlg1(task1);
            }
        });
        this.task2Thread=new Thread(new Runnable() {
            @Override
            public void run() {
                strictAlg2(task2);
            }
        });
    }
    private void strictAlg1(ITask task){
        while (true)
        {
            while (!this.grid.isSomeInCriticalPlace()){}
            criticalRegion(task.getMyNodeBox().getNameLabel().getText());
            
            task.getMyNodeBox().getGoToCriticalPlaceButton().fire();
        }
    }
    private void strictAlg2(ITask task){
        while (true)
        {
            while (this.grid.isSomeInCriticalPlace()){}
            criticalRegion(task.getMyNodeBox().getNameLabel().getText());
            task.getMyNodeBox().getGoToCriticalPlaceButton().fire();
        }
    }

    private void criticalRegion(String name){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name+" in critical region!");
    }
    private void nonCriticalRegion(String name){
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name+" in non critical region!");
    }

    private void setHandlers(){
        this.task1.getMyNodeBox().getInputButton().setOnAction(new InputHandler(task1));
        this.task1.getMyNodeBox().getGoToCriticalPlaceButton().setOnAction(new GoToHandler(task1));

        this.task2.getMyNodeBox().getInputButton().setOnAction(new InputHandler2(task2));
        this.task2.getMyNodeBox().getGoToCriticalPlaceButton().setOnAction(new GoToHandler(task2));
    }

    private class GoToHandler implements EventHandler {
        private ITask task;
        public GoToHandler(ITask task){
            this.task=task;
        }
        @Override
        public void handle(Event event) {
            this.task.getMyNodeBox().getTextField().setVisible(true);
            this.task.getMyNodeBox().getGoToCriticalPlaceButton().setVisible(false);
            this.task.getMyNodeBox().getInputButton().setVisible(true);
        }
    }
    private class InputHandler implements EventHandler {
        private ITask task;
        public InputHandler(ITask task){
            this.task=task;
        }
        @Override
        public void handle(Event event) {
            task.getMyNodeBox().setTextInputString(task.getMyNodeBox().getTextField().getText());
            task.makeTask();
            task.getMyNodeBox().getResultLabel().setText(task.getMyNodeBox().getResultString());

            this.task.getMyNodeBox().getTextField().setVisible(false);
            this.task.getMyNodeBox().getGoToCriticalPlaceButton().setVisible(true);
            this.task.getMyNodeBox().getInputButton().setVisible(false);

            grid.setSomeInCriticalPlace(true);
            nonCriticalRegion(task.getMyNodeBox().getNameLabel().getText());
        }
    }
     private class InputHandler2 implements EventHandler {
            private ITask task;
            public InputHandler2(ITask task){
                this.task=task;
            }
            @Override
            public void handle(Event event) {
                task.getMyNodeBox().setTextInputString(task.getMyNodeBox().getTextField().getText());
                task.makeTask();
                task.getMyNodeBox().getResultLabel().setText(task.getMyNodeBox().getResultString());

                this.task.getMyNodeBox().getTextField().setVisible(false);
                this.task.getMyNodeBox().getGoToCriticalPlaceButton().setVisible(true);
                this.task.getMyNodeBox().getInputButton().setVisible(false);

                grid.setSomeInCriticalPlace(false);
                nonCriticalRegion(task.getMyNodeBox().getNameLabel().getText());
            }
    }

}
