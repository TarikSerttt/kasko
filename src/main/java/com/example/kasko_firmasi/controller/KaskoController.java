//package com.example.kasko_firmasi.controller;

//import com.example.kasko_firmasi.repository.CustomerRepository;
//import com.example.kasko_firmasi.service.KaskoService;
//import com.example.kasko_firmasi.model.Customer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;

//import java.util.Optional;

//@RestController
//@RequestMapping("/api")
//public class KaskoController {

    //@Autowired
    //private KaskoService kaskoService;

    //@Autowired
    //private CustomerRepository personInfoRepository;

    // POST: Fiyat hesaplama
    //@PostMapping("/calculate")
    //public String calculateInsurance(@RequestBody Customer customer) {
        //double price = kaskoService.calculatePrice(customer);
        //personInfoRepository.save(customer); // Kişiyi kaydedelim
        //return "Calculated insurance price for " + customer.getName() + " is: " + price;
    //}

    // request ıcınde customerId ıste
    // customer tablosundan customerId ıle uyusan kaydı getır
    // customer ıcındekı carId'yı cek ve onunla car tablosuna bır sorgu atarak car kaydına ulas
    // car tıpıne gore sıgorta ucretını hesapla
    // car kaydı sıgorta ucretı ve customer kaydını bırlestırıp clıent'a response gonderç

    // GET: ID'ye göre bir kişiyi getir
    //@GetMapping("/person/{id}")
    //public Optional<Customer> getPersonInfo(@PathVariable Long id) {
        //return personInfoRepository.findById(id);}

    // PUT: Var olan bir kişiyi güncelle
    //@PutMapping("/person/{id}")
    //public String updatePersonInfo(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        //Optional<Customer> personInfo = personInfoRepository.findById(id);

        //if (personInfo.isPresent()) {
           // Customer person = personInfo.get();
            //person.setName(updatedCustomer.getName());
            //person.setAge(updatedCustomer.getAge());
            //person.setIdNumber(updatedCustomer.getIdNumber());
            //personInfoRepository.save(person);
            //return "Person information updated successfully!";
        //} else {
          //  return "Person not found!";
        //}
    //}

    // DELETE: ID'ye göre bir kişiyi sil
   // @DeleteMapping("/person/{id}")
    //public String deletePersonInfo(@PathVariable Long id) {
       // if (personInfoRepository.existsById(id)) {
         //   personInfoRepository.deleteById(id);
           // return "Person deleted successfully!";
        //} else {
  //          return "Person not found!";
        //}
    //}
//}
