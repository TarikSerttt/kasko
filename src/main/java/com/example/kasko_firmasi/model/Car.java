package com.example.kasko_firmasi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;

    @NotNull(message = "Aracınızın yaşını giriniz.")
    private int year;

    @NotBlank(message = "Plakanızı giriniz")
    private String licensePlate;

    @NotBlank(message = "Marka gerekli")
    private String brand;

    private double price;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private boolean priceAccepted;

}
