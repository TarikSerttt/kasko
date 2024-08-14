package com.example.kasko_firmasi.service;

import com.example.kasko_firmasi.model.PersonInfo;
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
    public PersonInfo saveCustomer(PersonInfo personInfo) {
        return customerRepository.save(personInfo);
    }

    // Tüm müşterileri listeleme
    public List<PersonInfo> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Belirli bir müşteriyi ID'sine göre bulma
    public Optional<PersonInfo> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    // Müşteri bilgilerini güncelleme
    public PersonInfo updateCustomer(PersonInfo personInfo) {
        return customerRepository.save(personInfo);
    }

    // Müşteri silme
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
