package lk.ijse.POSBackend.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lk.ijse.POSBackend.entity.embedded.Address;
import lk.ijse.POSBackend.entity.embedded.Name;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Customer")
@Getter
@Setter
public class CustomerEntity {
    @Id
    @Column(name = "Cust_ID", nullable = false, length = 7)
    private String id;

    @Column(name = "Title")
    private String title;

    private Name name;

    @Column(name = "NIC", length = 12)
    private String nic;

    private Address address;

    @ElementCollection
    @CollectionTable(
            name = "CustomerPhone",
            joinColumns = @JoinColumn(name = "Cust_ID")
    )
    private List<String> phone;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;

    @OneToMany(mappedBy = "customerEntity", targetEntity = OrderEntity.class, cascade = CascadeType.ALL)
    private List<OrderEntity> orderEntities;
}
