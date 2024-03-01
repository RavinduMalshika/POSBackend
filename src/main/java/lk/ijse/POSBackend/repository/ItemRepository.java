package lk.ijse.POSBackend.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import lk.ijse.POSBackend.entity.CategoryEntity;
import lk.ijse.POSBackend.entity.ItemEntity;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, String> {
    @Query("SELECT i FROM ItemEntity i WHERE i.categoryEntity = :categoryEntity")
    List<ItemEntity> filterByCategory(@Param("categoryEntity") CategoryEntity categoryEntity);
}
