package com.travelcompany.eshop.report;
import com.travelcompany.eshop.domain.Customer;
import com.travelcompany.eshop.domain.OrderedTicket;
import com.travelcompany.eshop.domain.Purchase;
import com.travelcompany.eshop.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class ReportToScreen {
    Logger logger = LoggerFactory.getLogger(ReportToScreen.class.getName());

    CalculateReports calculateReports = new CalculateReports();

    public void totalNumberAndCostOfTickets() throws SQLException {
        List<OrderedTicket>  list1 = calculateReports.totalTicketsAndSumForAll();
        logger.info("The total tickes and cost of them for all customers : {}",list1);
    }

    public void totalOfferedItinerariesPerDestination() throws SQLException {
        String query1 = "select * from orderedtickets a inner join itineraries c on a.ItineraryId = c.Id order by DestinationAirportId ";
        calculateReports.findItineraries(query1);
        logger.info("The offered itineraries per destination are : {}" ,calculateReports.findItineraries(query1) );

    }

    public void totalOfferedItinerariesPerDeparture() throws SQLException {
        String query1 = "select * from orderedtickets a inner join itineraries c on a.ItineraryId = c.Id order by DepartureAirportId ";
        calculateReports.findItineraries(query1);
        logger.info("The offered itineraries per departure are : {}" ,calculateReports.findItineraries(query1) );

    }

    public void customersWithMostTickets() throws SQLException, ServiceException {
        String query = "select PassengerId , COUNT(PassengerId) AS MOST_FREQUENT from orderedtickets  GROUP BY PassengerId ORDER BY COUNT(PassengerId) desc limit 5";
        List<Purchase> list1 = calculateReports.findCustomersWithMostTickets();
        logger.info("Customer/s with most purchased tickets and the number of them respectively : {}",list1 );

    }

    public void customersWithNoTickets() throws SQLException {

        List<Customer> customerList = calculateReports.findCustomersWithNoTickets();
        logger.info("Customer/s with no purchased tickets is/are : {} ", customerList);


    }


}
