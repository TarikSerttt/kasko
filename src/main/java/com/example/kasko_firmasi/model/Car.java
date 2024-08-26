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

    @NotBlank(message = "Aracınızın modelini giriniz.")
    private String model;

    @NotNull(message = "Aracınızın üretim yılını giriniz.")
    private int year;

    @NotBlank(message = "Plakanızı giriniz.")
    private String licensePlate;

    @NotBlank(message = "Marka bilgisi gerekli.")
    private String brand;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
