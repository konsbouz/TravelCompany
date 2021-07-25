package com.travelcompany.eshop.repositories;

import com.travelcompany.eshop.domain.Itinerary;
import com.travelcompany.eshop.service.ServiceException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ItineraryRepositoryTest {

    @Test
    void addToDb() {
    }

    @Test
    void getFromDB() throws ServiceException, SQLException {
        Itinerary itinerary = new Itinerary(7,"ATH","DUB","2020-12-02 09:35:00.000","Skylines",880);
        ItineraryRepository itineraryRepository = new ItineraryRepository();
        assertEquals(itinerary,itineraryRepository.getFromDB(7));

    }
}