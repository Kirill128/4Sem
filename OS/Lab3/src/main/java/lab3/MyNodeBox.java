package lab3;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class MyNodeBox {


    public MyNodeBox(Label nameLabel, Label resultLabel, TextField textField, Button goToCriticalPlaceButton, Button inputButton, VBox vBox, int row, int column) {
        this.nameLabel = nameLabel;
        this.resultLabel = resultLabel;
        this.textField = textField;
        this.goToCriticalPlaceButton = goToCriticalPlaceButton;
        this.inputButton = inputButton;
        this.vBox = vBox;
        this.startRow = row;
        this.startColumn = column;
    }

    private Label nameLabel;
    private Label resultLabel;
    private TextField textField;
    private Button goToCriticalPlaceButton;
    private Button inputButton;
    private VBox vBox;
    private int startRow;
    private int startColumn;

    private String resultString;
    private String textInputString;


    public String getResultString() {
        return resultString;
    }

    public void setResultString(String resultString) {
        this.resultString = resultString;
    }

    public String getTextInputString() {
        return textInputString;
    }

    public void setTextInputString(String textInputString) {
        this.textInputString = textInputString;
    }


    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getStartColumn() {
        return startColumn;
    }

    public void setStartColumn(int startColumn) {
        this.startColumn = startColumn;
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

