package com.example.kasko_firmasi.controller;

import com.example.kasko_firmasi.model.CarPriceCalculator;
import com.example.kasko_firmasi.service.CarPriceCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CarPriceCalculatorController {

    @Autowired
    private CarPriceCalculatorService carPriceCalculatorService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/calculate")
    public String calculatePrice(
            @RequestParam String name,
            @RequestParam int age,
            @RequestParam String carModel,
            @RequestParam int carYear,
            Model model) {

        // Kullanıcının verdiği araba bilgilerini alarak fiyatı hesapla
        CarPriceCalculator calculator = carPriceCalculatorService.calculatePriceFromInputs(name, age, carModel, carYear);

        // Hesaplanan fiyatı modele ekle
        model.addAttribute("price", calculator.getPrice());

        // index.html sayfasını geri döndür
        return "index";
    }
}
