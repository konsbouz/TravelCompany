package com.travelcompany.eshop.report;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.travelcompany.eshop.domain.Customer;
import com.travelcompany.eshop.domain.Itinerary;
import com.travelcompany.eshop.domain.OrderedTicket;
import com.travelcompany.eshop.domain.Purchase;
import com.travelcompany.eshop.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.util.List;

public class ReportToFile {
    Logger logger = LoggerFactory.getLogger(ReportToFile.class.getName());

    public void writeItinerariesToExcel(List<Itinerary> list, String filepath) throws FileNotFoundException {
        WriteToFileItineraries writeToFileItineraries = new WriteToFileItineraries();
        writeToFileItineraries.writeFilesToExcel(list, filepath);
        logger.info("\"Itineraries written successfully to file under the following path {}", filepath);

    }

    public void writeItinerariesToTxt(List<Itinerary> list, String filepath) throws FileNotFoundException {
        WriteToFileItineraries writeToFileItineraries = new WriteToFileItineraries();
        writeToFileItineraries.writeFilesToTxt(list, filepath);
        logger.info("Itineraries written successfully to file under the following path {}", filepath);

    }

    public void writeCustomersWithNoTicketsToExcel(List<Customer> list, String filepath) throws FileNotFoundException {
        WriteCustomersWithNoTicketsToFile writeCustomersWithNoTicketsToFile = new WriteCustomersWithNoTicketsToFile();
        writeCustomersWithNoTicketsToFile.writeFilesToExcel(list, filepath);
        logger.info("Customers written successfully to file under the following path {}", filepath);

    }

    public void writeCustomersWithNoTicketsToTxt(List<Customer> list, String filepath) throws FileNotFoundException {
        WriteCustomersWithNoTicketsToFile writeCustomersWithNoTicketsToFile = new WriteCustomersWithNoTicketsToFile();
        writeCustomersWithNoTicketsToFile.writeFilesToTxt(list, filepath);
        logger.info("Customers written successfully to file under the following path {}", filepath);
    }

    public void writeCustomerWithMostTicketsToExcel(List<Purchase> list, String filepath) throws FileNotFoundException {
        WriteCustomersWithMostTicketsToFile writeCustomersWithMostTicketsToFile = new WriteCustomersWithMostTicketsToFile();
        writeCustomersWithMostTicketsToFile.writeFilesToExcel(list, filepath);
        logger.info("Customer/s and number of tickets written successfully under the following path {}", filepath);

    }

    public void writeCustomerWithMostTicketsToTxt(List<Purchase> list, String filepath) throws FileNotFoundException {
        WriteCustomersWithMostTicketsToFile writeCustomersWithMostTicketsToFile = new WriteCustomersWithMostTicketsToFile();
        writeCustomersWithMostTicketsToFile.writeFilesToTxt(list, filepath);
        logger.info("Customer/s and number of tickets written successfully under the following path {}", filepath);


    }

    public void writeTotalTicketsAndSumToExcel(List<OrderedTicket> list, String filepath) throws FileNotFoundException, ServiceException {
        WriteCostAndTicketsToFile writeCostAndTicketsToFile = new WriteCostAndTicketsToFile();
        writeCostAndTicketsToFile.writeFilesToExcel(list,filepath);
        logger.info("Total tickets and total sum written successfully under the following path {}" , filepath);

    }
    public void writeTotalTicketsAndSumToTxt(List<OrderedTicket> list, String filepath) throws FileNotFoundException {
        WriteCostAndTicketsToFile writeCostAndTicketsToFile = new WriteCostAndTicketsToFile();
        writeCostAndTicketsToFile.writeFilesToTxt(list,filepath);
        logger.info("Total tickets and total sum written successfully under the following path {}" , filepath);

    }
}
