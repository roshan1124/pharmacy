package com.pharmacy.pharmacy_management.dto.request;

import java.time.LocalDate;

public class MedicineRequest {

    private String name;
    private String company;
    private Double price;
    private Integer stock;
    private LocalDate expiryDate;
    private String description;

    // Constructors
    public MedicineRequest() {}

    public MedicineRequest(String name, String company, Double price, Integer stock, LocalDate expiryDate, String description) {
        this.name = name;
        this.company = company;
        this.price = price;
        this.stock = stock;
        this.expiryDate = expiryDate;
        this.description = description;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}