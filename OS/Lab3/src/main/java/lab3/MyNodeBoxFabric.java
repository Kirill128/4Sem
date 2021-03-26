package lab3;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class MyNodeBoxFabric {
    public static MyNodeBox makeMyNodeBox(String name,int row,int column){
        Label nameLabel=new Label(name);

        Label result=new Label();
        TextField textField=new TextField();

        textField.setMaxWidth(150);
        textField.setVisible(false);

        Button inputButton = new Button(" Input ");
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
                column
        );

        inputButton.setVisible(false);

        vBox.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        return myNodeBox;
    }
}
