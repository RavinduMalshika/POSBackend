package lk.ijse.POSBackend.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Orders")
@Getter
@Setter
public class OrderEntity {
    @Id
    @Column(name = "Order_ID", nullable = false, length = 7)
    private String id;

    @Column(name = "OrderDate", columnDefinition = "date")
    private Date date;

    @OneToMany(mappedBy = "orderEntity", targetEntity = OrderDetailEntity.class, cascade = CascadeType.ALL)
    private List<OrderDetailEntity> orderDetailEntities;

    @ManyToOne()
    @JoinColumn(name = "Cust_ID", nullable = false)
    private CustomerEntity customerEntity;
}
