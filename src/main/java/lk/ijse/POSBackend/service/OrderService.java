package lk.ijse.POSBackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lk.ijse.POSBackend.dto.OrderDto;
import lk.ijse.POSBackend.entity.OrderEntity;

@Service
public interface OrderService {
    public OrderEntity createOrder(OrderDto orderDto);

    public OrderEntity updateOrder(OrderDto orderDto, String id);

    public OrderDto findOrderById(String id);

    public List<OrderDto> findOrdersByCustomer(String id);

    public List<OrderDto> findAllOrders();

    public String deleteOrder(String id);

    public String generateId();
}
