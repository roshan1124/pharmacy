package com.pharmacy.pharmacy_management.controller;

import com.pharmacy.pharmacy_management.entity.Bill;
import com.pharmacy.pharmacy_management.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/billing")
public class BillingController {

    @Autowired
    private BillingService billingService;

    @PostMapping
    public ResponseEntity<Bill> createBill(@RequestBody Bill bill) {
        Bill createdBill = billingService.createBill(bill);
        return new ResponseEntity<>(createdBill, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Bill>> getAllBills() {
        List<Bill> bills = billingService.getAllBills();
        return ResponseEntity.ok(bills);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long id) {
        Bill bill = billingService.getBillById(id);
        return ResponseEntity.ok(bill);
    }

    @GetMapping("/customer/{customerName}")
    public ResponseEntity<List<Bill>> getBillsByCustomerName(@PathVariable String customerName) {
        List<Bill> bills = billingService.getBillsByCustomerName(customerName);
        return ResponseEntity.ok(bills);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<Bill>> getBillsByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        List<Bill> bills = billingService.getBillsByDateRange(startDate, endDate);
        return ResponseEntity.ok(bills);
    }

    @GetMapping("/sales/total")
    public ResponseEntity<Double> getTotalSalesByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        Double totalSales = billingService.getTotalSalesByDateRange(startDate, endDate);
        return ResponseEntity.ok(totalSales);
    }
}