package lk.ijse.POSBackend.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Item")
@Getter
@Setter
public class ItemEntity {
    @Id
    @Column(name = "Item_ID", nullable = false, length = 7)
    private String id;

    @Column(name = "Name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Category", nullable = false)
    @JsonIgnore
    private CategoryEntity categoryEntity;

    @Column(name = "Price")
    private Double price;

    @OneToMany(mappedBy = "itemEntity", targetEntity = StockEntity.class, cascade =  CascadeType.ALL)
    private List<StockEntity> stockEntities;

    @OneToMany(mappedBy = "itemEntity", targetEntity = OrderDetailEntity.class, cascade = CascadeType.ALL)
    private List<OrderDetailEntity> orderDetailEntities;
}
