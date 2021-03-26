package lab3;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GridWorkerStrictAlt extends Application {
    private  MyGrid grid;
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

        Button startButton=new Button("Start");

        this.grid.getGrid().add(this.task1.getMyNodeBox().getvBox(), this.task1.getMyNodeBox().getStartColumn(),this.task1.getMyNodeBox().getStartRow());
        this.grid.getGrid().add(this.task2.getMyNodeBox().getvBox(), this.task2.getMyNodeBox().getStartColumn(),this.task2.getMyNodeBox().getStartRow());
        this.grid.getGrid().add(startButton,2,2);
//        setThreads();
//        setHandlers();

        primaryStage.setScene(new Scene(grid.getGrid(),800,600));
        primaryStage.show();

//        this.task1Thread.start();
//        this.task2Thread.start();

        grid.setSomeInCriticalPlace(false);

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                strictAlt1();
            }
        });
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                thread.start();
            }
        });

    }

//-------------------------Strict alteration-------------------------
    private void strictAlt1(){
        while (this.grid.isSomeInCriticalPlace()){}
        criticalRegion(task1);
        grid.setSomeInCriticalPlace(true);
        nonCriticalRegion1(task1);
    }
    private void strictAlt2(){
            while (!this.grid.isSomeInCriticalPlace()){}
            criticalRegion(task2);
            grid.setSomeInCriticalPlace(false);
            nonCriticalRegion2(task2);
    }

    public void criticalRegion(ITask task){
        System.out.println(task.getMyNodeBox().getNameLabel().getText()+" in critical region!");
        task.getMyNodeBox().getTextField().setVisible(true);
        task.getMyNodeBox().getGoToCriticalPlaceButton().setVisible(false);
        task.getMyNodeBox().getInputButton().setVisible(true);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task.getMyNodeBox().setTextInputString(task.getMyNodeBox().getTextField().getText());
        task.makeTask();
        Platform.runLater(()->task.getMyNodeBox().getResultLabel().setText(task.getMyNodeBox().getResultString()));
    }
    public void nonCriticalRegion1(ITask task){
        System.out.println(task.getMyNodeBox().getNameLabel().getText()+" in non critical region!");
        task.getMyNodeBox().getTextField().setVisible(false);
        task.getMyNodeBox().getGoToCriticalPlaceButton().setVisible(true);
        task.getMyNodeBox().getInputButton().setVisible(false);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        strictAlt2();
    }
    public void nonCriticalRegion2(ITask task){
        System.out.println(task.getMyNodeBox().getNameLabel().getText()+" in non critical region!");
        task.getMyNodeBox().getTextField().setVisible(false);
        task.getMyNodeBox().getGoToCriticalPlaceButton().setVisible(true);
        task.getMyNodeBox().getInputButton().setVisible(false);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        strictAlt1();
    }


//----------------------------------------------------------------------------------
//    private void setThreads(){
//        this.task1Thread=new Thread(new Runnable() {
//            @Override
//            public void run() {
//                strictAlg1(task1);
//            }
//        });
//        this.task2Thread=new Thread(new Runnable() {
//            @Override
//            public void run() {
//                strictAlg2(task2);
//            }
//        });
//    }
//    private void strictAlg1(ITask task){
//        while (true)
//        {
//            while (!this.grid.isSomeInCriticalPlace()){}
//            criticalRegion(task.getMyNodeBox().getNameLabel().getText());
//            task.getMyNodeBox().getGoToCriticalPlaceButton().fire();
//
//        }
//    }
//    private void strictAlg2(ITask task){
//        while (true)
//        {
//            while (this.grid.isSomeInCriticalPlace()){}
//            criticalRegion(task.getMyNodeBox().getNameLabel().getText());
//            task.getMyNodeBox().getGoToCriticalPlaceButton().fire();
//
//        }
//    }
//
//    private void criticalRegion(String name){
//
//
//        System.out.println(name+" in critical region!");
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//    private void nonCriticalRegion(String name){
//
//
//        try {
//            Thread.sleep(300);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(name+" in non critical region!");
//
//    }
//
//    private void setHandlers(){
//        this.task1.getMyNodeBox().getInputButton().setOnAction(new InputHandler(task1));
//        this.task1.getMyNodeBox().getGoToCriticalPlaceButton().setOnAction(new GoToHandler(task1));
//
//        this.task2.getMyNodeBox().getInputButton().setOnAction(new InputHandler2(task2));
//        this.task2.getMyNodeBox().getGoToCriticalPlaceButton().setOnAction(new GoToHandler(task2));
//    }
//
//    private class GoToHandler implements EventHandler {
//        private ITask task;
//        public GoToHandler(ITask task){
//            this.task=task;
//        }
//        @Override
//        public  void handle(Event event) {
//            this.task.getMyNodeBox().getTextField().setVisible(true);
//            this.task.getMyNodeBox().getGoToCriticalPlaceButton().setVisible(false);
//            this.task.getMyNodeBox().getInputButton().setVisible(true);
//        }
//    }
//    private class InputHandler implements EventHandler {
//        private ITask task;
//        public InputHandler(ITask task){
//            this.task=task;
//        }
//        @Override
//        public void handle(Event event) {
//            task.getMyNodeBox().setTextInputString(task.getMyNodeBox().getTextField().getText());
//            task.makeTask();
//            task.getMyNodeBox().getResultLabel().setText(task.getMyNodeBox().getResultString());
//
//            this.task.getMyNodeBox().getTextField().setVisible(false);
//            this.task.getMyNodeBox().getGoToCriticalPlaceButton().setVisible(true);
//            this.task.getMyNodeBox().getInputButton().setVisible(false);
//
//            grid.setSomeInCriticalPlace(true);
//            nonCriticalRegion(task.getMyNodeBox().getNameLabel().getText());
//        }
//    }
//     private class InputHandler2 implements EventHandler {
//            private ITask task;
//            public InputHandler2(ITask task){
//                this.task=task;
//            }
//            @Override
//            public void handle(Event event) {
//                task.getMyNodeBox().setTextInputString(task.getMyNodeBox().getTextField().getText());
//                task.makeTask();
//                task.getMyNodeBox().getResultLabel().setText(task.getMyNodeBox().getResultString());
//
//                this.task.getMyNodeBox().getTextField().setVisible(false);
//                this.task.getMyNodeBox().getGoToCriticalPlaceButton().setVisible(true);
//                this.task.getMyNodeBox().getInputButton().setVisible(false);
//
//                grid.setSomeInCriticalPlace(false);
//                nonCriticalRegion(task.getMyNodeBox().getNameLabel().getText());
//            }
//    }

}
