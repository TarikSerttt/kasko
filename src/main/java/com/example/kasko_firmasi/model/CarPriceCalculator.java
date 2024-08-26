package com.example.kasko_firmasi.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class CarPriceCalculator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double price;

    private boolean accepted;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
}
