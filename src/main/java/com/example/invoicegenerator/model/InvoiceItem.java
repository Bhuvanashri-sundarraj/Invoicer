package com.example.invoicegenerator.model;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

@Data
public class InvoiceItem {

    private String description;
    private double quantity;
    private double rate;

    private double round(double value) {
        return BigDecimal.valueOf(value)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    private String format(double value) {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("en", "IN"));
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);
        return formatter.format(value);
    }

    public double getAmountRaw() {
        return round(quantity * rate);
    }

    public String getAmount() {
        return format(getAmountRaw());
    }
}