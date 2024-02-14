package lk.ijse.POSBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.POSBackend.dto.CustomerDto;
import lk.ijse.POSBackend.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@CrossOrigin(origins = "*")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/customer/token")
    public ResponseEntity<CustomerDto> getCustomerFromToken(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findCustomerByToken(request));
    }
}
