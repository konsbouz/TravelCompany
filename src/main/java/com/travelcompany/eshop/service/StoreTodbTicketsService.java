//package com.travelcompany.eshop.service;
//import com.travelcompany.eshop.domain.Ticket;
//import com.travelcompany.eshop.repositories.TicketsRepository;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.List;
//
//public class StoreTodbTicketsService {
//    TicketsRepository ticketsRepository = new TicketsRepository();
//
//    public void StoreViaFile(String filename) throws IOException, SQLException {
//
//        List<Ticket> tickets = CSVReaderService.TicketsReader(filename);
//
//        for (Ticket ticket : tickets) {     ticketsRepository.addToDb(ticket);
//       }
//   }
//
//    public void StoreNewTicket(Ticket ticket) {
//
//        try {
//            ticketsRepository.addToDb(ticket);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
