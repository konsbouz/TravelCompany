package com.travelcompany.eshop.service;

import com.travelcompany.eshop.domain.*;
import com.travelcompany.eshop.repositories.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    @Test
    void purchase()  {

    }

    @Test
    @DisplayName("Add new customer to db")
    void storeNewCustomer() throws Exception {
        CustomerService customerService = new CustomerService();
        Customer customer = new Customer("konstantinos ","ddimitriou@mail.com","Athens","Greek", Category.Individual);
        boolean result = customerService.storeNewCustomer(customer);
        assertTrue(result);
    }

    @Test
    void selectCustomerPerId() throws ServiceException, SQLException {
        CustomerRepository customerRepository = new CustomerRepository();
        Customer customer = new Customer(2,"Dimitriou Dimitrios","ddimitriou@mail.com","Athens","Greek", Category.Individual);
        assertEquals(customer,customerRepository.getFromDB(2));

    }

    @Test
    void selectCustomerPerName() throws ServiceException, SQLException {
        CustomerRepository customerRepository = new CustomerRepository();
        Customer customer = new Customer(2,"Dimitriou Dimitrios","ddimitriou@mail.com","Athens","Greek", Category.Individual);
        assertEquals(customer,customerRepository.getFromDB1("Dimitriou Dimitrios"));
    }

    @Test
    void selectAllCustomers() {
    }
}