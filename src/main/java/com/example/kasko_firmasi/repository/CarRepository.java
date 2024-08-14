package com.example.kasko_firmasi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.kasko_firmasi.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
