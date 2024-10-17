package controller.orderDetails;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entity.OrderDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailsController implements OrderDetailsService{
    @Override
    public ObservableList<OrderDetails> getAll() {
        String SQL = "SELECT * FROM orderdetail";

        ObservableList<OrderDetails> orderDetailsObservableList = FXCollections.observableArrayList();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                orderDetailsObservableList.add(new OrderDetails(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4)
                ));
            }
            return orderDetailsObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addOrderDetails(OrderDetails orderDetails) {
        String SQL = "INSERT INTO orderdetail VALUES(?,?,?,?)";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setObject(1,orderDetails.getOrderId());
            statement.setObject(2,orderDetails.getItemCode());
            statement.setObject(3,orderDetails.getOrderQty());
            statement.setObject(4,orderDetails.getDiscount());

            return statement.executeUpdate()> 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteOrderDetails(String orderId) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            return connection.createStatement().executeUpdate("DELETE FROM orderdetail WHERE OrderID='"+orderId+"' " )>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateOrderDetails(OrderDetails orderDetails) {
        String SQL = "UPDATE orderdetail SET ItemCode=?,OrderQTY=?,Discount=? WHERE OrderID=?";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setObject(1,orderDetails.getItemCode());
            statement.setObject(2,orderDetails.getOrderQty());
            statement.setObject(3,orderDetails.getDiscount());
            statement.setObject(3,orderDetails.getOrderId());

            return statement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OrderDetails searchOrderDetails(String orderId) {
        String SQL = "SELECT * FROM orderdetail WHERE OrderID='"+orderId+"'";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                return new OrderDetails(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
