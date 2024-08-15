package com.example.kasko_firmasi.service;

import com.example.kasko_firmasi.model.Customer;
import com.example.kasko_firmasi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Müşteri kaydetme
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Tüm müşterileri listeleme
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Belirli bir müşteriyi ID'sine göre bulma
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    // Müşteri bilgilerini güncelleme
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Müşteri silme
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
