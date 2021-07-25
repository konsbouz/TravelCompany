package com.travelcompany.eshop.repositories;

import com.google.protobuf.StringValue;
import com.travelcompany.eshop.domain.*;
import com.travelcompany.eshop.service.ServiceException;
import org.apache.poi.ss.formula.functions.T;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class TicketsRepositoryTest {
    @BeforeEach
    void initialize(){
        Customer customer = new Customer(2,"Dimitriou Dimitrios","ddimitriou@mail.com","Athens","Greek", Category.Individual);
        Itinerary itinerary = new Itinerary(7,"ATH","DUB","2020-12-02 09:35:00.000","SkyLines",880);
    }

    @Test
    void addToDb() throws ServiceException, SQLException {
//        TicketsRepository ticketsRepository = new TicketsRepository();
//        Customer customer = new Customer(2,"Dimitriou Dimitrios","ddimitriou@mail.com","Athens","Greek", Category.Individual);
//        Itinerary itinerary = new Itinerary(7,"ATH","DUB","2020-12-02 09:35:00.000","SkyLines",880);
//        Ticket ticket = new Ticket(customer,itinerary, PaymentMethod.Cash,880);
//        int[] result = ticketsRepository.addToDb(ticket);
//        assertEquals(StringValue.of("[1]"),result);
    }

    @Test
    void getFromDB() throws ServiceException, SQLException {
        TicketsRepository ticketsRepository = new TicketsRepository();
        Customer customer = new Customer(2,"Dimitriou Dimitrios","ddimitriou@mail.com","Athens","Greek", Category.Individual);
        Itinerary itinerary = new Itinerary(7,"ATH","DUB","2020-12-02 09:35:00.000","SkyLines",880);
        PaymentMethod paymentMethod = PaymentMethod.Cash;
        Ticket ticket = new Ticket(13,customer,itinerary,paymentMethod,1056);
        assertEquals(ticket,ticketsRepository.getFromDB(13));


    }
}