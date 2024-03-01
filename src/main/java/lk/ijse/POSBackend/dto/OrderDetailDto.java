package lk.ijse.POSBackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailDto {
    private String orderId;
    private String itemId;
    private Double price;
    private Double quantity;
    private Double discount;
}
