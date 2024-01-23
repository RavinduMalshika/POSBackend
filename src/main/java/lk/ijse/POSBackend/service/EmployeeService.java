package lk.ijse.POSBackend.service;

import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.POSBackend.dto.EmployeeDto;
import lk.ijse.POSBackend.entity.EmployeeEntity;

public interface EmployeeService {
    public EmployeeEntity createEmployee(EmployeeDto employeeDto);

    public EmployeeEntity updateEmployee(EmployeeDto employeeDto, String id);
    
    public EmployeeDto findEmployeeById(String id);

    public EmployeeDto findEmployeeByEmail(String email);

    public EmployeeDto findEmployeeByToken(HttpServletRequest request);

    public String deleteEmployee(String id);

    public String generateId();

    public Boolean verifyCredentials(String email, String password);
}
