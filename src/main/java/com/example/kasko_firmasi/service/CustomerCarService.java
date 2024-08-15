package com.example.kasko_firmasi.service;

import com.example.kasko_firmasi.model.CustomerCar;
import com.example.kasko_firmasi.repository.CustomerCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerCarService {

    private final CustomerCarRepository customerCarRepository;

    @Autowired
    public CustomerCarService(CustomerCarRepository customerCarRepository) {
        this.customerCarRepository = customerCarRepository;
    }

    public CustomerCar saveCustomerCar(CustomerCar customerCar) {
        return customerCarRepository.save(customerCar);
    }

    public CustomerCar getCustomerCarById(Long id) {
        return customerCarRepository.findById(id).orElse(null);
    }

    public List<CustomerCar> getAllCustomerCars() {
        return customerCarRepository.findAll();
    }

    public void deleteCustomerCar(Long id) {
        customerCarRepository.deleteById(id);
    }
}
