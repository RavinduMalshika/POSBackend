package lk.ijse.POSBackend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.POSBackend.dto.CustomerDto;
import lk.ijse.POSBackend.dto.EmployeeDto;
import lk.ijse.POSBackend.entity.CustomerEntity;
import lk.ijse.POSBackend.entity.EmployeeEntity;
import lk.ijse.POSBackend.entity.embedded.Address;
import lk.ijse.POSBackend.entity.embedded.Name;
import lk.ijse.POSBackend.repository.CustomerRepository;
import lk.ijse.POSBackend.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Value("${app.secret}")
    private String secret;

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
            customerEntity.setPassword(customerDto.getPassword());

            return customerRepository.save(customerEntity);
        } else {
            return null;
        }
    }

    @Override
    public CustomerEntity updateCustomerDetails(CustomerDto customerDto, String id) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElse(null);

        if (customerEntity != null) {
            customerEntity.setId(customerDto.getId());
            customerEntity.setTitle(customerDto.getTitle());
            customerEntity.setName(new Name(customerDto.getFirstName(), customerDto.getLastName()));
            customerEntity.setNic(customerDto.getNic());
            customerEntity.setAddress(
                    new Address(customerDto.getAddress(), customerDto.getCity(), customerDto.getProvince()));
            customerEntity.setPhone(customerDto.getPhone());

            return customerRepository.save(customerEntity);
        } else {
            return null;
        }
    }

    @Override
    public CustomerEntity updateCustomerPassword(CustomerDto customerDto, String id) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElse(null);

        if (customerEntity != null) {
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
        if (customerEntities.size() > 0) {
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
        String email = null;

        if (token != null) {
            try {
                email = Jwts.parser()
                        .setSigningKey(secret)
                        .parseClaimsJws(token.replace("Bearer ", ""))
                        .getBody()
                        .getSubject();

            } catch (Exception e) {
                throw e;
            }
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
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(customerEntity.getId());
        System.out.println(encoder.matches(password, customerEntity.getPassword()));

        if (customerEntity != null && encoder.matches(password, customerEntity.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

}
