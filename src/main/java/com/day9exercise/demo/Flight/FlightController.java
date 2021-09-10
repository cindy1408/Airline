package com.day9exercise.demo.Flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "flights")
public class FlightController {

    private final FlightDataAccess flightDataAccess;

public FlightController(FlightDataAccess flightDataAccess){
    this.flightDataAccess = flightDataAccess;
}

@GetMapping
public List<Flight> getListFlights(){
    return flightDataAccess.getListFlights();
}

@PostMapping //adds a new flight but age and no. of passengers stays as 0 unless you actually hardcode it.
public List<Flight> createNewFlight(@RequestBody Flight flight){
    System.out.println("POST REQUEST TO ADD...");
    System.out.println(flight);
    flightDataAccess.addFlight(flight);
    return flightDataAccess.getListFlights();
}

@PutMapping
public void updateFlight(@RequestBody Flight flight){
        System.out.println("PUT REQUEST TO UPDATE...");
        System.out.println(flight);
    }

@DeleteMapping(path= "{flightNumber}")
public void cancelFlight(@PathVariable("flightNumber") String flightNumber){
    System.out.println("THE FOLLOWING FLIGHT NUMBER WILL BE DELETED: " + flightNumber);
    }
}
