package lk.ijse.POSBackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lk.ijse.POSBackend.dto.CustomerDto;
import lk.ijse.POSBackend.dto.EmployeeDto;
import lk.ijse.POSBackend.service.CustomerService;
import lk.ijse.POSBackend.service.EmployeeService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    CustomerService customerService;

    @Autowired
    EmployeeService employeeService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomerDto customerDto = customerService.findCustomerByEmail(username);

        if (customerDto != null) {
            System.out.println(customerDto.getId());

            return User.builder()
                    .username(customerDto.getEmail())
                    .password(customerDto.getPassword())
                    .build();
        } else {
            EmployeeDto employeeDto = employeeService.findEmployeeByEmail(username);

            if(employeeDto != null) {
                return User.builder()
                    .username(employeeDto.getEmail())
                    .password(employeeDto.getPassword())
                    .build();
            } else {
                throw new UsernameNotFoundException("User is not found.");
            }
        }

    }

}
