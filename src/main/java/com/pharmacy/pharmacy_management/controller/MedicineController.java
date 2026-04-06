package com.pharmacy.pharmacy_management.controller;

import com.pharmacy.pharmacy_management.dto.request.MedicineRequest;
import com.pharmacy.pharmacy_management.dto.response.MedicineResponse;
import com.pharmacy.pharmacy_management.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/medicine")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @PostMapping
    public ResponseEntity<MedicineResponse> addMedicine(@RequestBody MedicineRequest request) {
        MedicineResponse response = medicineService.addMedicine(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MedicineResponse>> getAllMedicines() {
        List<MedicineResponse> medicines = medicineService.getAllMedicines();
        return ResponseEntity.ok(medicines);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicineResponse> getMedicineById(@PathVariable Long id) {
        MedicineResponse medicine = medicineService.getMedicineById(id);
        return ResponseEntity.ok(medicine);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicineResponse> updateMedicine(@PathVariable Long id, @RequestBody MedicineRequest request) {
        MedicineResponse medicine = medicineService.updateMedicine(id, request);
        return ResponseEntity.ok(medicine);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable Long id) {
        medicineService.deleteMedicine(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<MedicineResponse>> searchMedicinesByName(@RequestParam String name) {
        List<MedicineResponse> medicines = medicineService.searchMedicinesByName(name);
        return ResponseEntity.ok(medicines);
    }

    @GetMapping("/company/{company}")
    public ResponseEntity<List<MedicineResponse>> getMedicinesByCompany(@PathVariable String company) {
        List<MedicineResponse> medicines = medicineService.getMedicinesByCompany(company);
        return ResponseEntity.ok(medicines);
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<MedicineResponse>> getLowStockMedicines(@RequestParam Integer threshold) {
        List<MedicineResponse> medicines = medicineService.getLowStockMedicines(threshold);
        return ResponseEntity.ok(medicines);
    }
}