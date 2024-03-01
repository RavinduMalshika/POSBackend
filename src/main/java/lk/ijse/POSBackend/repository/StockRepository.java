package lk.ijse.POSBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lk.ijse.POSBackend.entity.ItemEntity;
import lk.ijse.POSBackend.entity.StockEntity;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, String> {
    @Query("SELECT s FROM StockEntity s WHERE s.itemEntity = :itemEntity")
    List<StockEntity> findByItem(@Param("itemEntity") ItemEntity itemEntity);
}
