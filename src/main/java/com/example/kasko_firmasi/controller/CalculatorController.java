package com.example.kasko_firmasi.controller;

import com.example.kasko_firmasi.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculate")
public class CalculatorController {  // Sınıf adını burada CalculatorController olarak ayarladık.

    private final CalculatorService calculatorService;

    @Autowired
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/{carYear}")
    public ResponseEntity<Double> calculatePrice(@PathVariable int carYear) {
        double price = calculatorService.calculateCarPrice(carYear);
        return ResponseEntity.ok(price);
    }

    @PostMapping("/confirm")
    public ResponseEntity<String> confirmPrice(@RequestBody double price) {
        boolean isConfirmed = calculatorService.confirmPrice(price);
        if (isConfirmed) {
            return ResponseEntity.ok("Price accepted");
        } else {
            return ResponseEntity.status(400).body("Price not accepted");
        }
    }
}
