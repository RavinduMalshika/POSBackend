package lk.ijse.POSBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.POSBackend.dto.CustomerDto;
import lk.ijse.POSBackend.entity.CustomerEntity;
import lk.ijse.POSBackend.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Customer", description = "Customer Management APIs")
@RestController
@CrossOrigin(origins = "*")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Operation(
        summary = "Retrieve a customer by token",
        description = "Get a customer DTO by specifying its token",
        tags = {"GET"}
    )
    @GetMapping("/customer/token")
    public ResponseEntity<CustomerDto> getCustomerFromToken(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findCustomerByToken(request));
    }

    @Operation(
        summary = "Update customer details",
        description = "Update a customer details by specifying its ID",
        tags = {"PUT"}
    )
    @PutMapping("/customer/{id}")
    public ResponseEntity<CustomerEntity> updateCustomerDetails(@PathVariable String id, @RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok().body(customerService.updateCustomerDetails(customerDto, id));
    }

    @Operation(
        summary = "Update customer password",
        description = "Update a customer password by specifying its ID",
        tags = {"PUT"}
    )
    @PutMapping("/customer/password/{id}")
    public ResponseEntity<CustomerEntity> updateCustomerPassword(@PathVariable String id, @RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok().body(customerService.updateCustomerPassword(customerDto, id));
    }

    @Operation(
        summary = "Verify customer credentials",
        description = "Verify the credentials entered as valid credentials using password and specifying the customer ID",
        tags = {"POST"}
    )
    @PostMapping("/customer/verify/{id}")
    public ResponseEntity<Boolean> verifyCredentials(@PathVariable String id, @RequestBody String password) {
        return ResponseEntity.ok().body(customerService.verifyCredentials(id, password));
    }

    @Operation(
        summary = "Generate an ID for a customer",
        description = "Get the next available ID for a new customer",
        tags = {"GET"}
    )
    @GetMapping("/auth/customer/generateId")
    public ResponseEntity<String> generateCustomerId() {
        return ResponseEntity.ok().body(customerService.generateId());
    }

    @Operation(
        summary = "Delete customer",
        description = "Delete a customer by specifying its ID",
        tags = {"DELETE"}
    )
    @DeleteMapping("/customer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String id) {
        return ResponseEntity.ok().body(customerService.deleteCustomer(id));
    }

}
