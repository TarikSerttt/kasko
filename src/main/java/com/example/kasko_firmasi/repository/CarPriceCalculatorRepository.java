package com.example.kasko_firmasi.repository;

import com.example.kasko_firmasi.model.CarPriceCalculator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarPriceCalculatorRepository extends JpaRepository<CarPriceCalculator, Long> {

}
