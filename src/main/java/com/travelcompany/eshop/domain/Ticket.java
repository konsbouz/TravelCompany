package com.travelcompany.eshop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    private int id;
    private Customer customer;
    private Itinerary itinerary;
    protected PaymentMethod paymentMethod;
    private double amount;

    public Ticket(Customer customer, Itinerary itinerary, PaymentMethod paymentMethod, double amount) {
        this.customer = customer;
        this.itinerary = itinerary;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
    }
}
