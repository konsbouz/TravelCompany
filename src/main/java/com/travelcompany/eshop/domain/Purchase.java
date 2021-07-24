package com.travelcompany.eshop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Purchase {

    private Customer customer;
    private int numberOfPurchases;

    @Override
    public String toString() {
        return "{" +
                "Customer:  " + customer +
                ",Number Of Purchased tickets  : " + numberOfPurchases +
                '}';
    }
}
