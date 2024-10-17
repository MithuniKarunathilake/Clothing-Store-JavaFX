package entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@ToString
@Data
@Entity
@Table(name = "ordersDetails")
public class OrderDetails {
    private String orderId;
    private String itemCode;
    private Integer orderQty;
    private Integer discount;

    public OrderDetails(String orderId, String itemCode, Integer orderQty, Integer discount) {
        this.orderId = orderId;
        this.itemCode = itemCode;
        this.orderQty = orderQty;
        this.discount = discount;
    }
}
