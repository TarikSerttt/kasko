package com.example.kasko_firmasi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;


@Data
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Aracınızın modelini giriniz.")
    private String model;

    @NotNull(message = "Aracınızın üretim yılını giriniz.")
    private int year;

    @NotBlank(message = "Plakanızı giriniz.")
    private String licensePlate;

    @NotBlank(message = "Marka bilgisi gerekli.")
    private String brand;


    private double price;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "car")
    private List<CarPriceCalculator> carPriceCalculators;

}
