package lab.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import lab.algs.BiasAlg;
import lab.process.Process;

public class Controller {
    @FXML
    private VBox operationMemory;
    @FXML
    private TextField inputSizeForAdd;
    @FXML
    private TextField inputIdToDelete;
    @FXML
    private TextField inputAskFrom;
    @FXML
    private TextField inputAskTo;
    @FXML
    private TextField inputProcessIdFromTo;

    @FXML
    private Button btnAddProcess;
    @FXML
    private Button btnDeleteProcess;
    @FXML
    private Button btnAsk;

    private BiasAlg biasAlg;


    @FXML
    public void addProcess(){
        System.out.println("add");
        this.biasAlg.addProcess(new Process(Integer.parseInt(this.inputSizeForAdd.getText())){});
    }

    @FXML
    public void deleteProcess(){
        System.out.println("delete");
        this.biasAlg.deleteProcess(Integer.parseInt(this.inputIdToDelete.getText()));
    }

    @FXML
    public void ask(){
        System.out.println("ask");
        this.biasAlg.askOneCellFromAnother(Integer.parseInt(this.inputProcessIdFromTo.getText()),
                Integer.parseInt(this.inputAskFrom.getText()),
                Integer.parseInt(this.inputAskTo.getText()));
    }

    @FXML
    public void clear(){
        this.biasAlg.clear(Integer.parseInt(this.inputProcessIdFromTo.getText()),
                Integer.parseInt(this.inputAskFrom.getText()),
                Integer.parseInt(this.inputAskTo.getText()));
    }

    public BiasAlg getBiasAlg() {
        return biasAlg;
    }

    public void setBiasAlg(BiasAlg biasAlg) {
        this.biasAlg = biasAlg;
    }

    public VBox getOperationMemory() {
        return operationMemory;
    }

    public void setOperationMemory(VBox operationMemory) {
        this.operationMemory = operationMemory;
    }

    public TextField getInputSizeForAdd() {
        return inputSizeForAdd;
    }

    public void setInputSizeForAdd(TextField inputSizeForAdd) {
        this.inputSizeForAdd = inputSizeForAdd;
    }

    public TextField getInputIdToDelete() {
        return inputIdToDelete;
    }

    public void setInputIdToDelete(TextField inputIdToDelete) {
        this.inputIdToDelete = inputIdToDelete;
    }

    public TextField getInputAskFrom() {
        return inputAskFrom;
    }

    public TextField getInputProcessIdFromTo() {
        return inputProcessIdFromTo;
    }

    public void setInputProcessIdFromTo(TextField inputProcessIdFromTo) {
        this.inputProcessIdFromTo = inputProcessIdFromTo;
    }

    public void setInputAskFrom(TextField inputAskFrom) {
        this.inputAskFrom = inputAskFrom;
    }

    public TextField getInputAskTo() {
        return inputAskTo;
    }

    public void setInputAskTo(TextField inputAskTo) {
        this.inputAskTo = inputAskTo;
    }

    public Button getBtnAddProcess() {
        return btnAddProcess;
    }

    public void setBtnAddProcess(Button btnAddProcess) {
        this.btnAddProcess = btnAddProcess;
    }

    public Button getBtnDeleteProcess() {
        return btnDeleteProcess;
    }

    public void setBtnDeleteProcess(Button btnDeleteProcess) {
        this.btnDeleteProcess = btnDeleteProcess;
    }

    public Button getBtnAsk() {
        return btnAsk;
    }

    public void setBtnAsk(Button btnAsk) {
        this.btnAsk = btnAsk;
    }
}
