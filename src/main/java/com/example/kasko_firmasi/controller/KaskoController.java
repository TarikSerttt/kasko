package com.example.kasko_firmasi.controller;

import com.example.kasko_firmasi.service.KaskoService;
import com.example.kasko_firmasi.model.PersonInfo;
import com.example.kasko_firmasi.repository.PersonInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class KaskoController {

    @Autowired
    private KaskoService kaskoService;

    @Autowired
    private PersonInfoRepository personInfoRepository;

    // POST: Fiyat hesaplama
    @PostMapping("/calculate")
    public String calculateInsurance(@RequestBody PersonInfo personInfo) {
        double price = kaskoService.calculatePrice(personInfo);
        personInfoRepository.save(personInfo); // Kişiyi kaydedelim
        return "Calculated insurance price for " + personInfo.getName() + " is: " + price;
    }

    // request ıcınde customerId ıste
    // customer tablosundan customerId ıle uyusan kaydı getır
    // customer ıcındekı carId'yı cek ve onunla car tablosuna bır sorgu atarak car kaydına ulas
    // car tıpıne gore sıgorta ucretını hesapla
    // car kaydı sıgorta ucretı ve customer kaydını bırlestırıp clıent'a response gonderç

    // GET: ID'ye göre bir kişiyi getir
    @GetMapping("/person/{id}")
    public Optional<PersonInfo> getPersonInfo(@PathVariable Long id) {
        return personInfoRepository.findById(id);
    }

    // PUT: Var olan bir kişiyi güncelle
    @PutMapping("/person/{id}")
    public String updatePersonInfo(@PathVariable Long id, @RequestBody PersonInfo updatedPersonInfo) {
        Optional<PersonInfo> personInfo = personInfoRepository.findById(id);

        if (personInfo.isPresent()) {
            PersonInfo person = personInfo.get();
            person.setName(updatedPersonInfo.getName());
            person.setAge(updatedPersonInfo.getAge());
            person.setIdNumber(updatedPersonInfo.getIdNumber());
            person.setCarModel(updatedPersonInfo.getCarModel());
            personInfoRepository.save(person);
            return "Person information updated successfully!";
        } else {
            return "Person not found!";
        }
    }

    // DELETE: ID'ye göre bir kişiyi sil
    @DeleteMapping("/person/{id}")
    public String deletePersonInfo(@PathVariable Long id) {
        if (personInfoRepository.existsById(id)) {
            personInfoRepository.deleteById(id);
            return "Person deleted successfully!";
        } else {
            return "Person not found!";
        }
    }
}
