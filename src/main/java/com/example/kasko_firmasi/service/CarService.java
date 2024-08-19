package com.example.kasko_firmasi.service;

import com.example.kasko_firmasi.model.Car;
import com.example.kasko_firmasi.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    // Araba kaydetme
    public Car saveCar(Car car) {
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
