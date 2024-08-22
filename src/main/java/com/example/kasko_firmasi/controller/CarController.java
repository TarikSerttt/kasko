package com.example.kasko_firmasi.controller;

import com.example.kasko_firmasi.model.Car;
import com.example.kasko_firmasi.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
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
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCarById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/accept-price")
    public ResponseEntity<String> acceptPrice(@PathVariable Long id, @RequestBody boolean accept) {
        Optional<Car> carOptional = carService.getCarById(id);
        if (carOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Car car = carOptional.get();
        if (accept) {
            car.setPriceAccepted(true); // Fiyatın kabul edilme durumu güncelleniyor
            carService.updateCar(id, car); // Güncellenmiş araba veritabanına kaydediliyor
            return ResponseEntity.ok("Price accepted.");
        } else {
            car.setPriceAccepted(false); // Fiyatın reddedilme durumu güncelleniyor
            carService.updateCar(id, car); // Güncellenmiş araba veritabanına kaydediliyor
            return ResponseEntity.ok("Price declined.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCar(@PathVariable Long id, @RequestBody Car carDetails) {
        Optional<Car> existingCar = carService.getCarById(id);
        if (existingCar.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hatalı ID girildi");
        }
        Car updatedCar = carService.updateCar(id, carDetails);
        return ResponseEntity.ok(updatedCar);
    }
}
