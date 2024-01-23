package lk.ijse.POSBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lk.ijse.POSBackend.entity.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {
    @Query("SELECT e FROM EmployeeEntity e WHERE e.email = :email")
    List<EmployeeEntity> findByEmail(@Param("email") String email);
} 
