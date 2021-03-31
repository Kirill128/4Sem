package lab3;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GridWorkerPaterson extends Application {

    private MyGrid grid;
    private ITask task1;
    private ITask task2;

    public static final int N=2;
    private int[] interested;
    private int turn;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.interested=new int[N];

        this.grid=GridFabric.makeGrid();
        this.task1=new Task1(MyNodeBoxFabric.makeMyNodeBox("Task 1",0,1));
        this.task2=new Task2(MyNodeBoxFabric.makeMyNodeBox("Task 2",2,1));

        Button startButton=new Button("Start");

        this.grid.getGrid().add(this.task1.getMyNodeBox().getvBox(), this.task1.getMyNodeBox().getStartColumn(),this.task1.getMyNodeBox().getStartRow());
        this.grid.getGrid().add(this.task2.getMyNodeBox().getvBox(), this.task2.getMyNodeBox().getStartColumn(),this.task2.getMyNodeBox().getStartRow());
        this.grid.getGrid().add(startButton,2,2);

        primaryStage.setScene(new Scene(grid.getGrid(),800,600));
        primaryStage.show();

        grid.setSomeInCriticalPlace(false);

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    enterRegion(0);
                    leaveRegion(0);
                    enterRegion(1);
                    leaveRegion(1);
                }
            }
        });
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                thread.start();
            }
        });

    }
    public void enterRegion(int process){
        int other;
        other=1-process;
        interested[process]=1;
        turn=process;
        while(turn==process && interested[other]==1){}
        criticalRegion(process);
    }
    public void leaveRegion(int process){
        nonCriticalRegion(process);
        interested[process]=0;
    }

    //-------------------------Strict alteration-------------------------
    public void criticalRegion(int process){
        ITask task= (process==1)? task2:task1;

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
    public void nonCriticalRegion(int process){
        ITask task=(process==1)? task2:task1;

        System.out.println(task.getMyNodeBox().getNameLabel().getText()+" in non critical region!");
        task.getMyNodeBox().getTextField().setVisible(false);
        task.getMyNodeBox().getGoToCriticalPlaceButton().setVisible(true);
        task.getMyNodeBox().getInputButton().setVisible(false);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



}
