package controller.orders;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entity.Orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersController implements OrdersService{
    @Override
    public ObservableList<Orders> getAll() {
        String SQL = "SELECT * FROM orders";

        ObservableList<Orders> ordersObservableList = FXCollections.observableArrayList();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                ordersObservableList.add(new Orders(
                        resultSet.getString(1),
                        resultSet.getDate(2).toLocalDate(),
                        resultSet.getString(3)
                ));
            }
            return ordersObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addOrders(Orders orders) {
        String SQL = "INSERT INTO orders VALUES(?,?,?)";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setObject(1,orders.getOrderId());
            statement.setObject(2,orders.getOrderDate());
            statement.setObject(3,orders.getCustId());

            return statement.executeUpdate()> 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteOrders(String orderId) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            return connection.createStatement().executeUpdate("DELETE FROM orders WHERE OrderID='"+orderId+"' " )>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateOrders(Orders orders) {
        String SQL = "UPDATE orders SET OrderDate=?,CustID=? WHERE OrderID=?";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setObject(1,orders.getOrderDate());
            statement.setObject(2,orders.getCustId());
            statement.setObject(3,orders.getOrderId());

            return statement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Orders searchOrders(String orderId) {
        String SQL = "SELECT * FROM orders WHERE OrderID='"+orderId+"'";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                return new Orders(
                        resultSet.getString(1),
                        resultSet.getDate(2).toLocalDate(),
                        resultSet.getString(3)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
