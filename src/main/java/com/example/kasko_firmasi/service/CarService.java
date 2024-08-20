package com.example.kasko_firmasi.service;

import com.example.kasko_firmasi.model.Car;
import com.example.kasko_firmasi.model.Customer;
import com.example.kasko_firmasi.repository.CarRepository;
import com.example.kasko_firmasi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final CalculatorService calculatorService; // Burada CalculatorService'i dahil ediyoruz

    @Autowired
    public CarService(CarRepository carRepository, CustomerRepository customerRepository, CalculatorService calculatorService) {
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.calculatorService = calculatorService;
    }

    // Araba kaydetme
    public Car saveCar(Car car) {
        if (car.getCustomer() != null && car.getCustomer().getId() != null) {
            Customer customer = customerRepository.findById(car.getCustomer().getId()).orElse(null);
            car.setCustomer(customer);
        }

        // Araba fiyatını hesaplıyoruz
        double price = calculatorService.calculateCarPrice(car.getYear());
        System.out.println("Hesaplanan Fiyat: " + price); // Fiyatı çıktı olarak alıyoruz

        return carRepository.save(car);
    }

    // ID ile araba bulma
    public Car getCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    // Tüm arabaları listeleme
    public Iterable<Car> getAllCars() {
        return carRepository.findAll();
    }

    // ID ile araba silme
    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }
}
