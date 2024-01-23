package lk.ijse.POSBackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Stock")
@Getter
@Setter
public class StockEntity {
    @Id
    @Column(name = "Stock_ID", nullable = false, length = 7)
    private String id;

    @ManyToOne()
    @JoinColumn(name = "Item", nullable = false)
    private ItemEntity itemEntity;

    @Column(name = "Batch")
    private Integer batch;

    @Column(name = "Price")
    private Double price;

    @Column(name = "Quantity")
    private Double quantity;
}
