package lk.ijse.POSBackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lk.ijse.POSBackend.dto.OrderDetailDto;
import lk.ijse.POSBackend.entity.OrderDetailEntity;

@Service
public interface OrderDetailService {
    public OrderDetailEntity createOrderDetail(OrderDetailDto orderDetailDto);
    
    public List<OrderDetailDto> findOrderDetailsByOrder(String id);
}
