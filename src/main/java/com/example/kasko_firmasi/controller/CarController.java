package com.example.kasko_firmasi.controller;

import com.example.kasko_firmasi.model.Car;
import com.example.kasko_firmasi.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<?> addCar(@Valid @RequestBody Car car, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        Car savedCar = carService.saveCar(car);
        return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable Long id) {
        Optional<Car> car = carService.getCarById(id);
        return car.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Iterable<Car>> getAllCars() {
        Iterable<Car> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id) {
        Optional<Car> existingCar = carService.getCarById(id);
        if (existingCar.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found with ID: " + id);
        }
        carService.deleteCarById(id);
        return ResponseEntity.ok("Car deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCar(@PathVariable Long id, @Valid @RequestBody Car carDetails, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        Optional<Car> existingCar = carService.getCarById(id);
        if (existingCar.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found with ID: " + id);
        }

        Car updatedCar = carService.updateCar(id, carDetails);
        return ResponseEntity.ok(updatedCar);
    }
}
