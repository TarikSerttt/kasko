package com.example.kasko_firmasi.service;

import com.example.kasko_firmasi.model.Car;
import com.example.kasko_firmasi.model.CarPriceCalculator;
import com.example.kasko_firmasi.repository.CarRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@Validated
public class CarService {

    @Autowired
    private CarPriceCalculatorService carPriceCalculatorService;

    @Autowired
    private CarRepository carRepository;

    public Car saveCar(@Valid Car car, String idNumber) {
        // Arabayı veritabanına kaydet
        Car savedCar = carRepository.save(car);

        // Arabanın fiyatını hesaplayıp CarPriceCalculator'a kaydet
        CarPriceCalculator calculator = carPriceCalculatorService.calculatePriceForCar(savedCar, car.getYear(), idNumber);

        // Hesaplanan fiyatı arabaya ayarla
        savedCar.setPrice(calculator.getPrice());

        // Arabayı tekrar güncelle ve kaydet
        return carRepository.save(savedCar);
    }

    public Optional<Car> getCarById(@NotNull Long id) {
        return carRepository.findById(id);
    }

    public Iterable<Car> getAllCars() {
        return carRepository.findAll();
    }

    public void deleteCarById(@NotNull Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
        } else {
            // Eğer araba bulunamazsa uygun bir işlem yapılabilir
            throw new RuntimeException("Car not found with ID: " + id);
        }
    }

    public Car updateCar(@NotNull Long id, @Valid Car carDetails, String idNumber) {
        if (carRepository.existsById(id)) {
            carDetails.setId(id);

            // Arabayı güncelle ve kaydet
            Car updatedCar = carRepository.save(carDetails);

            // Fiyatı yeniden hesapla ve CarPriceCalculator'a kaydet
            CarPriceCalculator calculator = carPriceCalculatorService.calculatePriceForCar(updatedCar, carDetails.getYear(), idNumber);

            // Güncellenen fiyatı arabaya ayarla
            updatedCar.setPrice(calculator.getPrice());

            return carRepository.save(updatedCar);
        } else {
            throw new RuntimeException("Car not found with ID: " + id);
        }
    }
}
