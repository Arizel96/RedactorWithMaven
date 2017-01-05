package com.debtapp.javafx.controllers;

import com.debtapp.javafx.interfaces.impl.DebtorBook;
import com.debtapp.javafx.objects.Debtor;
import com.debtapp.javafx.support.ModalCreator;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    public static final String ERROR_PATH = "/fxml/modal_error.fxml";
    public static final String MODAL_PATH = "/fxml/modal.fxml";
    public static final String INFO_PATH = "/fxml/modal_info.fxml";
    public static final ModalCreator modalCreator = ModalCreator.getInstance() ;

    private static DebtorBook debtorBook = new DebtorBook();

    @FXML Button btnSearch;
    @FXML Button btnAdd;
    @FXML Button btnDelete;
    @FXML Button btnUpdate;
    @FXML TextField fieldSearch;
    @FXML TableView tableDebtorBook;
    @FXML TableColumn<Debtor, String> columnName;
    @FXML TableColumn<Debtor, String> columnPhone;
    @FXML TableColumn<Debtor, String> columnAddress;
    @FXML TableColumn<Debtor, String> columnId;
    @FXML TableColumn<Debtor, String> columnIp;
    @FXML Label lblCount;

    private Button clickedButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableDebtorBook.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        columnName.setCellValueFactory(new PropertyValueFactory<Debtor, String>("name"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Debtor, String>("phone"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<Debtor, String>("address"));
        columnId.setCellValueFactory(new PropertyValueFactory<Debtor, String>("vkId"));
        columnIp.setCellValueFactory(new PropertyValueFactory<Debtor, String>("ip"));
        debtorBook.fillData();
        tableDebtorBook.setItems(debtorBook.getDebtors());
        debtorBook.getDebtors().addListener(new ListChangeListener<Debtor>() {
            @Override
            public void onChanged(Change<? extends Debtor> c) {
                updateCountLabel();
            }
        });
        updateCountLabel();
    }

    public void buttonPressed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        // если нажата не кнопка - выходим из метода
        if (!(source instanceof Button)) {
            return;
        }

        clickedButton = (Button) actionEvent.getSource();

        switch (clickedButton.getId()) {
            case "btnSearch" :
                if (fieldSearch.getText().isEmpty()) {
                    modalCreator.createErrorModal(actionEvent,ERROR_PATH,"Пустой поиск!");
                } else {
                    lookFor();
                }
                break;
            case "btnAdd" :
                modalCreator.createAddModal(actionEvent,MODAL_PATH);
                break;
            case "btnDelete" :
                ObservableList<Debtor> selectedDebtors = tableDebtorBook.getSelectionModel().getSelectedItems();
                String text;
                if (selectedDebtors == null) {
                    text = "Выберите должника(ов)!";
                }else if (selectedDebtors.size() == 1) {
                    text = "Должник успешно удален!";
                }else {
                    text = "Должники успешно удалены!";
                }
                for (Debtor d : selectedDebtors) {
                    debtorBook.delete(d);
                }
                modalCreator.createInfoModal(actionEvent,INFO_PATH, text);
                break;
            case "btnUpdate" :
                if (tableDebtorBook.getSelectionModel().getSelectedItems().size() == 1) {
                   Debtor selectedDebtor = (Debtor) tableDebtorBook.getSelectionModel().getSelectedItem();
                    modalCreator.createEditingModal(actionEvent, MODAL_PATH, selectedDebtor);
                } else if (tableDebtorBook.getSelectionModel().getSelectedItem() == null) {
                    modalCreator.createErrorModal(actionEvent, ERROR_PATH,"Выберите строку для изменения!" );
                }else {
                    modalCreator.createErrorModal(actionEvent, ERROR_PATH,"Выберите одну строку для изменения!" );
                }
                break;
        }
    }

    public static void addToDebtorBook(Debtor debtor) {
        debtorBook.add(debtor);
    }

    public static void removeFromDebtorBook(Debtor debtor) {
        debtorBook.delete(debtor);
    }

    public static void updateDebtorBook(Debtor debtor) {
        debtorBook.update(debtor);
    }

    private void lookFor() {

    }

    private void updateCountLabel() {
        lblCount.setText("Количевство записей : " + debtorBook.getDebtors().size());
    }
}
