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



}
