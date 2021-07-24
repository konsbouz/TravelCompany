package com.travelcompany.eshop.service;

import com.travelcompany.eshop.domain.Customer;
import com.travelcompany.eshop.domain.Itinerary;
import com.travelcompany.eshop.domain.PaymentMethod;
import com.travelcompany.eshop.domain.Ticket;
import com.travelcompany.eshop.repositories.CustomerRepository;
import com.travelcompany.eshop.repositories.ItineraryRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CustomerService {


    public static void Purchase(String name,String destination) throws SQLException {
        CustomerService customerService = new CustomerService();
        Customer customer= customerService.selectcustomerPername(name);
        ItineraryRepository itineraryRepository = new ItineraryRepository();
        Itinerary itinerary=itineraryRepository.getFromDB1(destination);
        Ticket ticket = TicketService.OrderTicket(customer,itinerary);
        TicketService ticketService = new TicketService();
        ticketService.StoreNewTicket(ticket);
    }

    CustomerRepository customerRepository = new CustomerRepository();

    public void storeViaFile(String filename) throws Exception {

        List<Customer> customers = CSVReaderService.CustomersReader(filename);

        for (Customer customer : customers) {
            customerRepository.addToDb(customer);
        }
    }

    public void storeNewCustomer(Customer customer) throws Exception {


            customerRepository.addToDb(customer);

    }

    public Customer selectCustomerPerId(int id) throws SQLException {

        Customer customer = customerRepository.getFromDB(id);

        return customer;
    }

    public  Customer selectcustomerPername(String name) throws SQLException {
        Customer customer = customerRepository.getFromDB1(name);

        return customer;
    }
}
