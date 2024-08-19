package com.example.kasko_firmasi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Car {

    @Id

    private Long id;

    private String model;
    private String brand;
    private int year;
    private String licensePlate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
