package com.example.invoicegenerator.model;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.time.format.DateTimeFormatter;

@Data
public class Invoice {

    private String invoiceNumber;
    private LocalDate invoiceDate;
    private LocalDate buyerOrderDate;
    private String copyType;

    private String sellerName;
    private String sellerAddress;
    private String sellerGstin;
    private String sellerPan;

    private String buyerName;
    private String buyerAddressLine1;
    private String buyerAddressLine2;
    private String buyerAddressLine3;
    private String buyerGstin;
    private String buyerPan;

    private String modeOfPayment;
    private String dispatchThrough;

    private String bankDetails;
    private String terms;

    private List<InvoiceItem> items;

    public String getFormattedInvoiceDate() {
        return invoiceDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getFormattedBuyerOrderDate() {
        return buyerOrderDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    // ---------- ROUNDING HELPER ----------
    private double round(double value) {
        return BigDecimal.valueOf(value)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    // ---------- FORMAT HELPER ----------
    private String format(double value) {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("en", "IN"));
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);
        return formatter.format(value);
    }

    // ---------- CALCULATIONS ----------

    public double getTotalAmountRaw() {
        return round(items.stream()
                .mapToDouble(InvoiceItem::getAmountRaw)
                .sum());
    }

    public double getCgstRaw() {
        return round(getTotalAmountRaw() * 0.09);
    }

    public double getSgstRaw() {
        return round(getTotalAmountRaw() * 0.09);
    }

    public double getGrandTotalRaw() {
        return round(getTotalAmountRaw() + getCgstRaw() + getSgstRaw());
    }

    public double getTotalWeightRaw() {
        return round(items.stream()
                .mapToDouble(InvoiceItem::getQuantity)
                .sum());
    }

    // ---------- FORMATTED OUTPUT (FOR VIEW) ----------

    public String getTotalAmount() {
        return format(getTotalAmountRaw());
    }

    public String getCgst() {
        return format(getCgstRaw());
    }

    public String getSgst() {
        return format(getSgstRaw());
    }

    public String getGrandTotal() {
        return format(getGrandTotalRaw());
    }

    public String getTotalWeight() {
        return format(getTotalWeightRaw());
    }
}