package proj;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;



public class Program extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    public void start(Stage primaryStage) throws Exception {
//        Semaphore sem = new Semaphore(Semaphore3);
//        for(int i=1;i<=6;i++)
//            new Philosopher(sem,i).start();

        Circle circle=new Circle(100);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.TRANSPARENT);
        Label lab1=new Label("Вилка 1");
        Label lab2=new Label("Вилка 2");
        Label lab3=new Label("Вилка 3");
        Label lab4=new Label("Вилка 4");
        Label lab5=new Label("Вилка 5");
        Label lab6=new Label("Вилка 6");

        GridPane gridPane=new GridPane();
        gridPane.addColumn(0);
        gridPane.addColumn(1);
        gridPane.addColumn(2);
        gridPane.addColumn(3);
        gridPane.addColumn(4);
        gridPane.addRow(0);
        gridPane.addRow(1);
        gridPane.addRow(2);
        gridPane.addRow(3);
        gridPane.addRow(4);

        gridPane.add(lab1,1,0);
        gridPane.add(lab2,3,0);
        gridPane.add(lab3,0,1);
        gridPane.add(lab4,0,3);
        gridPane.add(lab5,4,1);
        gridPane.add(lab6,4,3);
        gridPane.add(circle,1,1,3,3);
        Scene scene=new Scene(gridPane,800,600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

