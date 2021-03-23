package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Rectangle task1Rect=new Rectangle(150,100,100,100);

        Rectangle task2Rect=new Rectangle(150,400,100,100);

        Group group=new Group(task1Rect,task2Rect);
//        group.getChildren().add();
        primaryStage.setScene(new Scene(group, 800, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
