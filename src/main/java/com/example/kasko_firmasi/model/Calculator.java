package com.example.kasko_firmasi.model;

public class Calculator {

    private static final int BASE_PRICE = 20000;

    public static int calculatePrice(int carYear) {
        int currentYear = 2024;
        int age = currentYear - carYear;

        int price = BASE_PRICE + (age * 5000);

        return price;
    }

    public static boolean askCustomerAcceptance(int calculatedPrice) {

        return true;  //deneme
    }
}

