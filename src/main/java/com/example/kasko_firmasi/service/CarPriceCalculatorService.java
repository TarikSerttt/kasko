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

    // Arabanın fiyatını hesaplayıp kaydetme işlemi
    public CarPriceCalculator calculateAndSavePrice(Long carId) {
        Car car = carRepository.findById(carId).orElse(null);
        if (car != null) {
            double price = calculateCarPrice(car);
            CarPriceCalculator calculator = new CarPriceCalculator();
            calculator.setPrice(price); // Hesaplanan fiyat atanıyor
            calculator.setAccepted(false); // Başlangıçta false olarak ayarlanıyor
            calculator.setCar(car);
            return carPriceCalculatorRepository.save(calculator);
        } else {
            throw new RuntimeException("Car not found");
        }
    }

    // Fiyatın kabul edilip edilmediğini onaylama işlemi
    public CarPriceCalculator confirmPrice(Long id, boolean isAccepted) {
        CarPriceCalculator calculator = carPriceCalculatorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Calculator not found"));
        calculator.setAccepted(isAccepted);
        return carPriceCalculatorRepository.save(calculator);
    }

    // Araba yaşı ve modeline göre fiyat hesaplama işlemi
    private double calculateCarPrice(Car car) {
        int currentYear = 2024;
        int carAge = currentYear - car.getYear(); // Arabanın yaşı hesaplanıyor
        double basePrice = 20000;
        double price = basePrice - carAge * 1000;
        return price;
    }

    // Girdilere göre fiyat hesaplama
    public CarPriceCalculator calculatePriceFromInputs(String brand, int year, String model, int mileage) {
        Car car = new Car();
        car.setBrand(brand);
        car.setYear(year);
        car.setModel(model);


        double price = calculateCarPrice(car);
        CarPriceCalculator calculator = new CarPriceCalculator();
        calculator.setPrice(price); // Hesaplanan fiyat atanıyor
        calculator.setAccepted(false); // Başlangıçta false olarak ayarlanıyor
        calculator.setCar(car);

        return calculator;
    }
}
