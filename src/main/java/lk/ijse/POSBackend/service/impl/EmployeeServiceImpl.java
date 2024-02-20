package lk.ijse.POSBackend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.POSBackend.dto.EmployeeDto;
import lk.ijse.POSBackend.entity.EmployeeEntity;
import lk.ijse.POSBackend.entity.embedded.Address;
import lk.ijse.POSBackend.entity.embedded.Name;
import lk.ijse.POSBackend.repository.EmployeeRepository;
import lk.ijse.POSBackend.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Value("${app.secret}")
    private String secret;

    @Override
    public EmployeeEntity createEmployee(EmployeeDto employeeDto) {
        if (employeeRepository.findById(employeeDto.getId()).orElse(null) == null) {
            EmployeeEntity employeeEntity = new EmployeeEntity();
            employeeEntity.setId(employeeDto.getId());
            employeeEntity.setTitle(employeeDto.getTitle());
            employeeEntity.setName(new Name(employeeDto.getFirstName(), employeeDto.getLastName()));
            employeeEntity.setNic(employeeDto.getNic());
            employeeEntity.setAddress(
                    new Address(employeeDto.getAddress(), employeeDto.getCity(), employeeDto.getProvince()));
            employeeEntity.setJobTitle(employeeDto.getJobTitle());
            employeeEntity.setAccessLevel(employeeDto.getAccessLevel());
            employeeEntity.setPhone(employeeDto.getPhone());
            employeeEntity.setEmail(employeeDto.getEmail());
            employeeEntity.setPassword(new BCryptPasswordEncoder().encode(employeeDto.getPassword()));

            return employeeRepository.save(employeeEntity);
        } else {
            return null;
        }
    }

    @Override
    public EmployeeEntity updateEmployeeDetails(EmployeeDto employeeDto, String id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);

        if (employeeEntity != null) {
            employeeEntity.setId(employeeDto.getId());
            employeeEntity.setTitle(employeeDto.getTitle());
            employeeEntity.setName(new Name(employeeDto.getFirstName(), employeeDto.getLastName()));
            employeeEntity.setNic(employeeDto.getNic());
            employeeEntity.setAddress(
                    new Address(employeeDto.getAddress(), employeeDto.getCity(), employeeDto.getProvince()));
            employeeEntity.setJobTitle(employeeDto.getJobTitle());
            employeeEntity.setAccessLevel(employeeDto.getAccessLevel());
            employeeEntity.setPhone(employeeDto.getPhone());

            return employeeRepository.save(employeeEntity);
        } else {
            return null;
        }
    }

    @Override
    public EmployeeEntity updateEmployeePassword(EmployeeDto employeeDto, String id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);

        if (employeeEntity != null) {
            employeeEntity.setPassword(new BCryptPasswordEncoder().encode(employeeDto.getPassword()));

            return employeeRepository.save(employeeEntity);
        } else {
            return null;
        }
    }

    @Override
    public EmployeeDto findEmployeeById(String id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        if (employeeEntity != null) {
            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setId(employeeEntity.getId());
            employeeDto.setTitle(employeeEntity.getTitle());
            employeeDto.setFirstName(employeeEntity.getName().getFirstName());
            employeeDto.setLastName(employeeEntity.getName().getLastName());
            employeeDto.setNic(employeeEntity.getNic());
            employeeDto.setAddress(employeeEntity.getAddress().getAddress());
            employeeDto.setCity(employeeEntity.getAddress().getCity());
            employeeDto.setProvince(employeeEntity.getAddress().getProvince());
            employeeDto.setJobTitle(employeeEntity.getJobTitle());
            employeeDto.setAccessLevel(employeeEntity.getAccessLevel());
            employeeDto.setPhone(employeeEntity.getPhone());
            employeeDto.setEmail(employeeEntity.getEmail());
            employeeDto.setPassword(employeeEntity.getPassword());

            return employeeDto;
        } else {
            return null;
        }
    }

    @Override
    public EmployeeDto findEmployeeByEmail(String email) {
        List<EmployeeEntity> employeeEntities = employeeRepository.findByEmail(email);
        EmployeeEntity employeeEntity;
        if(employeeEntities.size()>0) {
            employeeEntity = employeeEntities.get(0);
        } else {
            employeeEntity = null;
        }

        if (employeeEntity != null) {
            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setId(employeeEntity.getId());
            employeeDto.setTitle(employeeEntity.getTitle());
            employeeDto.setFirstName(employeeEntity.getName().getFirstName());
            employeeDto.setLastName(employeeEntity.getName().getLastName());
            employeeDto.setNic(employeeEntity.getNic());
            employeeDto.setAddress(employeeEntity.getAddress().getAddress());
            employeeDto.setCity(employeeEntity.getAddress().getCity());
            employeeDto.setProvince(employeeEntity.getAddress().getProvince());
            employeeDto.setJobTitle(employeeEntity.getJobTitle());
            employeeDto.setAccessLevel(employeeEntity.getAccessLevel());
            employeeDto.setPhone(employeeEntity.getPhone());
            employeeDto.setEmail(employeeEntity.getEmail());
            employeeDto.setPassword(employeeEntity.getPassword());

            return employeeDto;
        } else {
            return null;
        }
    }

    @Override
    public EmployeeDto findEmployeeByToken(HttpServletRequest request) {
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
            EmployeeDto employeeDto = findEmployeeByEmail(email);
            return employeeDto;
        } else {
            return null;
        }
    }

    @Override
    public String deleteEmployee(String id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);

        if (employeeEntity != null) {
            employeeRepository.delete(employeeEntity);
            return "Deleted Successfully";
        } else {
            return "Failed to Delete";
        }
    }

    @Override
    public String generateId() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();

        if (employeeEntities.size() > 0) {
            EmployeeEntity employeeEntity = employeeEntities.get(employeeEntities.size() - 1);

            String lastId = employeeEntity.getId().substring(3);
            Integer incrementedId = Integer.parseInt(lastId) + 1;
            String id = "EMP" + String.format("%04d", incrementedId);
            return id;
        } else {
            return "EMP0001";
        }
    }

    @Override
    public Boolean verifyCredentials(String id, String password) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(employeeEntity.getId());
        System.out.println(encoder.matches(password, employeeEntity.getPassword()));

        if (employeeEntity != null && encoder.matches(password, employeeEntity.getPassword())) {
            return true;
        } else {
            return false;
        }
    }
}
