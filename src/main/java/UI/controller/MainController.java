package UI.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;

/**
 * Created by tanzhenyu on 2017/6/14.
 */
public class MainController {
    @FXML
    private ChoiceBox choiceBox;

    @FXML
    public void handlerBtnClick(ActionEvent event) {
        Button btnSource = (Button) event.getSource();
        btnSource.setText("I am clicked!");
//        choiceBox.setItems(FXCollections.observableArrayList("1","2","3"));
    }
}
