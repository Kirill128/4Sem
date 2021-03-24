package lab3;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.xml.ws.handler.Handler;
import java.util.EventListener;


public class App extends Application{
    public static final int CRITICAL_PLACE_ROW=1;
    public static final int CRITICAL_PLACE_COLUMN=2;
    private GridPane grid;
    private MyNodeBox taskBox1;
    private MyNodeBox taskBox2;

    private volatile boolean someInCriticalPlace;

    @Override
    public void start(Stage primaryStage) throws Exception{
        int windowWidth=800;
        int windowHeight=600;

        this.grid=gridInit(800,600);

        this.taskBox1=makeTaskGroup("Task 1", 0, 0, new TaskMaker() {
            @Override
            public void makeTask() {
                task1GetResult();
            }
        });
        this.taskBox2=makeTaskGroup("Task 2", 2, 0, new TaskMaker() {
            @Override
            public void makeTask() {
                task2GetResult();
            }
        });

        grid.add(taskBox1.getvBox(),taskBox1.getColumn(),taskBox1.getRow());
        grid.add(taskBox2.getvBox(),taskBox2.getColumn(),taskBox2.getRow());


        primaryStage.setScene(new Scene(grid, windowWidth, windowHeight));
        primaryStage.show();

        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                taskBox1.getGoToCriticalPlaceButton().fire();

            }
        });
        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                taskBox2.getGoToCriticalPlaceButton().fire();
            }
        });

        Button buttonStart=new Button("Start");
        grid.add(buttonStart,3,2);
        buttonStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                thread1.start();
                thread2.start();
            }
        });
    }

//--------------------Strict alteration----------------------------------
    public void strictAlternation(TaskMaker taskMaker){
        while (this.someInCriticalPlace) {}
        criticalRegion();
    }

    public void criticalRegion(){
        this.someInCriticalPlace=true;
        System.out.println("Task in critical region!!");
    }
    public void nonCriticalRegion(){
        this.someInCriticalPlace=false;
        System.out.println("Task in non critical region!!");
    }

//----------------------------Tasks Execute-------------------------------------
    public void task1GetResult(){
        int input=Integer.valueOf(taskBox1.getTextField().getText());
        taskBox1.getResultLabel().setText("Result: "+task1Proccess(input));
    }
    public void task2GetResult(){
        String input=taskBox2.getTextField().getText();
        char c=input.toCharArray()[0];
        taskBox2.getResultLabel().setText("Result: "+(int)c);
    }
    public long task1Proccess(int input){
        if(input==1){
            return 1;
        }
        return input*task1Proccess(input-1);
    }

//-----------------------------------------------------------------------
    public MyNodeBox makeTaskGroup(String name,int row,int column,TaskMaker taskMaker){
        Label nameLabel=new Label(name);
        Label result=new Label("Result: ");
        TextField textField=new TextField();
        textField.setMaxWidth(150);
        textField.setVisible(false);

        Button inputButton = new Button("Some input");
        Button goToCriticalPlaceButton = new Button("Go To Critical place");

        VBox vBox=new VBox(10,nameLabel,textField,goToCriticalPlaceButton,inputButton,result);

        MyNodeBox myNodeBox=new MyNodeBox(
                nameLabel,
                result,
                textField,
                goToCriticalPlaceButton,
                inputButton,
                vBox,
                row,
                column,
                taskMaker
        );
        GoToCriticalRegionHandlerPaint a=new GoToCriticalRegionHandlerPaint(myNodeBox);
        InputHandlerPaint b=new InputHandlerPaint(myNodeBox);
        Button buttonGoto=new Button();
        Button buttonInput=new Button();

        buttonGoto.setOnAction(a);
        buttonInput.setOnAction(b);
        goToCriticalPlaceButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buttonGoto.fire();
            }
        });
        inputButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buttonInput.fire();
            }
        });
        inputButton.setVisible(false);

        vBox.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        return myNodeBox;
    }

    public GridPane gridInit( int windowWidth,int windowHeight){
        GridPane grid=new GridPane();
        grid.setGridLinesVisible(true);
        grid.getColumnConstraints().add(new ColumnConstraints(windowWidth/4));
        grid.getColumnConstraints().add(new ColumnConstraints(windowWidth/4));
        grid.getColumnConstraints().add(new ColumnConstraints(windowWidth/4));
        grid.getColumnConstraints().add(new ColumnConstraints(windowWidth/4));
        grid.getRowConstraints().add(new RowConstraints(windowHeight/3));
        grid.getRowConstraints().add(new RowConstraints(windowHeight/3));
        grid.getRowConstraints().add(new RowConstraints(windowHeight/3));
        return grid;
    }

    public static void main(String[] args) {
        launch(args);
    }

    private class GoToCriticalRegionHandlerPaint implements EventHandler{

        public GoToCriticalRegionHandlerPaint(MyNodeBox myNodeBox) {
            this.myNodeBox = myNodeBox;
        }

        private MyNodeBox myNodeBox;

        @Override
        public void handle(Event event) {

            this.myNodeBox.getTextField().setVisible(true);
            this.myNodeBox.goToCriticalPlaceButton.setVisible(false);
            this.myNodeBox.inputButton.setVisible(true);
            grid.getChildren().remove(this.myNodeBox.vBox);
            grid.add(this.myNodeBox.vBox,CRITICAL_PLACE_COLUMN,CRITICAL_PLACE_ROW);
        }
    }
    private class InputHandlerPaint implements EventHandler {
        public InputHandlerPaint(MyNodeBox myNodeBox) {
            this.myNodeBox = myNodeBox;
        }

        private MyNodeBox myNodeBox;
        @Override
        public void handle(Event event) {

            this.myNodeBox.textField.setVisible(false);
            this.myNodeBox.goToCriticalPlaceButton.setVisible(true);
            this.myNodeBox.inputButton.setVisible(false);
            grid.getChildren().remove(this.myNodeBox.vBox);
            grid.add(this.myNodeBox.vBox,this.myNodeBox.column,this.myNodeBox.row);

            this.myNodeBox.taskMaker.makeTask();
            nonCriticalRegion();
        }
    }
1
}
