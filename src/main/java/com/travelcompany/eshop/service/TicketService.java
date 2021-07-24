package com.travelcompany.eshop.service;

import com.travelcompany.eshop.domain.Customer;
import com.travelcompany.eshop.domain.Itinerary;
import com.travelcompany.eshop.domain.PaymentMethod;
import com.travelcompany.eshop.domain.Ticket;
import com.travelcompany.eshop.repositories.ItineraryRepository;
import com.travelcompany.eshop.repositories.TicketsRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TicketService {

    public static Ticket OrderTicket (Customer customer,Itinerary itinerary) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setCustomer(customer);
        ticket.setItinerary(itinerary);
        ticket.setPaymentMethod(PaymentMethod.Cash);
        ticket.setAmount(CalculateFinalPriceService.finalPriceCalculation(ticket));

        return ticket;
    }



    public static void StoreViaFile(String filename) throws IOException, SQLException {
        TicketsRepository ticketsRepository = new TicketsRepository();
        List<Ticket> tickets = CSVReaderService.TicketsReader(filename);

        for (Ticket ticket : tickets) {     ticketsRepository.addToDb(ticket);
        }
    }

    public static void StoreNewTicket(Ticket ticket) {
        TicketsRepository ticketsRepository = new TicketsRepository();

        try {
            ticketsRepository.addToDb(ticket);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

