package com.pharmacy.pharmacy_management.repository;

import com.pharmacy.pharmacy_management.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    List<Bill> findByCustomerNameContainingIgnoreCase(String customerName);

    List<Bill> findByBillDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Bill> findByUserId(Long userId);
}