package lk.ijse.POSBackend.entity;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class OrderDetailPK implements Serializable {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Order_ID", nullable = false)
    private OrderEntity orderEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Item_ID", nullable = false)
    private ItemEntity itemEntity;
}
