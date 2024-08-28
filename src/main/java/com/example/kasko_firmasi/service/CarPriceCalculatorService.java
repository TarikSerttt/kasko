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

    // Araba bilgilerini alarak fiyatı hesaplayıp kaydetme işlemi
    public CarPriceCalculator calculatePriceFromInputs(String idNumber, int age, String carModel, int carYear, String brand, String licensePlate) {
        // Yeni bir Car nesnesi oluşturun ve bilgileri ayarlayın
        Car car = new Car();
        car.setModel(carModel); // Eksik model bilgisi eklendi
        car.setLicensePlate(licensePlate);
        car.setYear(carYear);
        car.setBrand(brand);

        // Arabayı veritabanına kaydedin
        car = carRepository.save(car);

        // Araba ID'sini kullanarak fiyatı hesaplayın ve kaydedin
        return calculateAndSavePrice(car.getId(), age, idNumber);
    }

    // Araba bilgilerini ve yaşa göre fiyat hesaplaması yaparak CarPriceCalculator'ı döndürme
    public CarPriceCalculator calculatePriceForCar(Car car, int age, String idNumber) {
        double price = calculateCarPrice(car, age); // Yaşa göre fiyat hesaplama
        CarPriceCalculator calculator = new CarPriceCalculator();
        calculator.setPrice(price);
        calculator.setAccepted(false); // Başlangıçta false olarak ayarlanıyor
        calculator.setCar(car);
        calculator.setIdNumber(idNumber); // Kimlik numarasını ayarla
        return carPriceCalculatorRepository.save(calculator);
    }

    // Arabanın fiyatını hesaplayıp kaydedin
    private CarPriceCalculator calculateAndSavePrice(Long carId, int age, String idNumber) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new RuntimeException("Car not found with ID: " + carId));
        double price = calculateCarPrice(car, age); // Yaşa göre fiyat hesaplama
        CarPriceCalculator calculator = new CarPriceCalculator();
        calculator.setPrice(price);
        calculator.setAccepted(false); // Başlangıçta false olarak ayarlanıyor
        calculator.setCar(car);
        calculator.setIdNumber(idNumber); // Kimlik numarasını ayarla
        return carPriceCalculatorRepository.save(calculator);
    }

    // Fiyatın kabul edilip edilmediğini onaylama işlemi
    public CarPriceCalculator confirmPrice(Long id, boolean isAccepted) {
        CarPriceCalculator calculator = carPriceCalculatorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Calculator not found with ID: " + id));
        calculator.setAccepted(isAccepted);
        return carPriceCalculatorRepository.save(calculator);
    }

    // Araba yaşı ve model yılına göre fiyat hesaplama işlemi
    private double calculateCarPrice(Car car, int age) {
        int currentYear = java.time.Year.now().getValue(); // Güncel yıl alındı
        int carAge = currentYear - car.getYear(); // Arabanın yaşı hesaplanıyor
        double basePrice = 20000; // Temel fiyat
        double price = basePrice - carAge * 1000 - age * 500; // Fiyat hesaplama
        return Math.max(price, 0); // Fiyat negatif olamaz
    }
}
