package lk.ijse.POSBackend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lk.ijse.POSBackend.dto.OrderDetailDto;
import lk.ijse.POSBackend.entity.OrderDetailEntity;
import lk.ijse.POSBackend.repository.ItemRepository;
import lk.ijse.POSBackend.repository.OrderDetailRepository;
import lk.ijse.POSBackend.repository.OrderRepository;
import lk.ijse.POSBackend.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @Override
    public OrderDetailEntity createOrderDetail(OrderDetailDto orderDetailDto) {
        OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
        orderDetailEntity.setOrderEntity(orderRepository.findById(orderDetailDto.getOrderId()).orElse(null));
        orderDetailEntity.setItemEntity(itemRepository.findById(orderDetailDto.getItemId()).orElse(null));
        orderDetailEntity.setPrice(orderDetailDto.getPrice());
        orderDetailEntity.setQuantity(orderDetailDto.getQuantity());
        orderDetailEntity.setDiscount(orderDetailDto.getDiscount());
        return orderDetailRepository.save(orderDetailEntity);
    }

    @Override
    public List<OrderDetailDto> findOrderDetailsByOrder(String id) {
        List<OrderDetailDto> orderDetailDtos = new ArrayList<>();
        List<OrderDetailEntity> orderDetailEntities = orderDetailRepository.findByOrder(orderRepository.findById(id).orElse(null));
        for (OrderDetailEntity orderDetailEntity : orderDetailEntities) {
            OrderDetailDto orderDetailDto = new OrderDetailDto();
            orderDetailDto.setOrderId(orderDetailEntity.getOrderEntity().getId());
            orderDetailDto.setItemId(orderDetailEntity.getItemEntity().getId());
            orderDetailDto.setPrice(orderDetailEntity.getPrice());
            orderDetailDto.setQuantity(orderDetailEntity.getQuantity());
            orderDetailDto.setDiscount(orderDetailEntity.getDiscount());
            orderDetailDtos.add(orderDetailDto);
        }
        return orderDetailDtos;
    }
 
}
