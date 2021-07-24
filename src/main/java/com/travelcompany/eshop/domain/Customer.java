package com.travelcompany.eshop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {


    private int Id;
    private String Name;
    private String Email;
    private String AddressCity;
    private String Nationality;
    private Category CustomerCategory;


}