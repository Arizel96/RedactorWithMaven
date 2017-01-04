package com.debtapp.javafx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Arizel on 27.12.2016.
 */
public class ModalControllerError implements Initializable{
    private Button clickedButton;
    private Stage stage;
    private static String info = "";

    @FXML Button btnOkeyError;
    @FXML Label lblError;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblError.setText(info);
    }

    public void errorClose(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        // если нажата не кнопка - выходим из метода
        if (!(source instanceof Button)) {
            return;
        }

        clickedButton = (Button) actionEvent.getSource();
        switch (clickedButton.getId()) {
            case "btnOkeyError" :
                stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.close();
                break;
            case "btnCancel" :
                stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.close();
                break;
        }
    }

    public static void setInfo(String info) {
        ModalControllerError.info = info;
    }
}
