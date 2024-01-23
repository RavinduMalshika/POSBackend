package lk.ijse.POSBackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "OrderDetail")
@IdClass(OrderDetailPK.class)
@Getter
@Setter
public class OrderDetailEntity {
    @Id
    @ManyToOne()
    @JoinColumn(name = "Order_ID", nullable = false)
    private OrderEntity orderEntity;

    @Id
    @ManyToOne()
    @JoinColumn(name = "Item_ID", nullable = false)
    private ItemEntity itemEntity;

    @Column(name = "Quantity", nullable = false)
    private Double quantity;

    @Column(name = "Discount")
    private Double discount;
}
