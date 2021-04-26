package lab.monitor;


import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

public class Controller  {
    @FXML
    Rectangle philosopher1Place;
    @FXML
    Rectangle philosopher2Place;
    @FXML
    Rectangle philosopher3Place;
    @FXML
    Rectangle philosopher4Place;
    @FXML
    Rectangle philosopher5Place;
    @FXML
    Rectangle philosopher6Place;

    @FXML
    Label philosopher1EatCount;
    @FXML
    Label philosopher2EatCount;
    @FXML
    Label philosopher3EatCount;
    @FXML
    Label philosopher4EatCount;
    @FXML
    Label philosopher5EatCount;
    @FXML
    Label philosopher6EatCount;

    @FXML
    CheckBox fork1Place;
    @FXML
    CheckBox fork2Place;
    @FXML
    CheckBox fork3Place;
    @FXML
    CheckBox fork4Place;
    @FXML
    CheckBox fork5Place;
    @FXML
    CheckBox fork6Place;

    @FXML
    Label philosopher1State;
    @FXML
    Label philosopher2State;
    @FXML
    Label philosopher3State;
    @FXML
    Label philosopher4State;
    @FXML
    Label philosopher5State;
    @FXML
    Label philosopher6State;
}
