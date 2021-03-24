package lab3;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class MyNodeBox {


    public MyNodeBox(Label nameLabel, Label resultLabel, TextField textField, Button goToCriticalPlaceButton, Button inputButton, VBox vBox, int row, int column, ITask task) {
        this.nameLabel = nameLabel;
        this.resultLabel = resultLabel;
        this.textField = textField;
        this.goToCriticalPlaceButton = goToCriticalPlaceButton;
        this.inputButton = inputButton;
        this.vBox = vBox;
        this.row = row;
        this.column = column;
        this.task = task;
    }

    private Label nameLabel;
    private Label resultLabel;
    private TextField textField;
    private Button goToCriticalPlaceButton;
    private Button inputButton;
    private VBox vBox;
    private int row;
    private int column;
    private ITask task;


    public ITask getTask() {
        return task;
    }

    public void setTask(ITask task) {
        this.task = task;
    }

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

