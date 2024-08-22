package com.example.kasko_firmasi.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;
    private int year;
    private String licensePlate;
    private String brand;
    private double price;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private boolean priceAccepted;

}
