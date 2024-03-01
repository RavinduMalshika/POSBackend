package lk.ijse.POSBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lk.ijse.POSBackend.entity.CustomerEntity;
import lk.ijse.POSBackend.entity.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {
    @Query("SELECT o FROM OrderEntity o WHERE o.customerEntity = :customerEntity")
    List<OrderEntity> findByCustomer(@Param("customerEntity") CustomerEntity customerEntity);
}
