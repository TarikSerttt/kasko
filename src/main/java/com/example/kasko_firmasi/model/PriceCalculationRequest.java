package com.example.kasko_firmasi.model;

import lombok.Data;

@Data
public class PriceCalculationRequest {
    private int carYear;
    private Long customerId;
}
