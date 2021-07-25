package com.travelcompany.eshop.service;
import com.travelcompany.eshop.domain.Customer;
import com.travelcompany.eshop.domain.Itinerary;
import com.travelcompany.eshop.domain.PaymentMethod;
import com.travelcompany.eshop.domain.Ticket;
import com.travelcompany.eshop.repositories.TicketsRepository;
import java.sql.SQLException;


public class TicketService {

    public static Ticket OrderTicket (Customer customer,Itinerary itinerary,PaymentMethod paymentMethod) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setCustomer(customer);
        ticket.setItinerary(itinerary);
        ticket.setPaymentMethod(paymentMethod);
        ticket.setAmount(CalculateFinalPriceService.finalPriceCalculation(ticket));

        return ticket;
    }

    public static void StoreNewTicket(Ticket ticket) {
        TicketsRepository ticketsRepository = new TicketsRepository();

        try {
            ticketsRepository.addToDb(ticket);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

