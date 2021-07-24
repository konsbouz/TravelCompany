package com.travelcompany.eshop.report;

import com.travelcompany.eshop.domain.Customer;
import com.travelcompany.eshop.repositories.CustomerRepository;
import com.travelcompany.eshop.service.DBConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FindCustomerWithNoTicket {
    Logger logger = LoggerFactory.getLogger(FindCustomerWithNoTicket.class.getName());
    String query ="select PassengerId , COUNT(PassengerId) AS MOST_FREQUENT from orderedtickets " +
            "GROUP BY PassengerId ORDER BY COUNT(PassengerId) desc limit 5";
    public  List<Customer> FindCustomers() throws SQLException {

        PreparedStatement st = DBConnectionService.getConnection().prepareStatement(query);
        ResultSet resultSet = st.executeQuery();
        List<Customer> customerList = new ArrayList<>();
        CustomerRepository customerRepository = new CustomerRepository();

        while(resultSet.next()){
            Customer customer = new Customer();
            customer.setId(resultSet.getInt(1));
            customer.setName(customerRepository.getFromDB(customer.getId()).getName());
            customer.setEmail(customerRepository.getFromDB(customer.getId()).getEmail());
            customer.setAddressCity(customerRepository.getFromDB(customer.getId()).getAddressCity());
            customer.setNationality(customerRepository.getFromDB(customer.getId()).getAddressCity());
            customer.setCustomerCategory(customerRepository.getFromDB(customer.getId()).getCustomerCategory());
            customerList.add(customer);
        }

        return customerList;
    }


}
