package com.travelcompany.eshop.domain;

public enum PaymentMethod {

    Cash(0.0,"Cash"), Credit_Card(0.1,"Credit Card");

    private double discount;
    private final String textvalue;

    PaymentMethod(double discount, String textvalue) {
        this.textvalue = textvalue;
        this.discount = discount;

    }

    public double getDiscount() {
        return discount;
    }
}

