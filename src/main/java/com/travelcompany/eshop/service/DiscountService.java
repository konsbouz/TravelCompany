package com.travelcompany.eshop.service;

import com.travelcompany.eshop.domain.Category;
import com.travelcompany.eshop.domain.Customer;
import com.travelcompany.eshop.domain.PaymentMethod;
import com.travelcompany.eshop.domain.Ticket;

public class DiscountService {

    public static double CalculateDiscount(Category category,PaymentMethod paymentMethod ){
        double discount = 0.0;

        discount = category.getDiscount() + paymentMethod.getDiscount();


//        if(category==Category.Business){
//
//            discount = Category.Business.getDiscount();
//        }
//        else {
//
//            discount = Category.Individual.getDiscount();
//        }
//
//        if(paymentMethod == PaymentMethod.Cash){
//
//            discount += PaymentMethod.Cash.getDiscount();
//        }
//        else  {
//
//            discount += PaymentMethod.Credit_Card.getDiscount();
//        }

        return discount;
    }

}
