package lk.ijse.POSBackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lk.ijse.POSBackend.dto.OrderDto;
import lk.ijse.POSBackend.entity.OrderEntity;
import lk.ijse.POSBackend.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/order/generateId")
    public ResponseEntity<String> generateItemId() {
        return ResponseEntity.ok().body(orderService.generateId());
    }

    @PostMapping("/order")
    public ResponseEntity<OrderEntity> createOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderDto));
    }
    
    @GetMapping("/order/{id}")
    public ResponseEntity<List<OrderDto>> getOrdersByCustomer(@PathVariable String id) {
        return ResponseEntity.ok().body(orderService.findOrdersByCustomer(id));
    }
    
}
