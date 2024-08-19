
//package com.example.kasko_firmasi.model;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import java.util.List;
//
//@Data
//@Entity
//public class CustomerCar {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    private Customer customer;

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "customer_car_id")
//    private List<Car> cars;
//}
