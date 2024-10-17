package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class Orders {
    private String orderId;
    private LocalDate orderDate;
    private String custId;
    List<OrderDetails> orderDetails;

    public Orders(String orderId, LocalDate orderDate, String custId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.custId = custId;
    }
}