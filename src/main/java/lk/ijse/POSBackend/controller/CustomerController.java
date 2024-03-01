package lk.ijse.POSBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.POSBackend.dto.CustomerDto;
import lk.ijse.POSBackend.entity.CustomerEntity;
import lk.ijse.POSBackend.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin(origins = "*")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/customer/token")
    public ResponseEntity<CustomerDto> getCustomerFromToken(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findCustomerByToken(request));
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<CustomerEntity> updateCustomerDetails(@PathVariable String id, @RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok().body(customerService.updateCustomerDetails(customerDto, id));
    }

    @PutMapping("/customer/password/{id}")
    public ResponseEntity<CustomerEntity> updateCustomerPassword(@PathVariable String id, @RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok().body(customerService.updateCustomerPassword(customerDto, id));
    }

    @PostMapping("/customer/verify/{id}")
    public ResponseEntity<Boolean> verifyCredentials(@PathVariable String id, @RequestBody String password) {
        return ResponseEntity.ok().body(customerService.verifyCredentials(id, password));
    }

}
