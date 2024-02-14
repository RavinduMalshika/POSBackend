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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin(origins = "*")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/employee")
    public ResponseEntity<EmployeeEntity> createEmployee(@RequestBody EmployeeDto employeeDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(employeeService.createEmployee(employeeDto));
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<EmployeeEntity> updateEmployeeDetails(@PathVariable String id, @RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok().body(employeeService.updateEmployeeDetails(employeeDto, id));
    }

    @PutMapping("/employee/password/{id}")
    public ResponseEntity<EmployeeEntity> updateEmployeePassword(@PathVariable String id, @RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok().body(employeeService.updateEmployeePassword(employeeDto, id));
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findEmployeeById(id));
    }

    @GetMapping("/employee/email/{email}")
    public ResponseEntity<EmployeeDto> getEmployeeByEmail(@PathVariable String email) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findEmployeeByEmail(email));
    }

    @GetMapping("/employee/token")
    public ResponseEntity<EmployeeDto> getEmployeeByToken(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findEmployeeByToken(request));
    }
    
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.deleteEmployee(id));
    }

    @PostMapping("/employee/verify/{id}")
    public ResponseEntity<Boolean> verifyCredentials(@PathVariable String id, @RequestBody String password) {
        return ResponseEntity.ok().body(employeeService.verifyCredentials(id, password));
    }
    
    @GetMapping("/employee/generateId")
    public ResponseEntity<String> generateEmployeeId() {
        return ResponseEntity.ok().body(employeeService.generateId());
    }
    
}
