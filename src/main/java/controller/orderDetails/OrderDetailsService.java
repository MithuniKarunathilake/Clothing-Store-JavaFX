package controller.orderDetails;

import javafx.collections.ObservableList;
import entity.OrderDetails;

public interface OrderDetailsService {
    ObservableList<OrderDetails> getAll();
    boolean addOrderDetails(OrderDetails orderDetails);
    boolean deleteOrderDetails(String orderId);
    boolean updateOrderDetails(OrderDetails orderDetails);
    OrderDetails searchOrderDetails(String orderId);
}
