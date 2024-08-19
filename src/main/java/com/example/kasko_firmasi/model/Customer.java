package com.example.kasko_firmasi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Customer {

    @Id

    private Long id;
    private String name;
    private int age;
    private String idNumber;

    // Müşteri ile araba arasında bir One-to-Many ilişkisi tanımlıyoruz
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Car> cars = new ArrayList<>();

    public void addCar(Car car) {
        this.cars.add(car);
        car.setCustomer(this); // Arabayı bu müşteri ile ilişkilendiriyoruz
    }
}
