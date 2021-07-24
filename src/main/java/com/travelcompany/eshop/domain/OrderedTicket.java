package com.travelcompany.eshop.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedTicket {

    private int tickets;
    private double sum;
    private Customer customer;



    public OrderedTicket(int tickets, Customer customer) {
        this.tickets = tickets;
        this.customer = customer;
    }

    public OrderedTicket(int tickets, double sum) {
        this.tickets = tickets;
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "{" +
                "Total number of purchased tickets : = " + tickets +
                ",Total cost of purchase tickets : = " + sum +
                '}';
    }
}
