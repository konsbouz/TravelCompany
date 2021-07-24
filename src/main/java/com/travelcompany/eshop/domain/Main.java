package com.travelcompany.eshop.domain;

//import com.travelcompany.eshop.repositories.TicketsRepository;
//import com.travelcompany.eshop.report.FindCustomersWithMostTickets;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.travelcompany.eshop.report.*;
import com.travelcompany.eshop.repositories.ItineraryRepository;
import com.travelcompany.eshop.service.CustomerService;
import com.travelcompany.eshop.service.ServiceException;
import com.travelcompany.eshop.service.TicketService;
import com.travelcompany.eshop.util.Directory;
//import com.travelcompany.eshop.service.StoreTodbTicketsService;
//import com.travelcompany.eshop.service.StoreTodbTicketsService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {

String perDestination = "select * from orderedtickets a inner join itineraries c on a.ItineraryId = c.Id order by DestinationAirportId";
String perDeparture = "select * from orderedtickets a inner join itineraries c on a.ItineraryId = c.Id order by DepartureAirportId";
String customerswithmosttickets ="select PassengerId , COUNT(PassengerId) AS MOST_FREQUENT from orderedtickets  GROUP BY PassengerId ORDER BY COUNT(PassengerId) desc limit 5";
String customerswithnotickets = "SELECT Id FROM   customers WHERE  Id NOT IN (SELECT PassengerId FROM orderedtickets)";


    //Ordered Itineraries
    //FindIOfferedItineraries findIOfferedItineraries = new FindIOfferedItineraries();
    //findIOfferedItineraries.FindItineraries(perDepartureQuery);

          //Customers with no tickets
         // FindCustomerWithNoTicket findCustomerWithNoTicket = new FindCustomerWithNoTicket();
        // findCustomerWithNoTicket.FindCustomers(customerswithnotickets);

        //Customers with most tickets
      //  FindCustomersWithMostTickets.FindCustomers(customerswithmosttickets);

//        WriteTofiletest writeTofiletest = new WriteTofiletest();
//        FindIOfferedItineraries findIOfferedItineraries = new FindIOfferedItineraries();
//        List<Itinerary> a = findIOfferedItineraries.FindItineraries(perDestinationQuery);
//        writeTofiletest.LetsWriteToFile(a);
//        System.out.println("File has been writen ");
//        CustomerRepository customerRepository = new CustomerRepository();
//        Customer customer1 = customerRepository.getFromDB(3);
//        ItineraryRepository itineraryRepository = new ItineraryRepository();
//        Itinerary itinerary= itineraryRepository.getFromDB(1);
//        TicketService ticketService = new TicketService();
//       Ticket ticket= ticketService.OrderedTicket(customer1,itinerary);
//       CustomerService customerService = new CustomerService();
//       customerService.Purchase(ticket);


//        FindIOfferedItineraries findIOfferedItineraries = new FindIOfferedItineraries();
//        List<Itinerary> a = findIOfferedItineraries.FindItineraries(perDestinationQuery);
//        WriteToFileItineraries writeToFileItineraries =new WriteToFileItineraries();
//        writeToFileItineraries.writeFilesToExcel(a,"C:\\Users\\BouzouKo\\travelcompany\\newexcel235.xls");
      //writeCostAndTicketsToFile.writeFilesToTxt(b,"new5.txt");
      //writeCostAndTicketsToFile.writeFilesToExcel(b,"elaligo1.xls");

      //  WriteCustomersWithMostTicketsToFile writeCustomersWithMostTicketsToFile =new WriteCustomersWithMostTicketsToFile();
      //  List<OrderedTicket> y = FindCustomersWithMostTickets.FindCustomers(customerswithmosttickets);
      //  writeCostAndTicketsToFile.writeFilesToTxt(y,"neo.txt");

//        WriteCustomersWithNoTicketsToFile writeCustomersWithNoTicketsToFile = new WriteCustomersWithNoTicketsToFile();
//        FindCustomerWithNoTicket findCustomerWithNoTicket = new FindCustomerWithNoTicket();
//        List<Customer> customerwithnoticket = findCustomerWithNoTicket.FindCustomers(customerswithnotickets);
//        writeCustomersWithNoTicketsToFile.writeFilesToTxt(customerwithnoticket,"notickets.txt");
//        writeCustomersWithNoTicketsToFile.writeFilesToExcel(customerwithnoticket,"notickets.xls");

try {

//Screen reporting
    ReportToScreen reportToScreen = new ReportToScreen();
    reportToScreen.totalNumberAndCostOfTickets();
    reportToScreen.customersWithMostTickets();
    reportToScreen.customersWithNoTickets();
    reportToScreen.totalOfferedItinerariesPerDeparture();
    reportToScreen.totalOfferedItinerariesPerDestination();


//ReportstoFiles
    String filepath = Directory.FILE_DIRECTORY.getPath();
    ReportToFile reportToFile = new ReportToFile();

    //Itineraries per destination
    FindIOfferedItineraries findIOfferedItineraries = new FindIOfferedItineraries();
    List<Itinerary> list1 = findIOfferedItineraries.findItineraries(perDestination);
    List<Itinerary> list2 = findIOfferedItineraries.findItineraries(perDeparture);

    reportToFile.writeItinerariesToExcel(list1, filepath + "itinerariesperdestination.xls");
    reportToFile.writeItinerariesToTxt(list1, filepath + "itinerariesperdestination.txt");
    reportToFile.writeItinerariesToTxt(list2, filepath + "itinerariesperdeparture.txt");
    reportToFile.writeItinerariesToExcel(list2, filepath + "itinerariesperdeparture.xls");


    //FindCustomersWithNoTickets
    FindCustomerWithNoTicket findCustomerWithNoTicket = new FindCustomerWithNoTicket();
    List<Customer> list3 = findCustomerWithNoTicket.FindCustomers();
    reportToFile.writeCustomersWithNoTicketsToExcel(list3, filepath + "noticket.xls");
    reportToFile.writeCustomersWithNoTicketsToTxt(list3, filepath + "notickets.txt");

    //FindCustomer/s with most tickets
    FindCustomersWithMostTickets findCustomersWithMostTickets = new FindCustomersWithMostTickets();
    List<Purchase> list4 = findCustomersWithMostTickets.FindCustomers();
    reportToFile.writeCustomerWithMostTicketsToExcel(list4, filepath + "mosttickets.xls");
    reportToFile.writeCustomerWithMostTicketsToTxt(list4, filepath + "mosttickets.txt");


    //Calculate Max tickets and sum
    CalculateMaxTicketsAndCostService calculateMaxTicketsAndCostService1 = new CalculateMaxTicketsAndCostService();
    List<OrderedTicket> list5 = calculateMaxTicketsAndCostService1.totalTicketsAndSumForAll();
    reportToFile.writeTotalTicketsAndSumToExcel(list5, filepath + "maxticketsandsum.xls");
    reportToFile.writeTotalTicketsAndSumToTxt(list5, filepath + "maxticketsandsum.txt");

    //CustomerService.Purchase("Frederico Rossi", "DUT");
    CustomerService customerService =new CustomerService();
    Customer customer=customerService.selectcustomerPername("Frederico Rossi");
    customerService.storeNewCustomer(customer);
    ///sadddddddd
}
catch (Exception e){

    if(e instanceof ServiceException){
        System.out.println("Something went wrong");
    }
}
    }


}
