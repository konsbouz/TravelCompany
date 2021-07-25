package com.travelcompany.eshop.service;

import com.travelcompany.eshop.domain.Category;
import com.travelcompany.eshop.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    private CustomerService customerService;

    @BeforeEach
    void initialize(){
        Customer customer = new Customer("Kostas","@gmail","Athens","Greek", Category.Individual);


    }

    @Test
    @DisplayName("Add new customer to db")
    void storeNewCustomer() throws Exception {
        Customer customer = new Customer("Kostas","@gmail","Athens","Greek", Category.Individual);
        boolean result = customerService.storeNewCustomer(customer);
        assertTrue(result);

    }

    @Test
    void selectCustomerPerId() {
    }

    @Test
    void selectAllCustomers() {
    }
}