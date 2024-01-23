package lk.ijse.POSBackend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.POSBackend.dto.CustomerDto;
import lk.ijse.POSBackend.entity.CustomerEntity;
import lk.ijse.POSBackend.entity.embedded.Address;
import lk.ijse.POSBackend.entity.embedded.Name;
import lk.ijse.POSBackend.repository.CustomerRepository;
import lk.ijse.POSBackend.security.jwt.JwtUtils;
import lk.ijse.POSBackend.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public CustomerEntity createCustomer(CustomerDto customerDto) {
        if (customerRepository.findById(customerDto.getId()).orElse(null) == null) {
            CustomerEntity customerEntity = new CustomerEntity();
            customerEntity.setId(customerDto.getId());
            customerEntity.setTitle(customerDto.getTitle());
            customerEntity.setName(new Name(customerDto.getFirstName(), customerDto.getLastName()));
            customerEntity.setNic(customerDto.getNic());
            customerEntity.setAddress(
                    new Address(customerDto.getAddress(), customerDto.getCity(), customerDto.getProvince()));
            customerEntity.setPhone(customerDto.getPhone());
            customerEntity.setEmail(customerDto.getEmail());
            customerEntity.setPassword(new BCryptPasswordEncoder().encode(customerDto.getPassword()));

            return customerRepository.save(customerEntity);
        } else {
            return null;
        }
    }

    @Override
    public CustomerEntity updateCustomer(CustomerDto customerDto, String id) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElse(null);

        if (customerEntity != null) {
            customerEntity.setId(customerDto.getId());
            customerEntity.setTitle(customerDto.getTitle());
            customerEntity.setName(new Name(customerDto.getFirstName(), customerDto.getLastName()));
            customerEntity.setNic(customerDto.getNic());
            customerEntity.setAddress(
                    new Address(customerDto.getAddress(), customerDto.getCity(), customerDto.getProvince()));
            customerEntity.setPhone(customerDto.getPhone());
            customerEntity.setEmail(customerDto.getEmail());
            customerEntity.setPassword(new BCryptPasswordEncoder().encode(customerDto.getPassword()));


            return customerRepository.save(customerEntity);
        } else {
            return null;
        }
    }

    @Override
    public CustomerDto findCustomerById(String id) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElse(null);
        if (customerEntity != null) {
            CustomerDto customerDto = new CustomerDto();
            customerDto.setId(customerEntity.getId());
            customerDto.setTitle(customerEntity.getTitle());
            customerDto.setFirstName(customerEntity.getName().getFirstName());
            customerDto.setLastName(customerEntity.getName().getLastName());
            customerDto.setNic(customerEntity.getNic());
            customerDto.setAddress(customerEntity.getAddress().getAddress());
            customerDto.setCity(customerEntity.getAddress().getCity());
            customerDto.setProvince(customerEntity.getAddress().getProvince());
            customerDto.setPhone(customerEntity.getPhone());
            customerDto.setEmail(customerEntity.getEmail());
            customerDto.setPassword(customerEntity.getPassword());

            return customerDto;
        } else {
            return null;
        }
    }

    @Override
    public CustomerDto findCustomerByEmail(String email) {
        List<CustomerEntity> customerEntities = customerRepository.findByEmail(email);
        CustomerEntity customerEntity;
        if(customerEntities.size()>0) {
            customerEntity = customerEntities.get(0);
        } else {
            customerEntity = null;
        }

        if (customerEntity != null) {
            CustomerDto customerDto = new CustomerDto();
            customerDto.setId(customerEntity.getId());
            customerDto.setTitle(customerEntity.getTitle());
            customerDto.setFirstName(customerEntity.getName().getFirstName());
            customerDto.setLastName(customerEntity.getName().getLastName());
            customerDto.setNic(customerEntity.getNic());
            customerDto.setAddress(customerEntity.getAddress().getAddress());
            customerDto.setCity(customerEntity.getAddress().getCity());
            customerDto.setProvince(customerEntity.getAddress().getProvince());
            customerDto.setPhone(customerEntity.getPhone());
            customerDto.setEmail(customerEntity.getEmail());
            customerDto.setPassword(customerEntity.getPassword());

            return customerDto;
        } else {
            return null;
        }
    }

    @Override
    public CustomerDto findCustomerByToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            String email = jwtUtils.getUsernameFromToken(token);

            CustomerDto customerDto = findCustomerByEmail(email);

            return customerDto;
        } else {
            return null;
        }
    }

    @Override
    public List<CustomerDto> findAllCustomers() {
        List<CustomerDto> customerDtos = new ArrayList<>();
        List<CustomerEntity> customerEntities = customerRepository.findAll();
        for (CustomerEntity customerEntity : customerEntities) {
            CustomerDto customerDto = new CustomerDto();
            customerDto.setId(customerEntity.getId());
            customerDto.setTitle(customerEntity.getTitle());
            customerDto.setFirstName(customerEntity.getName().getFirstName());
            customerDto.setLastName(customerEntity.getName().getLastName());
            customerDto.setNic(customerEntity.getNic());
            customerDto.setAddress(customerEntity.getAddress().getAddress());
            customerDto.setCity(customerEntity.getAddress().getCity());
            customerDto.setProvince(customerEntity.getAddress().getProvince());
            customerDto.setPhone(customerEntity.getPhone());
            customerDto.setEmail(customerEntity.getEmail());
            customerDto.setPassword(customerEntity.getPassword());
            
            customerDtos.add(customerDto);
        }

        return customerDtos;
    }

    @Override
    public String deleteCustomer(String id) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElse(null);

        if (customerEntity != null) {
            customerRepository.delete(customerEntity);
            return "Deleted Successfully";
        } else {
            return "Failed to Delete";
        }
    }

    @Override
    public String generateId() {
        List<CustomerEntity> customerEntities = customerRepository.findAll();

        if (customerEntities.size() > 0) {
            CustomerEntity customerEntity = customerEntities.get(customerEntities.size() - 1);

            String lastId = customerEntity.getId().substring(3);
            Integer incrementedId = Integer.parseInt(lastId) + 1;
            String id = "CUS" + String.format("%04d", incrementedId);
            return id;
        } else {
            return "CUS0001";
        }
    }

    @Override
    public Boolean verifyCredentials(String id, String password) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElse(null);
        if (customerEntity.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

}
