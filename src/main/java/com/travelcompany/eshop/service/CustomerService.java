package com.travelcompany.eshop.service;
import com.travelcompany.eshop.domain.Customer;
import com.travelcompany.eshop.domain.Itinerary;
import com.travelcompany.eshop.domain.Ticket;
import com.travelcompany.eshop.repositories.CustomerRepository;
import com.travelcompany.eshop.repositories.ItineraryRepository;
import java.sql.SQLException;
import java.util.List;

public class CustomerService {


    public static void Purchase(String name,String destination) throws Exception {
        CustomerService customerService = new CustomerService();
        Customer customer= customerService.selectCustomerPerName(name);
        ItineraryRepository itineraryRepository = new ItineraryRepository();
        Itinerary itinerary=itineraryRepository.getFromDB1(destination);
        Ticket ticket = TicketService.OrderTicket(customer,itinerary);
        TicketService ticketService = new TicketService();
        ticketService.StoreNewTicket(ticket);
    }

    CustomerRepository customerRepository = new CustomerRepository();

    public boolean storeNewCustomer(Customer customer) throws Exception {

            customerRepository.addToDb(customer);
    return true;
    }

    public Customer selectCustomerPerId(int id) throws Exception{
        Customer customer = customerRepository.getFromDB(id);

        return customer;
    }

    public  Customer selectCustomerPerName(String name) throws Exception {
        Customer customer = customerRepository.getFromDB1(name);

        return customer;
    }
    public List <Customer> selectAllCustomers() throws SQLException, ServiceException {
        List<Customer> customerList = customerRepository.GetAllFromDb();

        return customerList;
    }
}
