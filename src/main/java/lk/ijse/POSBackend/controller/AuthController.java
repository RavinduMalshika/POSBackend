package lk.ijse.POSBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lk.ijse.POSBackend.dto.CustomerDto;
import lk.ijse.POSBackend.dto.LoginDto;
import lk.ijse.POSBackend.security.jwt.JwtUtils;
import lk.ijse.POSBackend.service.CustomerService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Authorization", description = "Authorization APIs")
@RestController
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CustomerService customerService;

    @Operation(
        summary = "Login as an employee or customer",
        description = "Generate a token if the credentails match to an employee or a customer",
        tags = {"POST"}
    )
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok().body(token);
    }
    
    @Operation(
        summary = "Register a customer",
        description = "Creates a customer entry to the database",
        tags = {"POST"}
    )
    @PostMapping("/auth/register")
    public ResponseEntity<?> signUp(@RequestBody CustomerDto customerDto) {
        if(customerService.findCustomerByEmail(customerDto.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email is already in use");
        }

        CustomerDto newCustomer = new CustomerDto();
        newCustomer.setId(customerDto.getId());
        newCustomer.setTitle(customerDto.getTitle());
        newCustomer.setFirstName(customerDto.getFirstName());
        newCustomer.setLastName(customerDto.getLastName());
        newCustomer.setNic(customerDto.getNic());
        newCustomer.setAddress(customerDto.getAddress());
        newCustomer.setCity(customerDto.getCity());
        newCustomer.setProvince(customerDto.getProvince());
        newCustomer.setEmail(customerDto.getEmail());
        newCustomer.setPassword(passwordEncoder.encode(customerDto.getPassword()));

        customerService.createCustomer(newCustomer);

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(customerDto.getEmail(), customerDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok().body(token);
    }

    
    
}
