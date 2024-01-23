package lk.ijse.POSBackend.dto;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    private String id;
    private Date date;
    private List<OrderDetailDto> orderDetailDtos;
    private String customerId;
}
