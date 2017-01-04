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
public class ModalInfoController implements Initializable{
    private Button clickedButton;
    private Stage stage;
    private static String info = "";

    @FXML Label lblInfo;

    public void setClose(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        // если нажата не кнопка - выходим из метода
        if (!(source instanceof Button)) {
            return;
        }

        clickedButton = (Button)actionEvent.getSource();
        if (clickedButton.getId().equals("btnOkey")) {
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    public static void setInfo(String info) {
        ModalInfoController.info = info;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblInfo.setText(info);
    }
}
