package com.travelcompany.eshop.report;

import com.travelcompany.eshop.domain.Customer;
import com.travelcompany.eshop.domain.Purchase;
import com.travelcompany.eshop.repositories.CustomerRepository;
import com.travelcompany.eshop.service.DBConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FindCustomersWithMostTickets {

    public static List<Purchase> FindCustomers() throws SQLException {
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
}

