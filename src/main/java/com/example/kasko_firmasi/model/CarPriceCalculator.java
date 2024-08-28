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

    @Column(nullable = false)
    private boolean accepted;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String carModel;

    @Column(nullable = false)
    private int carYear;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String licensePlate;

    @Column(nullable = false, unique = true, length = 11)
    private String idNumber; // Kimlik numarası


}
