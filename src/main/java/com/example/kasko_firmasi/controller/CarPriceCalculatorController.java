package com.example.kasko_firmasi.controller;

import com.example.kasko_firmasi.model.CarPriceCalculator;
import com.example.kasko_firmasi.service.CarPriceCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculate")
public class CarPriceCalculatorController {

    @Autowired
    private CarPriceCalculatorService carPriceCalculatorService;

    // Fiyat hesaplama ve kaydetme
    @PostMapping("/{carId}")
    public ResponseEntity<CarPriceCalculator> calculatePrice(@PathVariable Long carId) {
        CarPriceCalculator result = carPriceCalculatorService.calculateAndSavePrice(carId);
        return ResponseEntity.ok(result);
    }

    // Fiyatın kabul edilip edilmediğinin onayı
    @PutMapping("/confirm/{id}")
    public ResponseEntity<CarPriceCalculator> confirmPrice(@PathVariable Long id, @RequestParam boolean isAccepted) {
        CarPriceCalculator result = carPriceCalculatorService.confirmPrice(id, isAccepted);
        return ResponseEntity.ok(result);
    }
}
