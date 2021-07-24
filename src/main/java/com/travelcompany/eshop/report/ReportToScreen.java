package com.travelcompany.eshop.report;
import com.travelcompany.eshop.domain.Customer;
import com.travelcompany.eshop.domain.OrderedTicket;
import com.travelcompany.eshop.domain.Purchase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class ReportToScreen {
    Logger logger = LoggerFactory.getLogger(ReportToScreen.class.getName());

    public void totalNumberAndCostOfTickets() throws SQLException {

        CalculateMaxTicketsAndCostService calculateMaxTicketsAndCostService = new CalculateMaxTicketsAndCostService();
        List<OrderedTicket>  list1 = calculateMaxTicketsAndCostService.totalTicketsAndSumForAll();
        logger.info("The total tickes and cost of them for all customers : {}",list1);
    }

    public void totalOfferedItinerariesPerDestination() throws SQLException {
        String query1 = "select * from orderedtickets a inner join itineraries c on a.ItineraryId = c.Id order by DestinationAirportId ";
        FindIOfferedItineraries findIOfferedItineraries = new FindIOfferedItineraries();
        findIOfferedItineraries.findItineraries(query1);
        logger.info("The offered itineraries per destination are : {}" ,findIOfferedItineraries.findItineraries(query1) );

    }

    public void totalOfferedItinerariesPerDeparture() throws SQLException {
        String query1 = "select * from orderedtickets a inner join itineraries c on a.ItineraryId = c.Id order by DepartureAirportId ";
        FindIOfferedItineraries findIOfferedItineraries = new FindIOfferedItineraries();
        findIOfferedItineraries.findItineraries(query1);
        logger.info("The offered itineraries per departure are : {}" ,findIOfferedItineraries.findItineraries(query1) );

    }

    public void customersWithMostTickets() throws SQLException {
        String query = "select PassengerId , COUNT(PassengerId) AS MOST_FREQUENT from orderedtickets  GROUP BY PassengerId ORDER BY COUNT(PassengerId) desc limit 5";
        FindCustomersWithMostTickets findCustomersWithMostTickets = new FindCustomersWithMostTickets();
        List<Purchase> list1 = findCustomersWithMostTickets.FindCustomers();
        logger.info("Customer/s with most purchased tickets and the number of them respectively : {}",list1 );

    }

    public void customersWithNoTickets() throws SQLException {
        String query = "SELECT Id FROM   customers WHERE  Id NOT IN (SELECT PassengerId FROM orderedtickets)";
        FindCustomerWithNoTicket findCustomerWithNoTicket = new FindCustomerWithNoTicket();
        List<Customer> customerList = findCustomerWithNoTicket.FindCustomers();
        logger.info("Customer/s with no purchased tickets is/are : {} ", customerList);


    }


}
