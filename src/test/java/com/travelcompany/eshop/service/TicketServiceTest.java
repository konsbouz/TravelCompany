package com.travelcompany.eshop.service;

import com.travelcompany.eshop.domain.Category;
import com.travelcompany.eshop.domain.Customer;
import com.travelcompany.eshop.domain.Itinerary;
import com.travelcompany.eshop.domain.PaymentMethod;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class TicketServiceTest {

    @Test
    void orderTicket() throws SQLException {
            Customer customer = new Customer(2,"Dimitriou Dimitrios","ddimitriou@mail.com","Athens","Greek", Category.Individual);
            Itinerary itinerary = new Itinerary(7,"ATH","DUB","2020-12-02 09:35:00.000","SkyLines",880);
            assertEquals(1055,TicketService.OrderTicket(customer,itinerary, PaymentMethod.Cash).getAmount());
        }
    }
