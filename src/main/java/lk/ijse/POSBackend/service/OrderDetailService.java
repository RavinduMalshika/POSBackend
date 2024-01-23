package lk.ijse.POSBackend.service;

import java.util.List;

import lk.ijse.POSBackend.dto.OrderDetailDto;
import lk.ijse.POSBackend.entity.OrderDetailEntity;

public interface OrderDetailService {
    public OrderDetailEntity createOrderDetail(OrderDetailDto orderDetailDto);

    // public OrderDetailEntity updateOrderDetail(OrderDetailDto orderDetailDto, String id);

    // public OrderDetailDto findOrderDetailById(String id);

    // public List<OrderDetailDto> findAllOrderDetails();

    // public String deleteOrderDetail(String id);

    public List<OrderDetailDto> findOrderDetailsByOrder(String id);
}
