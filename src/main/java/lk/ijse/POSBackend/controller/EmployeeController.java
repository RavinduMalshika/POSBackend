package lk.ijse.POSBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.POSBackend.dto.EmployeeDto;
import lk.ijse.POSBackend.entity.EmployeeEntity;
import lk.ijse.POSBackend.service.EmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@CrossOrigin(origins = "*")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/auth/employee")
    public ResponseEntity<EmployeeEntity> createEmployee(@RequestBody EmployeeDto employeeDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(employeeService.createEmployee(employeeDto));
    }

    @GetMapping("/auth/employee")
    public ResponseEntity<EmployeeDto> getEmployeeById() {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findEmployeeById("EMP0001"));
    }
    
    @DeleteMapping("/auth/employee")
    public ResponseEntity<String> deleteEmployee() {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.deleteEmployee("EMP0001"));
    }

    @GetMapping("/employee/token")
    public ResponseEntity<EmployeeDto> getEmployeeByToken(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findEmployeeByToken(request));
    }
    
}
