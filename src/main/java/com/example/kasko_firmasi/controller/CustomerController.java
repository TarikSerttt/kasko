package com.example.kasko_firmasi.controller;

import com.example.kasko_firmasi.model.PersonInfo;
import com.example.kasko_firmasi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/add")
    public PersonInfo addCustomer(@RequestBody PersonInfo personInfo) {
        return customerService.saveCustomer(personInfo);
    }

    @GetMapping("/all")
    public List<PersonInfo> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Optional<PersonInfo> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PutMapping("/update")
    public PersonInfo updateCustomer(@RequestBody PersonInfo personInfo) {
        return customerService.updateCustomer(personInfo);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);

    }
}
