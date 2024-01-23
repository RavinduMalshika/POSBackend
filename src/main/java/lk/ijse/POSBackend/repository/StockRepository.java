package lk.ijse.POSBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lk.ijse.POSBackend.entity.StockEntity;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, String> {
    
}
