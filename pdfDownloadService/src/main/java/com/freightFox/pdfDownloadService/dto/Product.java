package com.freightFox.pdfDownloadService.dto;

import java.math.BigDecimal;

public class Product {
    String name;
    String quantity;
    BigDecimal rate;
    BigDecimal amount;

    public Product(String name, String quantity, BigDecimal rate, BigDecimal amount) {
        this.name = name;
        this.quantity = quantity;
        this.rate = rate;
        this.amount = amount;
    }

    public  Product(){};

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
