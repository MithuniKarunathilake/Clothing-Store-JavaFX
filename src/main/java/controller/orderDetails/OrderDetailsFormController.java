package controller.orderDetails;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import entity.OrderDetails;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderDetailsFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colDiscount;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colOrderQty;

    @FXML
    private TableView<OrderDetails> tblOrderDetails;

    @FXML
    private JFXTextField txtDiscount;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtOrderId;

    @FXML
    private JFXTextField txtOrderQty;

    OrderDetailsService service =new OrderDetailsController();

    @FXML
    void btnAddOnAction(ActionEvent event) {
        OrderDetails orderDetails = new OrderDetails(
                txtOrderId.getText(),
                txtItemCode.getText(),
                Integer.parseInt(txtOrderQty.getText()),
                Integer.parseInt(txtDiscount.getText())
        );

        if (service.addOrderDetails(orderDetails)){
            new Alert(Alert.AlertType.INFORMATION,"Order Details Added!").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Order Details Not Added :(").show();
        }
        loadTable();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if(service.deleteOrderDetails(txtOrderId.getText())){
            new Alert(Alert.AlertType.INFORMATION,"Order Details Deleted!").show();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Order Details not Deleted!").show();
        }

        loadTable();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        OrderDetails orderDetails = service.searchOrderDetails(txtOrderId.getText());

        if (orderDetails!=null){
            setValues(orderDetails);
            loadTable();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        OrderDetails orderDetails = new OrderDetails(
                txtOrderId.getText(),
                txtItemCode.getText(),
                Integer.parseInt(txtOrderQty.getText()),
                Integer.parseInt(txtDiscount.getText())
        );
        if (service.updateOrderDetails(orderDetails)){
            new Alert(Alert.AlertType.INFORMATION,"Order Details Updated!").show();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Order Details Not Updated :(").show();
        }

        loadTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colOrderQty.setCellValueFactory(new PropertyValueFactory<>("orderQty"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));

        loadTable();

        tblOrderDetails.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if(newValue != null){
                setValues(newValue);
            }
        }));
    }

    private void loadTable(){
        ObservableList<OrderDetails> all = service.getAll();

        tblOrderDetails.setItems(all);
    }

    private void setValues(OrderDetails newValue) {
        txtOrderId.setText(newValue.getOrderId());
        txtItemCode.setText(newValue.getItemCode());
        txtOrderQty.setText(String.valueOf(newValue.getOrderQty()));
        txtDiscount.setText(String.valueOf(newValue.getDiscount()));
    }
}
