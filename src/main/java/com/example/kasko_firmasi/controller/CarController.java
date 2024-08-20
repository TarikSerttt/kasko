package com.example.kasko_firmasi.controller;

import com.example.kasko_firmasi.model.Car;
import com.example.kasko_firmasi.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        Car savedCar = carService.saveCar(car);
        return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable Long id) {
        Car car = carService.getCarById(id);
        return car != null ? ResponseEntity.ok(car) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Iterable<Car>> getAllCars() {
        Iterable<Car> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCarById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}/accept-price")
    public ResponseEntity<String> acceptPrice(@PathVariable Long id, @RequestBody boolean accept) {
        Car car = carService.getCarById(id);
        if (car == null) {
            return ResponseEntity.notFound().build();
        }
        if (accept) {
            return ResponseEntity.ok("Price accepted.");
        } else {
            return ResponseEntity.ok("Price declined.");
        }
    }

}
