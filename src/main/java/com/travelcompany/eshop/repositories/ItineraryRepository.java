package com.travelcompany.eshop.repositories;

import com.travelcompany.eshop.api.DbRepository;
import com.travelcompany.eshop.domain.Itinerary;
import com.travelcompany.eshop.database.DBConnectionService;
import com.travelcompany.eshop.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.rowset.serial.SerialException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItineraryRepository implements DbRepository<Itinerary> {

    Logger logger = LoggerFactory.getLogger(ItineraryRepository.class.getName());

    @Override
    public int[] addToDb(Itinerary itinerary) throws SQLException, ServiceException {
        String SqlQuery = "Insert into itineraries (Id,DepartureAirportId ,DestinationAirportId , Departuredate , Airline , Price) values (?, ?, ?, ?,?,? )";
        PreparedStatement st = null;
        try {
            st = DBConnectionService.getConnection().prepareStatement(SqlQuery);
            st.setInt(1, itinerary.getId());
            st.setString(2, itinerary.getDepartureAirportId());
            st.setString(3, itinerary.getDestinationAirportId());
            st.setString(4, itinerary.getDepartureDate());
            st.setString(5, itinerary.getAirlines());
            st.setDouble(6, itinerary.getPrice());
            st.addBatch();
            int[] rowsAffected = st.executeBatch();
            logger.info("Insert command was successful with {} row(s) affected. ", rowsAffected);
            return rowsAffected;
        } catch (Exception e) {
            logger.error("insert itineraries failed", e);
            throw new ServiceException();
        } finally {
            if (st != null) {
                st.close();
            }
            DBConnectionService.getConnection().close();
        }
    }

    @Override
    public Itinerary getFromDB(int id) throws SQLException, ServiceException {
        String SqlQuery = "select * from itineraries where Id =? ";
        PreparedStatement st = null;
        try {
            st = DBConnectionService.getConnection().prepareStatement(SqlQuery);
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            resultSet.next();
            Itinerary itinerary = new Itinerary();
            itinerary.setId(resultSet.getInt(1));
            itinerary.setDepartureAirportId(resultSet.getString(2));
            itinerary.setDestinationAirportId(resultSet.getString(3));
            itinerary.setDepartureDate(resultSet.getString(4));
            itinerary.setAirlines(resultSet.getString(5));
            itinerary.setPrice(resultSet.getDouble(6));
            return itinerary;
        } catch (Exception e) {
            logger.error("retrieve itineraries from db failed", e);
            throw new ServiceException();
        } finally {
            if (st != null) {
                st.close();
            }
            DBConnectionService.getConnection().close();

        }
    }

    @Override
    public Itinerary getFromDB1(String name) throws SQLException, ServiceException {
        String SqlQuery = "select * from itineraries where DestinationAirPortId =? ";
        PreparedStatement st = null;
        try {
            st = DBConnectionService.getConnection().prepareStatement(SqlQuery);
            st.setString(1, name);
            Itinerary itinerary = new Itinerary();
            ResultSet resultSet = st.executeQuery();

            resultSet.next();
            itinerary.setId(resultSet.getInt(1));
            itinerary.setDepartureAirportId(resultSet.getString(2));
            itinerary.setDestinationAirportId(resultSet.getString(3));
            itinerary.setDepartureDate(resultSet.getString(4));
            itinerary.setAirlines(resultSet.getString(5));
            itinerary.setPrice(resultSet.getDouble(6));
            st.close();
            DBConnectionService.getConnection().close();

            return itinerary;
        } catch (Exception e) {
            logger.error("retrieve itineraries per DestinationAirPortId failed", e);
            throw new ServiceException();
        } finally {
            if (st != null) {
                st.close();
            }
            DBConnectionService.getConnection().close();
        }
    }

    @Override
    public Itinerary updateToDb(int id, Itinerary itinerary) throws SQLException {
        return null;
    }

    @Override
    public boolean DeleteFromDb(int id) {
        return false;
    }

    @Override
    public List<Itinerary> GetAllFromDb() throws SQLException {
        String SqlQuery = "select * from itineraries ";
        PreparedStatement st = null;
        try {
            st = DBConnectionService.getConnection().prepareStatement(SqlQuery);
            ResultSet resultSet = st.executeQuery();
            List<Itinerary> itineraryList = new ArrayList<>();
            while (resultSet.next()) {
                Itinerary itinerary = new Itinerary();
                itinerary.setId(resultSet.getInt(1));
                itinerary.setDepartureAirportId(resultSet.getString(2));
                itinerary.setDestinationAirportId(resultSet.getString(3));
                itinerary.setDepartureDate(resultSet.getString(4));
                itinerary.setAirlines(resultSet.getString(5));
                itinerary.setPrice(resultSet.getDouble(6));
                itineraryList.add(itinerary);
            }

            return itineraryList;
        } catch (Exception e) {
            logger.error("retrieve all itineraries from db failed", e);
            throw new SerialException();
        } finally {
            if (st != null) {
                st.close();
            }
            DBConnectionService.getConnection().close();
        }
    }
}
