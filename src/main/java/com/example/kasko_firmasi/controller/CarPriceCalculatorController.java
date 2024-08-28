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
            @RequestParam String idNumber,
            @RequestParam int age,
            @RequestParam String carModel,
            @RequestParam int carYear,
            @RequestParam String brand,
            @RequestParam String licensePlate,
            Model model) {

        // Kullanıcının verdiği araba bilgilerini alarak fiyatı hesapla
        CarPriceCalculator calculator = carPriceCalculatorService.calculatePriceFromInputs(
                idNumber, age, carModel, carYear, brand, licensePlate);

        if (calculator == null) {
            model.addAttribute("message", "Fiyat hesaplanamadı. Lütfen bilgilerinizi kontrol edin.");
            return "index";
        }

        // Hesaplanan fiyatı modele ekle
        model.addAttribute("price", calculator.getPrice());
        model.addAttribute("calculatorId", calculator.getId());

        // Kullanıcıyı sonuç sayfasına yönlendir
        return "result";
    }

    @PostMapping("/confirm")
    public String confirmPrice(@RequestParam Long id, @RequestParam boolean isAccepted, Model model) {
        // Fiyatın kabul edilip edilmediğini onayla
        CarPriceCalculator calculator = carPriceCalculatorService.confirmPrice(id, isAccepted);

        if (calculator == null) {
            model.addAttribute("message", "Fiyat onayı alınamadı. Lütfen tekrar deneyin.");
            return "result";
        }

        model.addAttribute("price", calculator.getPrice());
        model.addAttribute("message", isAccepted ? "Fiyat kabul edildi!" : "Fiyat reddedildi.");

        // Kullanıcıyı sonuç sayfasına yönlendir
        return "result";
    }
}
