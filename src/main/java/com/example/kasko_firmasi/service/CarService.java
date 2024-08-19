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

    @Autowired
    public CarService(CarRepository carRepository, CustomerRepository customerRepository) {
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    // Araba kaydetme
    public Car saveCar(Car car) {
        if (car.getCustomer() != null && car.getCustomer().getId() != null) {
            Customer customer = customerRepository.findById(car.getCustomer().getId()).orElse(null);
            car.setCustomer(customer);
        }
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
