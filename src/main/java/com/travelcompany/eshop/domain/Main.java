package com.travelcompany.eshop.domain;
import com.travelcompany.eshop.database.RestoreDb;
import com.travelcompany.eshop.database.StoreToDbService;
import com.travelcompany.eshop.report.*;
import com.travelcompany.eshop.repositories.CustomerRepository;
import com.travelcompany.eshop.service.*;
import com.travelcompany.eshop.directory.Directory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args)  {
        Logger logger = LoggerFactory.getLogger(Main.class.getName());

        String perDestination = "select * from orderedtickets a inner join itineraries c on a.ItineraryId = c.Id order by DestinationAirportId";
        String perDeparture = "select * from orderedtickets a inner join itineraries c on a.ItineraryId = c.Id order by DepartureAirportId";


        try {
            Path filepath1 = Paths.get(Directory.BACK_UP_DIRECTORY.getPath());
            DirectoryAvailabilityService directoryAvailabilityService = new DirectoryAvailabilityService();
            if(directoryAvailabilityService.isAvailable(filepath1)){
                logger.info("System is able to backup the database");

            }
            else {
                logger.error("The configured path for database backup is not valid.");
                System.exit(-1);
            }


//Write files to db
            StoreToDbService storeToDbService = new StoreToDbService();
            storeToDbService.storeCustomersViaFile("customers.csv");
            storeToDbService.storeItinerariesViaFile("itineraries.csv");
            storeToDbService.storeTicketViaFile("orderedtickets.csv");

//Screen reporting
            ReportToScreen reportToScreen = new ReportToScreen();
            reportToScreen.totalNumberAndCostOfTickets();
            reportToScreen.customersWithMostTickets();
            reportToScreen.customersWithNoTickets();
            reportToScreen.totalOfferedItinerariesPerDeparture();
            reportToScreen.totalOfferedItinerariesPerDestination();


//Reports toFiles
            String filepath = Directory.FILE_DIRECTORY.getPath();
            ReportToFile reportToFile = new ReportToFile();
            CalculateReports calculateReports = new CalculateReports();

            //Write Itineraries per destination/ per departure
            List<Itinerary> list1 = calculateReports.findItineraries(perDestination);
            List<Itinerary> list2 = calculateReports.findItineraries(perDeparture);
            reportToFile.writeItinerariesToExcel(list1, filepath + "itinerariesperdestination.xls");
            reportToFile.writeItinerariesToTxt(list1, filepath + "itinerariesperdestination.txt");
            reportToFile.writeItinerariesToTxt(list2, filepath + "itinerariesperdeparture.txt");
            reportToFile.writeItinerariesToExcel(list2, filepath + "itinerariesperdeparture.xls");


            //Write to File CustomersWithNoTickets
            List<Customer> list3 = calculateReports.findCustomersWithNoTickets();
            reportToFile.writeCustomersWithNoTicketsToExcel(list3, filepath + "noticket.xls");
            reportToFile.writeCustomersWithNoTicketsToTxt(list3, filepath + "notickets.txt");

            //Write to File Customer/s with most tickets
            List<Purchase> list4 = calculateReports.findCustomersWithMostTickets();
            reportToFile.writeCustomerWithMostTicketsToExcel(list4, filepath + "mosttickets.xls");
            reportToFile.writeCustomerWithMostTicketsToTxt(list4, filepath + "mosttickets.txt");


            //Write to File Max tickets and sum
            List<OrderedTicket> list5 = calculateReports.totalTicketsAndSumForAll();
            reportToFile.writeTotalTicketsAndSumToExcel(list5, filepath + "maxticketsandsum.xls");
            reportToFile.writeTotalTicketsAndSumToTxt(list5, filepath + "maxticketsandsum.txt");

            //Purchase senario
           // CustomerService.Purchase("Dimitriou Dimitrios","DUB");


// RestoreDB
            RestoreDb restoreDb = new RestoreDb();
            restoreDb.backUpCustomers(filepath1);
            restoreDb.backUpItineraries(filepath1);
            restoreDb.backUpOrderedTickets(filepath1);
            restoreDb.backUpAll(filepath1);


            CustomerRepository customerRepository = new CustomerRepository();
            customerRepository.GetAllFromDb();



        } catch (Exception e) {

            if (e instanceof ServiceException) {
                System.out.println("Something went wrong");
            }
        }
    }
}



