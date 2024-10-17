package controller.orders;

import javafx.collections.ObservableList;
import entity.Orders;

public interface OrdersService {
    ObservableList<Orders> getAll();
    boolean addOrders(Orders orders);
    boolean deleteOrders(String orderId);
    boolean updateOrders(Orders orders);
    Orders searchOrders (String orderId);
}