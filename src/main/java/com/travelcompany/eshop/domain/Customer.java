package com.travelcompany.eshop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {


    private int id;
    private String name;
    private String email;
    private String addressCity;
    private String nationality;
    private Category customerCategory;

    public Customer(String name, String email, String addressCity, String nationality, Category customerCategory) {
        this.name = name;
        this.email = email;
        this.addressCity = addressCity;
        this.nationality = nationality;
        this.customerCategory = customerCategory;
    }
}