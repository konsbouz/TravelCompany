package com.travelcompany.eshop.service;
import com.travelcompany.eshop.domain.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReaderService {
    Logger logger = LoggerFactory.getLogger(CSVReaderService.class.getName());

    public static List<Ticket> TicketsReader(String filename) throws IOException {

        CSVParser records = ParserService.CsvParser(filename);
        List<Ticket> tickets = new ArrayList<>();
        for (CSVRecord record : records) {
            Ticket ticket = new Ticket();
            ticket.setId(Integer.parseInt(record.get(0)));
            Customer customer = new Customer();
            customer.setId(Integer.parseInt(record.get(1)));
            ticket.setCustomer(customer);
            Itinerary itinerary = new Itinerary();
            itinerary.setId(Integer.parseInt(record.get(2)));
            ticket.setItinerary(itinerary);
            ticket.setPaymentMethod(PaymentMethod.valueOf((record.get(3).replace(" ", "_"))));
            ticket.setAmount(Double.parseDouble(record.get(4)));
            tickets.add(ticket);
        }
        return tickets;
    }

    public static List<Customer> CustomersReader(String filename) throws IOException {

        CSVParser records = ParserService.CsvParser(filename);
        List<Customer> customers = new ArrayList<>();
        for (CSVRecord record : records) {
            Customer customer = new Customer();
            customer.setId(Integer.parseInt(record.get(0)));
            customer.setName(record.get(1));
            customer.setEmail(record.get(2));
            customer.setAddressCity(record.get(3));
            customer.setNationality(record.get(4));
            customer.setCustomerCategory(Category.valueOf(record.get(5)));
            customers.add(customer);
        }
        return customers;
    }


    public static List<Itinerary> ItinerariesReader(String filename) throws IOException {

        CSVParser records = ParserService.CsvParser(filename);
        List<Itinerary> itineraries = new ArrayList<>();
        for (CSVRecord record : records) {
            Itinerary itinerary = new Itinerary();
            itinerary.setId(Integer.parseInt(record.get(0)));
            itinerary.setDepartureAirportId(record.get(1));
            itinerary.setDestinationAirportId(record.get(2));
            itinerary.setDepartureDate(record.get(3));
            itinerary.setAirlines(record.get(4));
            itinerary.setPrice(Double.parseDouble(record.get(5)));
            itineraries.add(itinerary);
        }
        return itineraries;
    }

}
