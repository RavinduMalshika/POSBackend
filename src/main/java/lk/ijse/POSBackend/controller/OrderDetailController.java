package lk.ijse.POSBackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import lk.ijse.POSBackend.dto.OrderDetailDto;
import lk.ijse.POSBackend.entity.OrderDetailEntity;
import lk.ijse.POSBackend.service.OrderDetailService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@CrossOrigin(origins = "*")
public class OrderDetailController {
    @Autowired
    OrderDetailService orderDetailService;

    @PostMapping("/order-detail")
    public ResponseEntity<OrderDetailEntity> createOrderDetailEntity(@RequestBody OrderDetailDto orderDetailDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDetailService.createOrderDetail(orderDetailDto));
    }
    
    @GetMapping("/order-detail/order/{id}")
    public ResponseEntity<List<OrderDetailDto>> getMethodName(@PathVariable String id) {
        return ResponseEntity.ok().body(orderDetailService.findOrderDetailsByOrder(id));
    }
    
}
