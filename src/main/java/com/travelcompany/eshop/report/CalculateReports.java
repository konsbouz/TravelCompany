package com.travelcompany.eshop.report;
import com.travelcompany.eshop.domain.*;
import com.travelcompany.eshop.repositories.CustomerRepository;
import com.travelcompany.eshop.service.DBConnectionService;
import com.travelcompany.eshop.service.ServiceException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CalculateReports {


    public List<OrderedTicket> totalTicketsAndSumForAll() throws SQLException {
        List<OrderedTicket> totalTicketsAndSum = new ArrayList<>();
        String query1 = "select count(*) from orderedtickets;";
        String query2 = "select sum(AmountPaid) from orderedtickets;";
        OrderedTicket orderedTicket = new OrderedTicket(CountTickets(query1),SumForTickets(query2));
        totalTicketsAndSum.add(orderedTicket);
        return totalTicketsAndSum;

    }
    public int CountTickets(String query) throws SQLException {
        PreparedStatement st = DBConnectionService.getConnection().prepareStatement(query);
        ResultSet resultSet = st.executeQuery();
        resultSet.next();
        int totaltickets;
        totaltickets = resultSet.getInt(1);
        return totaltickets;

    }

    public double SumForTickets(String query) throws SQLException {
        PreparedStatement st = DBConnectionService.getConnection().prepareStatement(query);
        ResultSet resultSet = st.executeQuery();
        resultSet.next();
        double sum;
        sum = resultSet.getDouble(1);
        return sum;

    }

    public List<Purchase> findCustomersWithMostTickets() throws SQLException, ServiceException {
       String query = "select PassengerId , COUNT(PassengerId) AS MOST_FREQUENT" +
                " from orderedtickets  GROUP BY PassengerId ORDER BY COUNT(PassengerId) desc limit 5";

        PreparedStatement st = DBConnectionService.getConnection().prepareStatement(query);
        ResultSet resultSet = st.executeQuery();
        List<Purchase> purchaseList = new ArrayList<>();
        CustomerRepository customerRepository = new CustomerRepository();
        int tickets = 0;
        while (resultSet.next()) {
            if (resultSet.getInt(2) >= tickets) {
                Purchase purchase = new Purchase();
                purchase.setNumberOfPurchases(resultSet.getInt(2));
                Customer customer = new Customer();
                customer.setId(resultSet.getInt(1));
                customerRepository.getFromDB(customer.getId());
                customer.setName(customerRepository.getFromDB(customer.getId()).getName());
                customer.setEmail(customerRepository.getFromDB(customer.getId()).getEmail());
                customer.setNationality(customerRepository.getFromDB(customer.getId()).getNationality());
                customer.setAddressCity(customerRepository.getFromDB(customer.getId()).getAddressCity());
                customer.setCustomerCategory(customerRepository.getFromDB(customer.getId()).getCustomerCategory());
                purchase.setCustomer(customer);
                purchaseList.add(purchase);
                tickets = resultSet.getInt(2);
            }
        }
        return purchaseList;
    }

    public  List<Customer> findCustomersWithNoTickets() throws SQLException {
        String query = "SELECT * FROM  customers WHERE  Id NOT IN (SELECT PassengerId FROM orderedtickets)";
        PreparedStatement st = DBConnectionService.getConnection().prepareStatement(query);
        ResultSet resultSet = st.executeQuery();
        List<Customer> customerList = new ArrayList<>();
        CustomerRepository customerRepository = new CustomerRepository();

        while(resultSet.next()){
            Customer customer = new Customer();
            customer.setId(resultSet.getInt(1));
            customer.setName(resultSet.getString(2));
            customer.setEmail(resultSet.getString(3));
            customer.setAddressCity(resultSet.getString(4));
            customer.setNationality(resultSet.getString(5));
            customer.setCustomerCategory(Category.valueOf(resultSet.getString(6)));
            customerList.add(customer);
        }
        return customerList;
    }

    public List<Itinerary> findItineraries(String query) throws SQLException {
        PreparedStatement st = DBConnectionService.getConnection().prepareStatement(query);
        ResultSet resultSet = st.executeQuery();
        List<Itinerary> finalList = new ArrayList<>();
        while (resultSet.next()) {
            Itinerary itinerary = new Itinerary();
            itinerary.setId(resultSet.getInt(6));
            itinerary.setDepartureAirportId(resultSet.getString(7));
            itinerary.setDestinationAirportId(resultSet.getString(8));
            itinerary.setDepartureDate(resultSet.getString(9));
            itinerary.setAirlines(resultSet.getString(10));
            itinerary.setPrice(resultSet.getDouble(11));
            finalList.add(itinerary);

        }
        return finalList;

    }

}
