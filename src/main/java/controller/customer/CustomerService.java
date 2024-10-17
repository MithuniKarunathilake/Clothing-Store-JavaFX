package controller.customer;

import javafx.collections.ObservableList;
import entity.Customer;
public interface CustomerService {
    ObservableList<Customer> getAll();
    boolean addCustomer(Customer customer);
    boolean deleteCustomer(String id);
    boolean updateCustomer(Customer customer);
    Customer searchCustomer(String id);
    ObservableList<String> getCustomerIds();
}
