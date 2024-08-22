package com.example.kasko_firmasi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;


import java.util.List;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Isminizi giriniz.")
    private String name;

    @NotNull(message = "Yaş boş olamaz.")
    @Positive(message = "Yaş pozitif bir sayı olmalıdır.")
    private int age;


    @NotBlank(message = "ID numarasını giriniz.")
    @Size(min = 11, max = 11, message = "Kimlik numarası 11 karakter uzunluğunda olmalıdır.")
    private String idNumber;

    @OneToMany(mappedBy = "customer")
    private List<Car> cars;
}
