package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    public void btnOrdersOnAction(ActionEvent actionEvent) {
        try {
            Stage stage  = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/orders_form.fxml"))));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void btnItemOnAction(ActionEvent actionEvent) {
        try {
            Stage stage  = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/item_form.fxml"))));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void btnEmployeeOnAction(ActionEvent actionEvent) {
        try {
            Stage stage  = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/customer_form.fxml"))));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void btnSupplierOnAction(ActionEvent actionEvent) {
        try {
            Stage stage  = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/sample_form.fxml"))));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void btnOrderDetailsOnAction(ActionEvent actionEvent) {
        try {
            Stage stage  = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/place_order_form.fxml"))));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void btnReportsOnAction(ActionEvent actionEvent) {
        try {
            Stage stage  = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/sample_form.fxml"))));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
