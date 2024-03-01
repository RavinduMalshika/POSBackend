package lk.ijse.POSBackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockDto {
    private String id;
    private String itemId;
    private Integer batch;
    private Double quantity;
}
