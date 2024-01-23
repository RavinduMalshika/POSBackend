package lk.ijse.POSBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lk.ijse.POSBackend.entity.OrderDetailEntity;
import lk.ijse.POSBackend.entity.OrderEntity;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, OrderEntity> {
    @Query("SELECT o FROM OrderDetailEntity o WHERE o.orderEntity = :id")
    List<OrderDetailEntity> findByOrder(@Param("id") String id);
}
