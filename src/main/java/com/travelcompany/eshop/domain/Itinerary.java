package com.travelcompany.eshop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Itinerary {
        private int id;
        private String departureAirportId;
        private String destinationAirportId;
        private String departureDate;
        private String airlines;
        private double price;





}
