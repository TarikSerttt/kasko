package com.example.kasko_firmasi.service;

import org.springframework.stereotype.Service;
import com.example.kasko_firmasi.model.PersonInfo;

@Service
public class KaskoService {

    public double calculatePrice(PersonInfo personInfo) {
        // Burada fiyat hesaplama algoritması olacak
        double basePrice = 1000; // Örnek baz fiyat

        // Fiyat hesaplama algoritması
        double finalPrice = basePrice + (personInfo.getAge() * 10) + (personInfo.getCarModel().hashCode() % 100);

        return finalPrice;
    }
}
