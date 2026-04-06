package com.pharmacy.pharmacy_management.service.impl;

import com.pharmacy.pharmacy_management.dto.request.MedicineRequest;
import com.pharmacy.pharmacy_management.dto.response.MedicineResponse;
import com.pharmacy.pharmacy_management.entity.Medicine;
import com.pharmacy.pharmacy_management.repository.MedicineRepository;
import com.pharmacy.pharmacy_management.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public MedicineResponse addMedicine(MedicineRequest request) {
        Medicine medicine = new Medicine();
        medicine.setName(request.getName());
        medicine.setCompany(request.getCompany());
        medicine.setPrice(request.getPrice());
        medicine.setStock(request.getStock());
        medicine.setExpiryDate(request.getExpiryDate());
        medicine.setDescription(request.getDescription());

        Medicine savedMedicine = medicineRepository.save(medicine);
        return convertToResponse(savedMedicine);
    }

    @Override
    public List<MedicineResponse> getAllMedicines() {
        return medicineRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MedicineResponse getMedicineById(Long id) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found with id: " + id));
        return convertToResponse(medicine);
    }

    @Override
    public MedicineResponse updateMedicine(Long id, MedicineRequest request) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found with id: " + id));

        medicine.setName(request.getName());
        medicine.setCompany(request.getCompany());
        medicine.setPrice(request.getPrice());
        medicine.setStock(request.getStock());
        medicine.setExpiryDate(request.getExpiryDate());
        medicine.setDescription(request.getDescription());

        Medicine updatedMedicine = medicineRepository.save(medicine);
        return convertToResponse(updatedMedicine);
    }

    @Override
    public void deleteMedicine(Long id) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found with id: " + id));
        medicineRepository.delete(medicine);
    }

    @Override
    public List<MedicineResponse> searchMedicinesByName(String name) {
        return medicineRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicineResponse> getMedicinesByCompany(String company) {
        return medicineRepository.findByCompany(company)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicineResponse> getLowStockMedicines(Integer threshold) {
        return medicineRepository.findByStockLessThan(threshold)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private MedicineResponse convertToResponse(Medicine medicine) {
        return new MedicineResponse(
                medicine.getId(),
                medicine.getName(),
                medicine.getCompany(),
                medicine.getPrice(),
                medicine.getStock(),
                medicine.getExpiryDate(),
                medicine.getDescription()
        );
    }
}