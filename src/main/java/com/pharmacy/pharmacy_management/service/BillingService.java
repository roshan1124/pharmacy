package com.pharmacy.pharmacy_management.service;

import com.pharmacy.pharmacy_management.entity.Bill;
import java.util.List;

public interface BillingService {

    Bill createBill(Bill bill);

    List<Bill> getAllBills();

    Bill getBillById(Long id);

    List<Bill> getBillsByCustomerName(String customerName);

    List<Bill> getBillsByDateRange(String startDate, String endDate);

    Double getTotalSalesByDateRange(String startDate, String endDate);
}