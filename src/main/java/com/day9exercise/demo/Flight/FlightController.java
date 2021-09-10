package com.day9exercise.demo.Flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "flights")
public class FlightController {

    private final FlightService flightService;

public FlightController(FlightService flightService){
    this.flightService = flightService;
}

@GetMapping
public List<Flight> getListFlights(){
    return flightService.getListFlights();
}

@PostMapping
void createNewFlight(@RequestBody Flight flight){
    System.out.println("POST REQUEST TO UPDATE...");
    System.out.println(flight);
}

@PutMapping
void updateNewFlight(@RequestBody Flight flight){
        System.out.println("PUT REQUEST TO UPDATE...");
        System.out.println(flight);
    }

@DeleteMapping(path= "{flightNumber}")
    void cancelFlight(@PathVariable("flightNumber") String flightNumber){
    System.out.println("THE FOLLOWING FLIGHT NUMBER WILL BE DELETED " + flightNumber);
    }
}
