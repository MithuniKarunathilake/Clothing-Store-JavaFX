package controller.item;

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

import java.net.URL;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {

    public TableView <Item> tblItems;
    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colPackSize;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colUnitPrize;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtPackSize;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtUnitPrize;

    ItemService service = ItemController.getInstance();

    @FXML
    void btnAddOnAction(ActionEvent event) {
        Item item = new Item(
                txtItemCode.getText(),
                txtDescription.getText(),
                txtPackSize.getText(),
                Double.parseDouble(txtUnitPrize.getText()),
                Integer.parseInt(txtQty.getText())
        );

        if(service.addItem(item)){
            new Alert(Alert.AlertType.INFORMATION,"Item Added!").show();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Item Not Added :(").show();
        }

        loadTable();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if(service.deleteItem(txtItemCode.getText())){
            new Alert(Alert.AlertType.INFORMATION,"Item Deleted!").show();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Item not Deleted!").show();
        }

        loadTable();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        Item item = service.searchItem(txtItemCode.getText());

        if (item!=null){
            setValues(item);
            loadTable();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Item item = new Item(
                txtItemCode.getText(),
                txtDescription.getText(),
                txtPackSize.getText(),
                Double.parseDouble(txtUnitPrize.getText()),
                Integer.parseInt(txtQty.getText())
        );

        if (service.updateItem(item)){
            new Alert(Alert.AlertType.INFORMATION,"Item Updated!").show();
            loadTable();
        }else {
            new Alert(Alert.AlertType.ERROR,"Item Not Updated!").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colUnitPrize.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));



        tblItems.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if(newValue != null){
                setValues(newValue);
            }

        }));
        loadTable();
    }

    private void loadTable() {
        ObservableList<Item> itemObservableList = service.getAll();
        tblItems.setItems(itemObservableList);

    }

    private void setValues(Item newValue) {
        txtItemCode.setText(newValue.getItemCode());
        txtDescription.setText(newValue.getDescription());
        txtPackSize.setText(newValue.getPackSize());
        txtUnitPrize.setText(String.valueOf(newValue.getUnitPrice()));
        txtQty.setText(String.valueOf(newValue.getQtyOnHand()));
    }
}

