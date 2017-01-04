package com.debtapp.javafx.controllers;

import com.debtapp.javafx.objects.Debtor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Arizel on 25.12.2016.
 */
public class ModalController implements Initializable{
    @FXML Button btnOk;
    @FXML Button btnCancel;
    @FXML TextField fieldName;
    @FXML TextField fieldPhone;
    @FXML TextField fieldAddress;
    @FXML TextField fieldId;
    @FXML TextField fieldIp;

    private static Debtor debtor;
    private Button clickedButton;
    private Stage stage;
    private Debtor interDebt;

    public void buttonPressed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        // если нажата не кнопка - выходим из метода
        if (!(source instanceof Button)) {
            return;
        }

        clickedButton = (Button) actionEvent.getSource();
        switch (clickedButton.getId()) {
            case "btnOk" :
                if (isEmpty()) {
                    Controller.modalCreator.createErrorModal(actionEvent, Controller.ERROR_PATH, "Заполните все пустые поля!");
                } else {
                    if (interDebt != null) {
                        interDebt.setName(fieldName.getText());
                        interDebt.setPhone(fieldPhone.getText());
                        interDebt.setAddress(fieldAddress.getText());
                        interDebt.setVkId(fieldId.getText());
                        interDebt.setIp(fieldIp.getText());
                        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        stage.close();
                    }else {
                        Controller.addToDebtorBook(new Debtor(fieldName.getText(), fieldPhone.getText(), fieldAddress.getText(), fieldId.getText(), fieldIp.getText()));
                        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        stage.close();
                    }
                }
                break;
            case "btnCancel" :
                stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.close();
                break;
        }
    }

    private boolean isEmpty() {
        if (fieldAddress.getText().isEmpty()) return true;
        if (fieldName.getText().isEmpty()) return true;
        if (fieldPhone.getText().isEmpty()) return true;
        if (fieldId.getText().isEmpty()) return true;
        if (fieldIp.getText().isEmpty()) return true;
        return false;
    }

    public void setFields() {
        if (debtor == null) return;
        fieldName.setText(debtor.getName());
        fieldPhone.setText(debtor.getPhone());
        fieldAddress.setText(debtor.getAddress());
        fieldId.setText(debtor.getVkId());
        fieldIp.setText(debtor.getIp());
        interDebt = debtor;
        debtor = null;
    }

    public static void initDebtor(Debtor debtor) {
        ModalController.debtor = debtor;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setFields();
    }
}
