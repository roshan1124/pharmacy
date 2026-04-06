package com.pharmacy.pharmacy_management.service;

import com.pharmacy.pharmacy_management.dto.request.MedicineRequest;
import com.pharmacy.pharmacy_management.dto.response.MedicineResponse;
import java.util.List;

public interface MedicineService {

    MedicineResponse addMedicine(MedicineRequest request);

    List<MedicineResponse> getAllMedicines();

    MedicineResponse getMedicineById(Long id);

    MedicineResponse updateMedicine(Long id, MedicineRequest request);

    void deleteMedicine(Long id);

    List<MedicineResponse> searchMedicinesByName(String name);

    List<MedicineResponse> getMedicinesByCompany(String company);

    List<MedicineResponse> getLowStockMedicines(Integer threshold);
}