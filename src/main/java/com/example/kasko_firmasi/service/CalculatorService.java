package com.example.kasko_firmasi.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public double calculateCarPrice(int carYear) {
        int currentYear = 2024;
        int carAge = currentYear - carYear;

        double basePrice = 20000; // 2023 model araç için baz fiyat
        double depreciationRate = 5000; // Her yıl için fiyat düşüş miktarı

        double price = basePrice + (carAge * depreciationRate);

        return price;
    }

    public boolean confirmPrice(double price) {


        return true; // Şimdilik, fiyat her zaman onaylanmış gibi varsayıyoruz
    }
}
