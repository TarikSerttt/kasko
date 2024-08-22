package com.example.kasko_firmasi.service;

import com.example.kasko_firmasi.model.Car;
import com.example.kasko_firmasi.model.CarPriceCalculator;
import com.example.kasko_firmasi.repository.CarPriceCalculatorRepository;
import com.example.kasko_firmasi.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarPriceCalculatorService {
    @Autowired
    private CarPriceCalculatorRepository carPriceCalculatorRepository;

    @Autowired
    private CarRepository carRepository;

    public CarPriceCalculator calculateAndSavePrice(Long carId) {
        Car car = carRepository.findById(carId).orElse(null);
        if (car != null) {
            double price = calculateCarPrice(car);
            CarPriceCalculator calculator = new CarPriceCalculator();
            calculator.setPrice(price);
            calculator.setAccepted(false); // Başlangıçta false olarak ayarlanır
            calculator.setCar(car);
            return carPriceCalculatorRepository.save(calculator);
        } else {
            throw new RuntimeException("Car not found");
        }
    }

    public CarPriceCalculator confirmPrice(Long id, boolean isAccepted) {
        CarPriceCalculator calculator = carPriceCalculatorRepository.findById(id).orElseThrow(() -> new RuntimeException("Calculator not found"));
        calculator.setAccepted(isAccepted);
        return carPriceCalculatorRepository.save(calculator);
    }

    private double calculateCarPrice(Car car) {
        int currentYear = 2024;
        int carAge = currentYear - car.getYear();
        double basePrice = 20000 - carAge * 1000;
        return basePrice;
    }
}

