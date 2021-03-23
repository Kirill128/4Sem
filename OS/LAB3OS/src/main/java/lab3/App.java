package lab3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;



public class App extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        Label name1=new Label("Task 1");
        Label result1=new Label("Result: ");
        TextField textField1=new TextField();
        textField1.setMaxWidth(100);
        Button btn1 = new Button("Click");
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        VBox vBox1=new VBox(10,name1,textField1,result1);
        vBox1.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        Label name2=new Label("Task 2");
        TextField textField2=new TextField();
        Label result2=new Label("Result: ");
        textField2.setMaxWidth(100);
        VBox vBox2=new VBox(10,name2,textField2,result2);
        vBox2.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");


        GridPane group=new GridPane();
        group.add(vBox1,0,0);
        group.add(vBox2,0,1);

        primaryStage.setScene(new Scene(group, 800, 600));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
