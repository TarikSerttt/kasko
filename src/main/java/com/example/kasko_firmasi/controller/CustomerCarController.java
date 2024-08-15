package com.example.kasko_firmasi.controller;

import com.example.kasko_firmasi.model.CustomerCar;
import com.example.kasko_firmasi.service.CustomerCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer-cars")
public class CustomerCarController {

    private final CustomerCarService customerCarService;

    @Autowired
    public CustomerCarController(CustomerCarService customerCarService) {
        this.customerCarService = customerCarService;
    }

    @PostMapping
    public CustomerCar createCustomerCar(@RequestBody CustomerCar customerCar) {
        return customerCarService.saveCustomerCar(customerCar);
    }

    @GetMapping("/{id}")
    public CustomerCar getCustomerCarById(@PathVariable Long id) {
        return customerCarService.getCustomerCarById(id);
    }

    @GetMapping
    public List<CustomerCar> getAllCustomerCars() {
        return customerCarService.getAllCustomerCars();
    }

    @DeleteMapping("/{id}")
    public void deleteCustomerCar(@PathVariable Long id) {
        customerCarService.deleteCustomerCar(id);
    }
}
