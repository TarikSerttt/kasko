package com.example.kasko_firmasi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private String idNumber;

    @OneToMany(mappedBy = "customer")
    private List<Car> cars;
}
