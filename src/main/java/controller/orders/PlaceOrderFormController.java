package controller.orders;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.customer.CustomerController;
import controller.item.ItemController;
import dto.CartTM;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import entity.*;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PlaceOrderFormController implements Initializable {

    public JFXTextField txtOrderId;
    public Label lblNetTotal;
    @FXML
    private JFXComboBox<String> cmbCustomerId;

    @FXML
    private JFXComboBox<String> cmbItemCode;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblTime;

    @FXML
    private TableView<CartTM> tblOrders;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtStock;

    @FXML
    private JFXTextField txtUnitPrice;

    ObservableList<CartTM> cartTMS = FXCollections.observableArrayList();
    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        String itemCode = cmbItemCode.getValue();
        String description = txtDescription.getText();
        Integer qty = Integer.parseInt(txtQty.getText());
        Double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        Double total = unitPrice*qty;

        cartTMS.add(new CartTM(itemCode,description,qty,unitPrice,total));
        tblOrders.setItems(cartTMS);
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        String orderId = txtOrderId.getText();
        LocalDate orderDate = LocalDate.now();
        String customerId = cmbCustomerId.getValue();

        List<OrderDetails> orderDetails = new ArrayList<>();

        cartTMS.forEach(obj ->{
            orderDetails.add(new OrderDetails(orderId,obj.getItemCode(),obj.getQty(),0));
        });

        //Orders orders = new Orders(orderId,orderDate,customerId,orderDetails);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDateAndTime();
        loadCustomerIds();
        loadItemCodes();

        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observableValue, s, newValue) -> {
            if (newValue != null) {
                searchCustomer(newValue);
            }
        });

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observableValue, s, newValue) -> {
            if (newValue != null) {
                searchItem(newValue);
            }
        });
    }

    private void loadDateAndTime(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateNow = simpleDateFormat.format(date);

        lblDate.setText(dateNow);

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime now = LocalTime.now();
            lblTime.setText(now.getHour() + ":" + now.getMinute() + ":" + now.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void loadCustomerIds(){
        cmbCustomerId.setItems(CustomerController.getInstance().getCustomerIds());
    }

    private void searchCustomer(String id){
        Customer customer = CustomerController.getInstance().searchCustomer(id);
        txtCustomerName.setText(customer.getName());
        txtAddress.setText(customer.getAddress());
    }

    private void loadItemCodes(){
        cmbItemCode.setItems(ItemController.getInstance().getItemCodes());
    }

    private void searchItem(String itemCode){
        Item item = ItemController.getInstance().searchItem(itemCode);
        txtDescription.setText(item.getDescription());
        txtStock.setText(String.valueOf(item.getQtyOnHand()));
        txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnEditOnAction(ActionEvent actionEvent) {
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }
}
