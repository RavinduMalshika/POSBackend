package lk.ijse.POSBackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lk.ijse.POSBackend.dto.OrderDetailDto;
import lk.ijse.POSBackend.entity.OrderDetailEntity;
import lk.ijse.POSBackend.service.OrderDetailService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Order Detail", description = "Order Detail Management APIs")
@RestController
@CrossOrigin(origins = "*")
public class OrderDetailController {
    @Autowired
    OrderDetailService orderDetailService;

    @Operation(
        summary = "Create an order detail",
        description = "Save a new order detail to the database",
        tags = {"POST"}
    )
    @PostMapping("/order-detail")
    public ResponseEntity<OrderDetailEntity> createOrderDetailEntity(@RequestBody OrderDetailDto orderDetailDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDetailService.createOrderDetail(orderDetailDto));
    }
    
    @Operation(
        summary = "Retrieve all order details of an order",
        description = "Get a list of order detail DTOs of a specific order by specifying the order",
        tags = {"GET"}
    )
    @GetMapping("/order-detail/order/{id}")
    public ResponseEntity<List<OrderDetailDto>> getOrderDetailsByOrder(@PathVariable String id) {
        return ResponseEntity.ok().body(orderDetailService.findOrderDetailsByOrder(id));
    }
    
}
