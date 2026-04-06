package com.pharmacy.pharmacy_management.service.impl;

import com.pharmacy.pharmacy_management.entity.Bill;
import com.pharmacy.pharmacy_management.entity.BillItem;
import com.pharmacy.pharmacy_management.entity.Medicine;
import com.pharmacy.pharmacy_management.repository.BillRepository;
import com.pharmacy.pharmacy_management.repository.MedicineRepository;
import com.pharmacy.pharmacy_management.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class BillingServiceImpl implements BillingService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    @Transactional
    public Bill createBill(Bill bill) {
        // Calculate totals
        double total = 0;
        for (BillItem item : bill.getItems()) {
            Medicine medicine = medicineRepository.findById(item.getMedicine().getId())
                    .orElseThrow(() -> new RuntimeException("Medicine not found"));

            // Check stock
            if (medicine.getStock() < item.getQuantity()) {
                throw new RuntimeException("Insufficient stock for: " + medicine.getName());
            }

            // Update stock
            medicine.setStock(medicine.getStock() - item.getQuantity());
            medicineRepository.save(medicine);

            // Set price and subtotal
            item.setPrice(medicine.getPrice());
            item.setSubtotal(item.getPrice() * item.getQuantity());
            item.setBill(bill);
            total += item.getSubtotal();
        }

        bill.setTotalAmount(total);

        // Apply discount if any
        if (bill.getDiscount() == null) {
            bill.setDiscount(0.0);
        }
        bill.setFinalAmount(total - bill.getDiscount());
        bill.setBillDate(LocalDateTime.now());

        return billRepository.save(bill);
    }

    @Override
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    @Override
    public Bill getBillById(Long id) {
        return billRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found with id: " + id));
    }

    @Override
    public List<Bill> getBillsByCustomerName(String customerName) {
        return billRepository.findByCustomerNameContainingIgnoreCase(customerName);
    }

    @Override
    public List<Bill> getBillsByDateRange(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime start = LocalDate.parse(startDate, formatter).atStartOfDay();
        LocalDateTime end = LocalDate.parse(endDate, formatter).atTime(23, 59, 59);
        return billRepository.findByBillDateBetween(start, end);
    }

    @Override
    public Double getTotalSalesByDateRange(String startDate, String endDate) {
        List<Bill> bills = getBillsByDateRange(startDate, endDate);
        return bills.stream().mapToDouble(Bill::getFinalAmount).sum();
    }
}