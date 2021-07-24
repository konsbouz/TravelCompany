package com.travelcompany.eshop.service;

import com.travelcompany.eshop.domain.Itinerary;
import com.travelcompany.eshop.repositories.ItineraryRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StoreTodbItinerariesService {
    ItineraryRepository itineraryRepository = new ItineraryRepository();

    public void StoreViaFile(String filename) throws IOException, SQLException {

        List<Itinerary> itineraries = CSVReaderService.ItinerariesReader(filename);

        for (Itinerary itinerary : itineraries) {
            itineraryRepository.addToDb(itinerary);

        }
    }

    public void StoreNewItinerary(Itinerary itinerary) {

        try {
            itineraryRepository.addToDb(itinerary);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
