package controller.customer;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CustomerController implements CustomerService{

    private static CustomerController instance;

    private CustomerController(){}

    public static CustomerController getInstance(){
        return instance == null ?instance = new CustomerController() : instance;
    }

    @Override
    public ObservableList<Customer> getAll() {
        String SQL = "SELECT * FROM customer";

        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                customerObservableList.add(new Customer(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        LocalDate.parse(resultSet.getString(4)),
                        resultSet.getDouble(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9)
                ));
            }
            return customerObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addCustomer(Customer customer) {
        String SQL = "INSERT INTO customer VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setObject(1,customer.getId());
            statement.setObject(2,customer.getTitle());
            statement.setObject(3,customer.getName());
            statement.setObject(4,customer.getDOB());
            statement.setObject(5,customer.getSalary());
            statement.setObject(6,customer.getAddress());
            statement.setObject(7,customer.getCity());
            statement.setObject(8,customer.getProvince());
            statement.setObject(9,customer.getPostalCode());

            return statement.executeUpdate()> 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteCustomer(String id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            return connection.createStatement().executeUpdate("DELETE FROM customer WHERE CustID='"+id+"' " )>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        String SQL = "UPDATE customer SET CustTitle=?,CustName=?,DOB=?, salary=?, CustAddress=?, City=?, Province=?, PostalCode=? WHERE CustID=?";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setObject(1,customer.getId());
            statement.setObject(2,customer.getTitle());
            statement.setObject(3,customer.getName());
            statement.setObject(4,customer.getDOB());
            statement.setObject(5,customer.getSalary());
            statement.setObject(6,customer.getAddress());
            statement.setObject(7,customer.getCity());
            statement.setObject(8,customer.getProvince());
            statement.setObject(9,customer.getPostalCode());


            return statement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer searchCustomer(String id) {

        String SQL = "SELECT * FROM customer WHERE CustID='"+id+"'";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                return new Customer(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        LocalDate.parse(resultSet.getString(4)),
                        resultSet.getDouble(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ObservableList<String> getCustomerIds() {
        ObservableList<String> customerIds = FXCollections.observableArrayList();
        ObservableList<Customer> customerObservableList = getAll();
        customerObservableList.forEach(customer -> {
            customerIds.add(customer.getId());
        });
        return customerIds;
    }
}
