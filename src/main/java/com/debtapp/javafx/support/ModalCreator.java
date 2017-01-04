package com.debtapp.javafx.support;

import com.debtapp.javafx.controllers.ModalController;
import com.debtapp.javafx.controllers.ModalControllerError;
import com.debtapp.javafx.controllers.ModalInfoController;
import com.debtapp.javafx.objects.Debtor;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Arizel on 27.12.2016.
 */
public class ModalCreator {
    private static ModalCreator instance;


    private ModalCreator() {

    }

    public static ModalCreator getInstance() {
        if (instance == null) {
            return instance = new ModalCreator();
        }
        return instance;
    }

    public void createErrorModal(ActionEvent actionEvent, String fxmlPath, String error) {
        try {
            ModalControllerError.setInfo(error);
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            stage.setTitle("Ошибка");
            stage.setMinHeight(100);
            stage.setMinWidth(250);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createEditingModal(ActionEvent actionEvent, String fxmlPath, Debtor debtor) {
        try {
            ModalController.initDebtor(debtor);
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            stage.setTitle("Редактирование Записи");
            stage.setMinHeight(190);
            stage.setMinWidth(350);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createAddModal(ActionEvent actionEvent, String fxmlPath) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            stage.setTitle("Добавление Записи");
            stage.setMinHeight(190);
            stage.setMinWidth(350);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createInfoModal(ActionEvent actionEvent, String fxmlPath, String info) {
        try {
            ModalInfoController.setInfo(info);
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            stage.setTitle("Отчет");
            stage.setMinHeight(100);
            stage.setMinWidth(250);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
