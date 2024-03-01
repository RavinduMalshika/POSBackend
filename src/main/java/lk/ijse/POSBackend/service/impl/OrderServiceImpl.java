package lk.ijse.POSBackend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lk.ijse.POSBackend.dto.OrderDto;
import lk.ijse.POSBackend.entity.OrderEntity;
import lk.ijse.POSBackend.repository.CustomerRepository;
import lk.ijse.POSBackend.repository.ItemRepository;
import lk.ijse.POSBackend.repository.OrderDetailRepository;
import lk.ijse.POSBackend.repository.OrderRepository;
import lk.ijse.POSBackend.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ItemRepository itemRepository;

    @Override
    public OrderEntity createOrder(OrderDto orderDto) {
        if (orderRepository.findById(orderDto.getId()).orElse(null) == null) {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setId(orderDto.getId());
            orderEntity.setDate(orderDto.getDate());
            orderEntity.setCustomerEntity(customerRepository.findById(orderDto.getCustomerId()).orElse(null));
            return orderRepository.save(orderEntity);
        } else {
            return null;
        }
    }

    @Override
    public OrderEntity updateOrder(OrderDto orderDto, String id) {
        OrderEntity orderEntity = orderRepository.findById(id).orElse(null);

        if (orderEntity != null) {
            orderEntity.setId(orderDto.getId());
            orderEntity.setDate(orderDto.getDate());
            orderEntity.setCustomerEntity(customerRepository.findById(orderDto.getCustomerId()).orElse(null));

            return orderRepository.save(orderEntity);
        } else {
            return null;
        }
    }

    @Override
    public OrderDto findOrderById(String id) {
        OrderEntity orderEntity = orderRepository.findById(id).orElse(null);

        if (orderEntity != null) {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(orderEntity.getId());
            orderDto.setDate(orderEntity.getDate());
            orderDto.setCustomerId(orderEntity.getCustomerEntity().getId());

            return orderDto;
        } else {
            return null;
        }
    }

    @Override
    public List<OrderDto> findOrdersByCustomer(String id) {
        List<OrderDto> orderDtos = new ArrayList<>();
        List<OrderEntity> orderEntities = orderRepository.findByCustomer(customerRepository.findById(id).orElse(null));
        for (OrderEntity orderEntity : orderEntities) {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(orderEntity.getId());
            orderDto.setDate(orderEntity.getDate());
            orderDto.setCustomerId(orderEntity.getCustomerEntity().getId());
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }

    @Override
    public List<OrderDto> findAllOrders() {
        List<OrderDto> orderDtos = new ArrayList<>();
        List<OrderEntity> orderEntities = orderRepository.findAll();
        for (OrderEntity orderEntity : orderEntities) {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(orderEntity.getId());
            orderDto.setDate(orderEntity.getDate());
            orderDto.setCustomerId(orderEntity.getCustomerEntity().getId());

            orderDtos.add(orderDto);
        }
        return orderDtos;
    }

    @Override
    public String deleteOrder(String id) {
        OrderEntity orderEntity = orderRepository.findById(id).orElse(null);

        if (orderEntity != null) {
            orderRepository.delete(orderEntity);
            return "Deleted Successfully";
        } else {
            return "Failed to Delete";
        }
    }

    @Override
    public String generateId() {
        List<OrderEntity> orderEntities = orderRepository.findAll();

        if (orderEntities.size() > 0) {
            OrderEntity orderEntity = orderEntities.get(orderEntities.size() - 1);

            String lastId = orderEntity.getId().substring(3);
            Integer incrementedId = Integer.parseInt(lastId) + 1;
            String id = "ODR" + String.format("%04d", incrementedId);
            return id;
        } else {
            return "ODR0001";
        }
    }

}
