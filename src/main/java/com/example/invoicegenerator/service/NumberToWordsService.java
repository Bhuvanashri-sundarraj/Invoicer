package com.example.invoicegenerator.service;

import org.springframework.stereotype.Service;

@Service
public class NumberToWordsService {

    private final String[] units = {
            "", "One", "Two", "Three", "Four", "Five",
            "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen",
            "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };

    private final String[] tens = {
            "", "", "Twenty", "Thirty", "Forty",
            "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };

    public String convert(int number) {

        if (number < 20) return units[number];

        if (number < 100)
            return tens[number / 10] + " " + units[number % 10];

        if (number < 1000)
            return units[number / 100] + " Hundred " + convert(number % 100);

        if (number < 100000)
            return convert(number / 1000) + " Thousand " + convert(number % 1000);

        if (number < 10000000)
            return convert(number / 100000) + " Lakh " + convert(number % 100000);

        return "";
    }
}