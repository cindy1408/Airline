package com.day9exercise.demo.Flight;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    private FlightDataAccess flightDataAccess;


    public FlightService(FlightDataAccess flightDataAccess){
        this.flightDataAccess = flightDataAccess;
    }

    public List<Flight> getListFlights(){
        return flightDataAccess.getListFlights();
    }

    Flight doesFlightExist(String flightNumber){
        return FlightDataAccess.getListFlights()
                .stream()
                .filter(flight -> flight.getFlightNumber() == flightNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Flight cannot be found"));
    }
}
