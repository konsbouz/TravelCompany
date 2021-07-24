package com.travelcompany.eshop.service;
import com.travelcompany.eshop.domain.Category;
import com.travelcompany.eshop.domain.PaymentMethod;
import com.travelcompany.eshop.domain.Ticket;

public class CalculateFinalPriceService {

    public static double finalPriceCalculation(Ticket ticket){

        double price = ticket.getItinerary().getPrice();
        Category category = ticket.getCustomer().getCustomerCategory();
        PaymentMethod paymentMethod = ticket.getPaymentMethod();
        double finalPrice = price - price* DiscountService.CalculateDiscount(category,paymentMethod );

        return finalPrice;
    }

}
