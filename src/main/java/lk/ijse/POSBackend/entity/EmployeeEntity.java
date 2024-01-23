package lk.ijse.POSBackend.entity;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lk.ijse.POSBackend.entity.embedded.Address;
import lk.ijse.POSBackend.entity.embedded.Name;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Employee")
@Getter
@Setter
public class EmployeeEntity {
    @Id
    @Column(name = "Emp_ID", nullable = false, length = 7)
    private String id;

    @Column(name = "Title")
    private String title;

    private Name name;

    @Column(name = "NIC", nullable = false, length = 12)
    private String nic;

    private Address address;

    @Column(name = "Job_Title")
    private String jobTitle;

    @Column(name = "Access_Lvl")
    private Integer accessLevel;

    @ElementCollection
    @CollectionTable(
            name = "EmployeePhone",
            joinColumns = @JoinColumn(name = "Emp_ID")
    )
    private List<String> phone;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;
}
