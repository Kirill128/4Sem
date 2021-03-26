package lab3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sun.security.krb5.internal.TGSRep;

import java.util.concurrent.Semaphore;

public class GridWorker extends Application {
    private Semaphore semaphore=new Semaphore(1);
    private MyGrid grid;
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
        this.grid.getGrid().add(new TextField(),0,0);
        Button button=new Button("start");
        this.grid.getGrid().add(button,2,2);

        setThreads();
        setHandlers();

        primaryStage.setScene(new Scene(grid.getGrid(),800,600));
        primaryStage.show();

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                task2Thread.start();
                task1Thread.start();
            }
        });


    }
    private void setThreads(){
        this.task1Thread=new Thread(new Runnable() {
            @Override
            public void run() {
                strictAlt1(task1);
            }
        });
        this.task2Thread=new Thread(new Runnable() {
            @Override
            public void run() {
                strictAlt2(task2);
            }
        });
    }
    private void strictAlt1(ITask task){
        while (true)
        {
            while (!this.grid.isSomeInCriticalPlace()){}
            criticalRegion(task);
            grid.setSomeInCriticalPlace(true);
            nonCriticalRegion(task);
        }
    }
    private void strictAlt2(ITask task){
        while (true)
        {
            while (this.grid.isSomeInCriticalPlace()){}
            criticalRegion(task);
            grid.setSomeInCriticalPlace(false);
            nonCriticalRegion(task);
        }
    }

    private  void criticalRegion(ITask task){
        task.setIsInCriticalRegion(true);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(task.getMyNodeBox().getNameLabel().getText()+" in critical region!");

        task.getMyNodeBox().getTextField().setVisible(true);
        task.getMyNodeBox().getGoToCriticalPlaceButton().setVisible(false);
        task.getMyNodeBox().getInputButton().setVisible(true);
    }

    private  void nonCriticalRegion(ITask task){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task.getMyNodeBox().setTextInputString(task.getMyNodeBox().getTextField().getText());
        task.makeTask();
        task.getMyNodeBox().getResultLabel().setText(task.getMyNodeBox().getResultString());

        task.getMyNodeBox().getTextField().setVisible(false);
        task.getMyNodeBox().getGoToCriticalPlaceButton().setVisible(true);
        task.getMyNodeBox().getInputButton().setVisible(false);
        task.setIsInCriticalRegion(false);
        System.out.println(task.getMyNodeBox().getNameLabel().getText()+" in non critical region!");
    }

    private void setHandlers(){
        this.task1.getMyNodeBox().getInputButton().setOnAction(new InputHandler(task1,true));
        this.task2.getMyNodeBox().getInputButton().setOnAction(new InputHandler(task2,false));
    }

    private class InputHandler implements EventHandler {
        private ITask task;
        private final boolean value;

        public InputHandler(ITask task, boolean value) {
            this.task = task;
            this.value = value;
        }
        @Override
        public void handle(Event event) {
            try {
                semaphore.acquire();
                grid.setSomeInCriticalPlace(this.value);
                nonCriticalRegion(task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }

        }
    }

}
