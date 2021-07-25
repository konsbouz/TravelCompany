package com.travelcompany.eshop.repositories;

import com.travelcompany.eshop.api.DbRepository;
import com.travelcompany.eshop.domain.PaymentMethod;
import com.travelcompany.eshop.domain.Ticket;
import com.travelcompany.eshop.service.DBConnectionService;
import com.travelcompany.eshop.service.ServiceException;
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
    public int[] addToDb(Ticket ticket) throws SQLException, ServiceException {
        String SqlQuery = "Insert into orderedtickets (Id ,PassengerId, ItineraryId, PaymentMethod, AmountPaid) values (?, ?, ?, ?, ?)";
        PreparedStatement st = null;
        try {
            st = DBConnectionService.getConnection().prepareStatement(SqlQuery);
            st.setInt(1, ticket.getId());
            st.setInt(2, ticket.getCustomer().getId());
            st.setInt(3, ticket.getItinerary().getId());
            st.setString(4, String.valueOf(ticket.getPaymentMethod()));
            st.setDouble(5, ticket.getAmount());
            st.addBatch();
            int[] rowsAffected = st.executeBatch();
            logger.info("Insert command was successful with {} row(s) affected.", rowsAffected);
            return rowsAffected;
        } catch (Exception e) {
            logger.error("insert ticket failed", e);
            throw new ServiceException();
        } finally {
            if (st != null) {
                st.close();
            }
            DBConnectionService.getConnection().close();
        }
    }

    @Override
    public Ticket getFromDB(int id) throws SQLException, ServiceException {
        String SqlQuery = "select * from orderedtickets a inner join customers b on a.PassengerId=b.Id " +
                "inner join itineraries c on a.ItineraryId = c.Id where a.id =? ";
        PreparedStatement st = null;
        try {
            st = DBConnectionService.getConnection().prepareStatement(SqlQuery);
            st.setInt(1,id);
            CustomerRepository customerRepository = new CustomerRepository();
            ItineraryRepository itineraryRepository = new ItineraryRepository();
            ResultSet resultSet = st.executeQuery();
            resultSet.next();
            Ticket ticket = new Ticket();
            ticket.setId(resultSet.getInt(1));
            ticket.setCustomer(customerRepository.getFromDB(resultSet.getInt(2)));
            ticket.setItinerary(itineraryRepository.getFromDB(resultSet.getInt(3)));
            ticket.setPaymentMethod(PaymentMethod.valueOf(resultSet.getString(4)));
            ticket.setAmount(resultSet.getDouble(5));

            return ticket;
        } catch (Exception e) {
            logger.error("retrieve tickets from db", e);
            throw new ServiceException();
        } finally {
            if (st != null) {
                st.close();
            }
            DBConnectionService.getConnection().close();
        }

    }

    @Override
    public Ticket getFromDB1(String name) throws SQLException {
        return null;
    }


    @Override
    public Ticket updateToDb(int id, Ticket ticket) throws SQLException, ServiceException {
        String SqlQuery = "UPDATE orderedtickets SET  PassengerId = ? ,ItineraryId = ? ,PaymentMethod = ? " +
                ",AmountPaid = ? where id= ? ";
        PreparedStatement st = null;
        try{
        st = DBConnectionService.getConnection().prepareStatement(SqlQuery);
        st.setInt(1, ticket.getCustomer().getId());
        st.setInt(2, ticket.getItinerary().getId());
        st.setString(3, String.valueOf(ticket.getPaymentMethod()));
        st.setDouble(4, ticket.getAmount());
        st.setInt(5, id);
        st.executeUpdate();
        st.close();
        DBConnectionService.getConnection().close();
        logger.info("The update was successful ");
        return ticket;
        }
        catch (Exception e){
            logger.error("update tickets to db failed",e);
            throw new ServiceException();
        }finally{
            if (st != null) {
                st.close();
            }
            DBConnectionService.getConnection().close();
        }
    }

    @Override
    public boolean DeleteFromDb(int id) {
        return false;
    }

    @Override
    public List<Ticket> GetAllFromDb() throws SQLException, ServiceException {
        String SqlQuery = "select * from orderedtickets ";
        PreparedStatement st = null;
        try {
            st = DBConnectionService.getConnection().prepareStatement(SqlQuery);
            CustomerRepository customerRepository = new CustomerRepository();
            ItineraryRepository itineraryRepository = new ItineraryRepository();
            ResultSet resultSet = st.executeQuery();
            List<Ticket> tickets = new ArrayList<>();
            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(resultSet.getInt(1));
                ticket.setCustomer(customerRepository.getFromDB(resultSet.getInt(2)));
                ticket.setItinerary(itineraryRepository.getFromDB(resultSet.getInt(3)));
                ticket.setPaymentMethod(PaymentMethod.valueOf(resultSet.getString(4)));
                ticket.setAmount(resultSet.getDouble(5));
                tickets.add(ticket);
            }
            return tickets;
        }
        catch (Exception e){
            logger.error("get all tickets from db failed",e);
            throw new ServiceException();
        }finally{
            if (st != null) {
                st.close();
            }
            DBConnectionService.getConnection().close();
        }
    }


}
