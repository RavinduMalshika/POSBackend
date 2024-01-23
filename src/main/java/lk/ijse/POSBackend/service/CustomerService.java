package lk.ijse.POSBackend.service;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.POSBackend.dto.CustomerDto;
import lk.ijse.POSBackend.entity.CustomerEntity;

public interface CustomerService {
    public CustomerEntity createCustomer(CustomerDto customerDto);

    public CustomerEntity updateCustomer(CustomerDto customerDto, String id);
    
    public CustomerDto findCustomerById(String id);

    public CustomerDto findCustomerByEmail(String email);

    public CustomerDto findCustomerByToken(HttpServletRequest request);

    public List<CustomerDto> findAllCustomers();

    public String deleteCustomer(String id);

    public String generateId();

    public Boolean verifyCredentials(String id, String password);
}
