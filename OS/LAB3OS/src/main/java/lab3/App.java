package lab3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;



public class App extends Application{
    public static final int CRITICAL_PLACE_ROW=1;
    public static final int CRITICAL_PLACE_COLUMN=2;
    private GridPane grid;
    private MyNodeBox taskBox1;
    private MyNodeBox taskBox2;

    @Override
    public void start(Stage primaryStage) throws Exception{
        int windowWidth=800;
        int windowHeight=600;

        this.grid=gridInit(800,600);

        this.taskBox1=makeTaskGroup("task 1",0,0);
        this.taskBox2=makeTaskGroup("task 2",2,0);
        grid.add(taskBox1.getvBox(),taskBox1.getColumn(),taskBox1.getRow());
        grid.add(taskBox2.getvBox(),taskBox2.getColumn(),taskBox2.getRow());


        primaryStage.setScene(new Scene(grid, windowWidth, windowHeight));
        primaryStage.show();

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

    public MyNodeBox makeTaskGroup(String name,int row,int column){

        Label nameLabel=new Label(name);
        Label result=new Label("Result: ");
        TextField textField=new TextField();
        textField.setMaxWidth(150);
        textField.setVisible(false);

        Button inputButton = new Button("Some input");
        Button goToCriticalPlaceButton = new Button("Go To Critical place");

        VBox vBox=new VBox(10,nameLabel,textField,goToCriticalPlaceButton,inputButton,result);

        goToCriticalPlaceButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textField.setVisible(true);
                goToCriticalPlaceButton.setVisible(false);
                inputButton.setVisible(true);
                grid.getChildren().remove(vBox);
                grid.add(vBox,CRITICAL_PLACE_COLUMN,CRITICAL_PLACE_ROW);
            }
        });

        inputButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textField.setVisible(false);
                goToCriticalPlaceButton.setVisible(true);
                inputButton.setVisible(false);
                grid.getChildren().remove(vBox);
                grid.add(vBox,column,row);
            }
        });
        inputButton.setVisible(false);

        vBox.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        return new MyNodeBox(
                nameLabel,
                result,
                textField,
                goToCriticalPlaceButton,
                inputButton,
                vBox,
                row,
                column
        );
    }

    public static void main(String[] args) {
        launch(args);
    }

    private class GoToCriticalPlaceHandler implements EventHandler{
        private TextField textField;
        private Button goToCriticalPlaceButton;
        private Button inputButton;

        public GoToCriticalPlaceHandler(TextField textField,Button goToCritPlace,Button inputButton){
            this.goToCriticalPlaceButton=goToCritPlace;
            this.textField=textField;
            this.inputButton=inputButton;
        }
        @Override
        public void handle(Event event) {

        }
    }
    private class InputHandler implements EventHandler{
        private TextField textField;
        private Button goToCriticalPlaceButton;
        private Button inputButton;

        public InputHandler(TextField textField,Button goToCritPlace,Button inputButton){
            this.goToCriticalPlaceButton=goToCritPlace;
            this.textField=textField;
            this.inputButton=inputButton;
        }
        @Override
        public void handle(Event event) {

        }
    }

    private class MyNodeBox{
        public MyNodeBox(Label nameLabel, Label resultLabel,
                         TextField textField, Button goToCriticalPlaceButton,
                         Button inputButton, VBox vBox, int row, int column) {
            this.nameLabel = nameLabel;
            this.resultLabel = resultLabel;
            this.textField = textField;
            this.goToCriticalPlaceButton = goToCriticalPlaceButton;
            this.inputButton = inputButton;
            this.vBox = vBox;
            this.row = row;
            this.column = column;
        }

        private Label nameLabel;
        private Label resultLabel;
        private TextField textField;
        private Button goToCriticalPlaceButton;
        private Button inputButton;
        private VBox vBox;
        private int row;
        private int column;


        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getColumn() {
            return column;
        }

        public void setColumn(int column) {
            this.column = column;
        }

        public Label getNameLabel() {
            return nameLabel;
        }

        public void setNameLabel(Label nameLabel) {
            this.nameLabel = nameLabel;
        }

        public Label getResultLabel() {
            return resultLabel;
        }

        public void setResultLabel(Label resultLabel) {
            this.resultLabel = resultLabel;
        }

        public TextField getTextField() {
            return textField;
        }

        public void setTextField(TextField textField) {
            this.textField = textField;
        }

        public Button getGoToCriticalPlaceButton() {
            return goToCriticalPlaceButton;
        }

        public void setGoToCriticalPlaceButton(Button goToCriticalPlaceButton) {
            this.goToCriticalPlaceButton = goToCriticalPlaceButton;
        }

        public Button getInputButton() {
            return inputButton;
        }

        public void setInputButton(Button inputButton) {
            this.inputButton = inputButton;
        }

        public VBox getvBox() {
            return vBox;
        }

        public void setvBox(VBox vBox) {
            this.vBox = vBox;
        }

    }
}
