package com.example.kasko_firmasi.service;

import com.example.kasko_firmasi.model.Car;
import com.example.kasko_firmasi.model.CarPriceCalculator;
import com.example.kasko_firmasi.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarPriceCalculatorService carPriceCalculatorService;

    @Autowired
    private CarRepository carRepository;

    public Car saveCar(Car car) {
        // Arabayı veritabanına kaydet
        Car savedCar = carRepository.save(car);

        // Arabanın fiyatını hesaplayıp CarPriceCalculator'a kaydet
        CarPriceCalculator calculator = carPriceCalculatorService.calculateAndSavePrice(savedCar.getId());

        // Hesaplanan fiyatı arabaya ayarla
        savedCar.setPrice(calculator.getPrice());

        // Arabayı tekrar güncelle ve kaydet
        return carRepository.save(savedCar);
    }

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    public Iterable<Car> getAllCars() {
        return carRepository.findAll();
    }

    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }

    public Car updateCar(Long id, Car carDetails) {
        if (carRepository.existsById(id)) {
            carDetails.setId(id);

            // Arabayı güncelle ve kaydet
            Car updatedCar = carRepository.save(carDetails);

            // Fiyatı yeniden hesapla ve CarPriceCalculator'a kaydet
            CarPriceCalculator calculator = carPriceCalculatorService.calculateAndSavePrice(updatedCar.getId());

            // Güncellenen fiyatı arabaya ayarla
            updatedCar.setPrice(calculator.getPrice());

            return carRepository.save(updatedCar);
        }
        return null; // veya uygun bir hata işleme mekanizması
    }
}
