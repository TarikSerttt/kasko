package com.example.kasko_firmasi.controller;

import com.example.kasko_firmasi.model.Car;
import com.example.kasko_firmasi.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public Car addCar(@RequestBody Car car) {
        return carService.saveCar(car);
    }

    @GetMapping("/{id}")
    public Car getCar(@PathVariable Long id) {
        return carService.getCarById(id);
    }

    @GetMapping
    public Iterable<Car> getAllCars() {
        return carService.getAllCars();
    }
}
//bir müşterinin hangi araçları var
//bir kişinin kimliği (tek olmalı)