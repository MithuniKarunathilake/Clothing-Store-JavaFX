package controller.item;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import entity.Item;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemController implements ItemService {
    private static ItemController instance;

    private ItemController(){}

    public static ItemController getInstance(){
        return instance == null ?instance = new ItemController() : instance;
    }
    @Override
    public boolean addItem(Item item) {
        String SQL = "INSERT INTO item VALUES(?,?,?,?,?)";
        try {
           CrudUtil.execute(SQL,
                   item.getItemCode(),
                   item.getDescription() ,
                   item.getPackSize(),
                   item.getUnitPrice(),
                   item.getQtyOnHand()
           );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean deleteItem(String itemCode) {
        String SQL = "DELETE FROM item WHERE ItemCode='" + itemCode + "'";
        try {
            CrudUtil.execute(SQL);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public ObservableList<Item> getAll() {
        ObservableList<Item> itemObservableList = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM item";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL);
            while (resultSet.next()){
                itemObservableList.add(new Item(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getInt(5)
                ));
            }
            return itemObservableList;
            } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public boolean updateItem(Item item) {
        String SQL = "UPDATE item SET Description=?, PackSize=?, UnitPrice=?, QtyOnHand=? WHERE ItemCode=?";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1, item.getDescription());
            psTm.setObject(2, item.getPackSize());
            psTm.setDouble(3, item.getUnitPrice());
            psTm.setObject(4, item.getQtyOnHand());
            psTm.setObject(5, item.getItemCode());

            return psTm.executeUpdate() > 0;
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        return false;

    }

    @Override
    public Item searchItem(String itemCode) {
        String SQL = "SELECT * FROM item WHERE ItemCode='"+itemCode+"'";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                return new Item(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getInt(5)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ObservableList<String> getItemCodes() {
        ObservableList<String> itemCodes = FXCollections.observableArrayList();
        ObservableList<Item> itemObservableList = getAll();
        itemObservableList.forEach(item -> {
            itemCodes.add(item.getItemCode());
        });
        return itemCodes;
    }
}

