package com.example.kasko_firmasi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.kasko_firmasi.model.Car;
import com.example.kasko_firmasi.repository.CarRepository;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    // Araba bilgilerini veritabanına kaydetme
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    // Araba bilgilerini ID ile bulma
    public Car getCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    // Tüm arabaları listeleme
    public Iterable<Car> getAllCars() {
        return carRepository.findAll();
    }

    // Araba bilgilerini güncelleme
    public Car updateCar(Long id, Car updatedCar) {
        if (carRepository.existsById(id)) {
            updatedCar.setId(id);
            return carRepository.save(updatedCar);
        }
        return null;
    }

    // Araba bilgilerini silme
    public void deleteCar(Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
        }
    }
}
