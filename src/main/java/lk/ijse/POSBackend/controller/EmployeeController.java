package lk.ijse.POSBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.POSBackend.dto.EmployeeDto;
import lk.ijse.POSBackend.entity.EmployeeEntity;
import lk.ijse.POSBackend.service.EmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Employee", description = "Employee Management APIs")
@RestController
@CrossOrigin(origins = "*")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Operation(
        summary = "Create an employee",
        description = "Save a new employee to the database",
        tags = {"POST"}
    )
    @PostMapping("/employee")
    public ResponseEntity<EmployeeEntity> createEmployee(@RequestBody EmployeeDto employeeDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(employeeService.createEmployee(employeeDto));
    }

    @Operation(
        summary = "Update employee details",
        description = "Update an employee details by specifying its ID",
        tags = {"PUT"}
    )
    @PutMapping("/employee/{id}")
    public ResponseEntity<EmployeeEntity> updateEmployeeDetails(@PathVariable String id, @RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok().body(employeeService.updateEmployeeDetails(employeeDto, id));
    }

    @Operation(
        summary = "Update employee password",
        description = "Update an employee password by specifying its ID",
        tags = {"PUT"}
    )
    @PutMapping("/employee/password/{id}")
    public ResponseEntity<EmployeeEntity> updateEmployeePassword(@PathVariable String id, @RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok().body(employeeService.updateEmployeePassword(employeeDto, id));
    }

    @Operation(
        summary = "Retrieve an employee by ID",
        description = "Get an employee DTO by specifying its ID",
        tags = {"GET"}
    )
    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findEmployeeById(id));
    }

    @Operation(
        summary = "Retrieve an employee by Email",
        description = "Get an employee DTO by specifying its Email",
        tags = {"GET"}
    )
    @GetMapping("/employee/email/{email}")
    public ResponseEntity<EmployeeDto> getEmployeeByEmail(@PathVariable String email) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findEmployeeByEmail(email));
    }

    @Operation(
        summary = "Retrieve an employee by token",
        description = "Get an employee DTO by specifying its token",
        tags = {"GET"}
    )
    @GetMapping("/employee/token")
    public ResponseEntity<EmployeeDto> getEmployeeByToken(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findEmployeeByToken(request));
    }
    
    @Operation(
        summary = "Delete employee",
        description = "Delete an employee by specifying its ID",
        tags = {"DELETE"}
    )
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.deleteEmployee(id));
    }

    @Operation(
        summary = "Verify employee credentials",
        description = "Verify the credentials entered as valid credentials using password and specifying the employee ID",
        tags = {"POST"}
    )
    @PostMapping("/employee/verify/{id}")
    public ResponseEntity<Boolean> verifyCredentials(@PathVariable String id, @RequestBody String password) {
        return ResponseEntity.ok().body(employeeService.verifyCredentials(id, password));
    }
    
    @Operation(
        summary = "Generate an ID for an employee",
        description = "Get the next available ID for a new employee",
        tags = {"GET"}
    )
    @GetMapping("/employee/generateId")
    public ResponseEntity<String> generateEmployeeId() {
        return ResponseEntity.ok().body(employeeService.generateId());
    }
    
}
