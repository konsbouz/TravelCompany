//package com.travelcompany.eshop.service;
//
//import com.travelcompany.eshop.domain.Customer;
//import com.travelcompany.eshop.repositories.CustomerRepository;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.List;
//
//public class StoreTodbCustomersService {
//    CustomerRepository customerRepository = new CustomerRepository();
//
//    public void StoreViaFile(String filename) throws IOException, SQLException {
//
//        List<Customer> customers = CSVReaderService.CustomersReader(filename);
//
//        for (Customer customer : customers) {
//            customerRepository.addToDb(customer);
//        }
//    }
//
//    public void StoreNewCustomer(Customer customer) {
//
//        try {
//            customerRepository.addToDb(customer);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
