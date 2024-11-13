package com.freightFox.pdfDownloadService.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class PdfRequest {
    String seller;
    String sellerGstin;
    String sellerAddress;
    String buyer;
    String buyerGstin;
    String buyerAddress;
    List<Product> items;

    public PdfRequest(String seller, String sellerGstin, String sellerAddress, String buyer, String buyerGstin, String buyerAddress, List<Product> items) {
        this.seller = seller;
        this.sellerGstin = sellerGstin;
        this.sellerAddress = sellerAddress;
        this.buyer = buyer;
        this.buyerGstin = buyerGstin;
        this.buyerAddress = buyerAddress;
        this.items = List.copyOf(items);
    }

    public PdfRequest() {};

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getSellerGstin() {
        return sellerGstin;
    }

    public void setSellerGstin(String sellerGstin) {
        this.sellerGstin = sellerGstin;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getBuyerGstin() {
        return buyerGstin;
    }

    public void setBuyerGstin(String buyerGstin) {
        this.buyerGstin = buyerGstin;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = List.copyOf(items);
    }
}
