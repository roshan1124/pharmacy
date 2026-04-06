package com.pharmacy.pharmacy_management.repository;

import com.pharmacy.pharmacy_management.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    List<Medicine> findByNameContainingIgnoreCase(String name);

    List<Medicine> findByCompany(String company);

    List<Medicine> findByStockLessThan(Integer stock);
}