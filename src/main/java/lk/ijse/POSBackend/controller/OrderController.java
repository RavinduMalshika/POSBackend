package lk.ijse.POSBackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lk.ijse.POSBackend.dto.OrderDto;
import lk.ijse.POSBackend.entity.OrderEntity;
import lk.ijse.POSBackend.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Order", description = "Order Management APIs")
@RestController
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Operation(
        summary = "Generate an ID for an order",
        description = "Get the next available ID for a new order",
        tags = {"GET"}
    )
    @GetMapping("/order/generateId")
    public ResponseEntity<String> generateItemId() {
        return ResponseEntity.ok().body(orderService.generateId());
    }

    @Operation(
        summary = "Create an order",
        description = "Save a new order to the database",
        tags = {"POST"}
    )
    @PostMapping("/order")
    public ResponseEntity<OrderEntity> createOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderDto));
    }
    
    @Operation(
        summary = "Retrieve all orders by a customer",
        description = "Get a list of order DTOs placed by a customer by specifying the customer",
        tags = {"GET"}
    )
    @GetMapping("/order/{id}")
    public ResponseEntity<List<OrderDto>> getOrdersByCustomer(@PathVariable String id) {
        return ResponseEntity.ok().body(orderService.findOrdersByCustomer(id));
    }
    
}
