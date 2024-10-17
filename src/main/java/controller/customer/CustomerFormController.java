package controller.customer;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import entity.Customer;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbTitle;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPostalCode;

    @FXML
    private TableColumn<?, ?> colProvince;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private DatePicker dateDOB;

    @FXML
    private TableView<Customer> tblCustomers;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtCity;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPostalCode;

    @FXML
    private JFXTextField txtProvince;

    @FXML
    private JFXTextField txtSalary;

    CustomerService service = CustomerController.getInstance();

    @FXML
    void btnAddOnAction(ActionEvent event) {
        Customer customer = new Customer(
                txtAddress.getText(),
                cmbTitle.getValue(),
                txtName.getText(),
                dateDOB.getValue(),
                Double.parseDouble(txtSalary.getText()),
                txtAddress.getText(),
                txtCity.getText(),
                txtProvince.getText(),
                txtPostalCode.getText()
        );

        if (service.addCustomer(customer)){
            new Alert(Alert.AlertType.INFORMATION,"Customer Added!").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Customer Not Added :(").show();
        }
        loadTable();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if(service.deleteCustomer(txtId.getText())){
            new Alert(Alert.AlertType.INFORMATION,"Customer Deleted!").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Customer not Deleted!").show();
        }

        loadTable();
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadTable();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        Customer customer = service.searchCustomer(txtId.getText());

        if (customer!=null){
            setValues(customer);
            loadTable();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Customer customer = new Customer(
                txtAddress.getText(),
                cmbTitle.getValue(),
                txtName.getText(),
                dateDOB.getValue(),
                Double.parseDouble(txtSalary.getText()),
                txtAddress.getText(),
                txtCity.getText(),
                txtProvince.getText(),
                txtPostalCode.getText()
        );
        if (service.updateCustomer(customer)){
            new Alert(Alert.AlertType.INFORMATION,"Customer Updated!").show();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Customer Not Updated :(").show();
        }

        loadTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> titles = FXCollections.observableArrayList();
        titles.add("Mr");
        titles.add("Miss");
        titles.add("Ms");
        cmbTitle.setItems(titles);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        loadTable();

        tblCustomers.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if(newValue != null){
                setValues(newValue);
            }
        }));
    }

    private void loadTable(){
        ObservableList<Customer> all = service.getAll();

        tblCustomers.setItems(all);
    }

    private void setValues(Customer newValue) {
        txtId.setText(newValue.getId());
        cmbTitle.setValue(newValue.getTitle());
        txtName.setText(newValue.getName());
        dateDOB.setValue(newValue.getDOB());
        txtSalary.setText(String.valueOf(newValue.getSalary()));
        txtAddress.setText(newValue.getAddress());
        txtCity.setText(newValue.getCity());
        txtProvince.setText(newValue.getProvince());
        txtPostalCode.setText(newValue.getPostalCode());
    }

}
