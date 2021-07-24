package com.travelcompany.eshop.report;
import com.travelcompany.eshop.domain.Itinerary;
import com.travelcompany.eshop.service.DBConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FindIOfferedItineraries {

        Logger logger = LoggerFactory.getLogger(FindIOfferedItineraries.class.getName());

    public List<Itinerary> findItineraries(String query) throws SQLException {

        PreparedStatement st = DBConnectionService.getConnection().prepareStatement(query);
        ResultSet resultSet = st.executeQuery();

        List<Itinerary> finalList = new ArrayList<>();
        while (resultSet.next()) {
            Itinerary itinerary = new Itinerary();
            itinerary.setId(resultSet.getInt(6));
            itinerary.setDepartureAirportId(resultSet.getString(7));
            itinerary.setDestinationAirportId(resultSet.getString(8));
            itinerary.setDepartureDate(resultSet.getString(9));
            itinerary.setAirlines(resultSet.getString(10));
            itinerary.setPrice(resultSet.getDouble(11));
            finalList.add(itinerary);




        }

        return finalList;

    }



}
