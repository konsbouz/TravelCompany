package com.travelcompany.eshop.service;
import com.travelcompany.eshop.domain.Customer;
import com.travelcompany.eshop.domain.Itinerary;
import com.travelcompany.eshop.domain.Ticket;
import com.travelcompany.eshop.repositories.CustomerRepository;
import com.travelcompany.eshop.repositories.ItineraryRepository;
import com.travelcompany.eshop.repositories.TicketsRepository;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StoreToDbService {

    public void storeCustomersViaFile(String filename) throws Exception {
        CustomerRepository customerRepository = new CustomerRepository();
        List<Customer> customers = CSVReaderService.CustomersReader(filename);

        for (Customer customer : customers) {
            customerRepository.addToDb(customer);
        }
    }

    public void storeItinerariesViaFile(String filename) throws Exception, SQLException {
        ItineraryRepository itineraryRepository = new ItineraryRepository();
        List<Itinerary> itineraries = CSVReaderService.ItinerariesReader(filename);

        for (Itinerary itinerary : itineraries) {
            itineraryRepository.addToDb(itinerary);

        }
    }

    public void storeTicketViaFile(String filename) throws Exception {
        TicketsRepository ticketsRepository = new TicketsRepository();

        List<Ticket> tickets = CSVReaderService.TicketsReader(filename);

        for (Ticket ticket : tickets) {
            ticketsRepository.addToDb(ticket);
        }
    }


}
