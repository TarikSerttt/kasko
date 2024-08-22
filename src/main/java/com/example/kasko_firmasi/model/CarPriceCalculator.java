package com.example.kasko_firmasi.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity

public class CarPriceCalculator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double price;
    private boolean accepted; // Bu alan fiyatın kabul edilip edilmediğini gösterecek

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

}
