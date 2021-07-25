package com.travelcompany.eshop.service;

import com.travelcompany.eshop.domain.Customer;
import com.travelcompany.eshop.domain.Itinerary;
import com.travelcompany.eshop.domain.Ticket;
import com.travelcompany.eshop.repositories.CustomerRepository;
import com.travelcompany.eshop.repositories.ItineraryRepository;
import com.travelcompany.eshop.repositories.TicketsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.sql.SQLException;

public class RestoreDb {
    DirectoryAvailabilityService directoryAvailabilityService = new DirectoryAvailabilityService();

    Logger logger = LoggerFactory.getLogger(RestoreDb.class.getName());

    public void backUpCustomers(Path filepath) throws SQLException {
        if (directoryAvailabilityService.isAvailable(filepath)) {
            String filename = filepath + "/custoemersbackup.csv";
            try {
                PrintStream fw = new PrintStream(filename);
                CustomerRepository customerRepository = new CustomerRepository();

                for (Customer customer : customerRepository.GetAllFromDb()) {
                    fw.print(customer.getId());
                    fw.append(',');
                    fw.print(customer.getName());
                    fw.append(',');
                    fw.print(customer.getEmail());
                    fw.append(',');
                    fw.print(customer.getAddressCity());
                    fw.append(',');
                    fw.print(customer.getNationality());
                    fw.append(',');
                    fw.print(customer.getCustomerCategory());
                    fw.append('\n');
                }
                fw.flush();
                fw.close();
                DBConnectionService.getConnection().close();
                logger.info("Customers table stored successfully under the following path {}",filepath);
            } catch (FileNotFoundException | ServiceException e) {
                e.printStackTrace();
            }
        }
    }

    public void backUpItineraries(Path filepath) throws SQLException {
        if (directoryAvailabilityService.isAvailable(filepath)) {
            String filename = filepath + "/itinerariesbackup.csv";
            try {
                PrintStream fw = new PrintStream(filename);
                ItineraryRepository itineraryRepository = new ItineraryRepository();

                for (Itinerary itinerary : itineraryRepository.GetAllFromDb()) {
                    fw.print(itinerary.getId());
                    fw.append(',');
                    fw.print(itinerary.getDepartureAirportId());
                    fw.append(',');
                    fw.print(itinerary.getDestinationAirportId());
                    fw.append(',');
                    fw.print(itinerary.getDepartureDate());
                    fw.append(',');
                    fw.print(itinerary.getAirlines());
                    fw.append(',');
                    fw.print(itinerary.getPrice());
                    fw.append('\n');
                }
                fw.flush();
                fw.close();
                DBConnectionService.getConnection().close();
                logger.info("Itineraries table stored successfully under the following path {} ",filepath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void backUpOrderedTickets(Path filepath) throws SQLException {
        if (directoryAvailabilityService.isAvailable(filepath)) {
            String filename = filepath + "/orderedticektsbackup.csv";


            try {
                PrintStream fw = new PrintStream(filename);
                TicketsRepository ticketsRepository = new TicketsRepository();

                for (Ticket ticket : ticketsRepository.GetAllFromDb()) {
                    fw.print(ticket.getId());
                    fw.append(',');
                    fw.print(ticket.getCustomer().getId());
                    fw.append(',');
                    fw.print(ticket.getItinerary().getId());
                    fw.append(',');
                    fw.print(ticket.getPaymentMethod());
                    fw.append(',');
                    fw.print(ticket.getAmount());
                    fw.append('\n');

                }
                fw.flush();
                fw.close();
                DBConnectionService.getConnection().close();
                logger.info("Orderedtickets table stored successfully under the following path {} ", filepath);
            } catch (FileNotFoundException | ServiceException e) {
                e.printStackTrace();
            }
        }
    }

    public void backUpAll(Path filepath) throws SQLException {

        backUpCustomers(filepath);
        backUpOrderedTickets(filepath);
        backUpItineraries(filepath);

    }

}