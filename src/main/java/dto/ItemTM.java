package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemTM {
    private String itemCode;
    private String description;
    private String packSize;
    private Double unitPrice;
    private Integer  qtyOnHand;
}
