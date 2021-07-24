package com.travelcompany.eshop.repositories;

import com.travelcompany.eshop.api.DbRepository;
import com.travelcompany.eshop.domain.Customer;
import com.travelcompany.eshop.domain.Itinerary;
import com.travelcompany.eshop.domain.PaymentMethod;
import com.travelcompany.eshop.domain.Ticket;
import com.travelcompany.eshop.service.CalculateFinalPriceService;
import com.travelcompany.eshop.service.DBConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketsRepository implements DbRepository<Ticket> {

    Logger logger = LoggerFactory.getLogger(TicketsRepository.class.getName());

    @Override
    public int[] addToDb(Ticket ticket) throws SQLException {

        String SqlQuery = "Insert into orderedtickets (Id ,PassengerId, ItineraryId, PaymentMethod, AmountPaid) values (?, ?, ?, ?, ?)";
        PreparedStatement st = DBConnectionService.getConnection().prepareStatement(SqlQuery);
        st.setInt(1,ticket.getId());
        st.setInt(2, ticket.getCustomer().getId());
        st.setInt(3, ticket.getItinerary().getId());
        st.setString(4, String.valueOf(ticket.getPaymentMethod()));
        st.setDouble(5, ticket.getAmount());
        st.addBatch();

        int[] rowsAffected = st.executeBatch();
        logger.info("Insert command was successful with {} row(s) affected.", rowsAffected);

        st.close();
        DBConnectionService.getConnection().close();

        return new int[0];
    }

    @Override
    public Ticket getFromDB(int id) throws SQLException {
        String SqlQuery = "select * from orderedtickets a inner join customer b on a.PassengerId=b.Id " +
                "inner join itineraries c on a.ItineraryId = c.Id ";
        PreparedStatement st = DBConnectionService.getConnection().prepareStatement(SqlQuery);
        ResultSet resultSet = st.executeQuery();
        resultSet.next();
        Ticket ticket = new Ticket();
        ticket.setId(resultSet.getInt(1));
        Customer customer = new Customer();
        customer.setId(resultSet.getInt(2));
        ticket.setCustomer(customer);
        Itinerary itinerary = new Itinerary();
        itinerary.setId(resultSet.getInt(3));
        ticket.setItinerary(itinerary);
        ticket.setPaymentMethod(PaymentMethod.valueOf(resultSet.getString(4)));
        ticket.setAmount(resultSet.getDouble(5));

        st.close();
        DBConnectionService.getConnection().close();
        System.out.println(ticket);
        return ticket;


    }

    @Override
    public Ticket getFromDB1(String name) throws SQLException {
        return null;
    }


    @Override
    public Ticket updateToDb(int id, Ticket ticket) throws SQLException {
        String SqlQuery = "UPDATE orderedtickets SET  PassengerId = ? ,ItineraryId = ? ,PaymentMethod = ? " +
                ",AmountPaid = ? where id= ? ";
        PreparedStatement st = DBConnectionService.getConnection().prepareStatement(SqlQuery);
        st.setInt(1, ticket.getCustomer().getId());
        st.setInt(2,ticket.getItinerary().getId());
        st.setString(3, String.valueOf(ticket.getPaymentMethod()));
        st.setDouble(4,ticket.getAmount());
        st.setInt(5,id);

        st.executeUpdate();
        st.close();
        DBConnectionService.getConnection().close();
        logger.info("The update was successful ");
        return ticket;
    }

    @Override
    public boolean DeleteFromDb(int id) {
        return false;
    }

    @Override
    public List<Ticket> GetAllFromDb() throws SQLException {
       String SqlQuery = "Select * from orderedtickets ";
       PreparedStatement st = DBConnectionService.getConnection().prepareStatement(SqlQuery);
       ResultSet resultSet = st.executeQuery();
       List<Ticket> tickets = new ArrayList<>();
       while(resultSet.next()){
           Ticket ticket = new Ticket();
           ticket.setId(resultSet.getInt(1));
           Customer customer = new Customer();
           customer.setId(resultSet.getInt(2));
           ticket.setCustomer(customer);
           Itinerary itinerary = new Itinerary();
           itinerary.setId(resultSet.getInt(3));
           ticket.setItinerary(itinerary);
           ticket.setPaymentMethod(PaymentMethod.valueOf(resultSet.getString(4)));
           ticket.setAmount(CalculateFinalPriceService.finalPriceCalculation(ticket));
           tickets.add(ticket);
       }

        return tickets;
    }







}
