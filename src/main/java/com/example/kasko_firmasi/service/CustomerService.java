package com.example.kasko_firmasi.service;

import com.example.kasko_firmasi.model.Customer;
import com.example.kasko_firmasi.repository.CustomerRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Müşteri kaydetme
    public Customer saveCustomer(@Valid Customer customer) {
        validateCustomer(customer);
        return customerRepository.save(customer);
    }

    // Tüm müşterileri listeleme
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Belirli bir müşteriyi ID'sine göre bulma
    public Optional<Customer> getCustomerById(@NotNull Long id) {
        return customerRepository.findById(id);
    }

    // Müşteri güncelleme veya hata döndürme
    public Customer updateCustomer(@NotNull Long id, @Valid Customer customerDetails) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Hatalı ID: Müşteri bulunamadı"));

        validateCustomer(customerDetails);

        // Mevcut müşteri bilgilerini güncelle
        existingCustomer.setName(customerDetails.getName());
        existingCustomer.setAge(customerDetails.getAge());

        if (customerDetails.getIdNumber() != null) {
            existingCustomer.setIdNumber(customerDetails.getIdNumber());
        }

        return customerRepository.save(existingCustomer);
    }

    // Müşteri silme
    public void deleteCustomer(@NotNull Long id) {
        if (!customerRepository.existsById(id)) {
            throw new IllegalArgumentException("Hatalı ID: Müşteri bulunamadı");
        }
        customerRepository.deleteById(id);
    }

    // Müşteri doğrulama metodu
    private void validateCustomer(Customer customer) {
        if (customer.getName() == null || customer.getName().isEmpty()) {
            throw new IllegalArgumentException("Müşteri adı boş olamaz.");
        }
        if (customer.getAge() <= 0) {
            throw new IllegalArgumentException("Yaş 0'dan büyük olmalıdır.");
        }
        if (customer.getIdNumber() != null && (customer.getIdNumber().length() != 11 || !Pattern.matches("\\d+", customer.getIdNumber()))) {
            throw new IllegalArgumentException("ID numarası 11 haneli ve sadece rakamlardan oluşmalıdır.");
        }
    }
}
