package controller.orders;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import entity.Item;
import entity.Orders;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class OrdersFormController implements Initializable {

    @FXML
    public TableView<Orders> tblOrders;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colOrderDate;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableView<?> tblItems;

    @FXML
    private JFXTextField txtCustomerId;

    @FXML
    private JFXTextField txtOrderDate;

    @FXML
    private JFXTextField txtOrderId;

    OrdersService service =new OrdersController();

    @FXML
    void btnAddOnAction(ActionEvent event) {
        Orders orders = new Orders(
                txtOrderId.getText(),
                LocalDate.parse(txtOrderDate.getText()),
                txtCustomerId.getText()
        );

        if (service.addOrders(orders)){
            new Alert(Alert.AlertType.INFORMATION,"Order Added!").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Order Not Added :(").show();
        }

        loadTable();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if(service.deleteOrders(txtOrderId.getText())){
            new Alert(Alert.AlertType.INFORMATION,"Order Deleted!").show();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Order not Deleted!").show();
        }

        loadTable();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        Orders orders = service.searchOrders(txtOrderId.getText());
        if(orders!=null){
            setValues(orders);
            loadTable();
        }else {
            System.out.println("New value is null");
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Orders orders = new Orders(
                txtOrderId.getText(),
                LocalDate.parse(txtOrderDate.getText()),
                txtCustomerId.getText()
        );
        ;
        if (service.updateOrders(orders)){
            new Alert(Alert.AlertType.INFORMATION,"Order Updated!").show();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Order Not Updated :(").show();
        }

        loadTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("custId"));

        loadTable();

        tblOrders.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if(newValue != null){
                setValues(newValue);
            }
        }));
    }

    private void loadTable(){
        ObservableList<Orders> all = service.getAll();

        tblOrders.setItems(all);
    }

    private void setValues(Orders newValue) {
        txtOrderId.setText(newValue.getOrderId());
        txtOrderDate.setText(String.valueOf(newValue.getOrderDate()));
        txtCustomerId.setText(newValue.getCustId());
    }
}
