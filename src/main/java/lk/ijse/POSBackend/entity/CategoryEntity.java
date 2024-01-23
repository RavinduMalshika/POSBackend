package lk.ijse.POSBackend.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Category")
@Getter
@Setter
public class CategoryEntity {
    @Id
    @Column(name = "Category_ID", nullable = false, length = 7)
    private String id;

    @Column(name = "Name")
    private String name;

    @OneToMany(mappedBy = "categoryEntity", targetEntity = ItemEntity.class, cascade = CascadeType.ALL)
    private List<ItemEntity> itemEntities;
}
