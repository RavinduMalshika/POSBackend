package lk.ijse.POSBackend.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
    private String id;
    private String title;
    private String firstName;
    private String lastName;
    private String nic;
    private String address;
    private String city;
    private String province;
    private List<String> phone;
    private String email;
    private String password;
}
