package com.day9exercise.demo.Flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "flights")
public record FlightController(FlightService flightService) {

    @Autowired
    public FlightController {
    }
    @GetMapping
    public List<Flight> getListFlights(){
        return flightService.getListFlights();
    }

    @GetMapping("/countries")
    public Optional<Flight> listCountryFlights(int countryId){
        return flightService.listCountryFlights(countryId);
    }

    @GetMapping("/userId")
    public void viewUserFlight(int userId){
        flightService.viewUserFlight(userId);
    }

    @PostMapping
    public void addNewFlight(Flight flight){
        flightService.addNewFlight(flight);
    }

    @DeleteMapping
    public void deleteFlight(int flightId){
        flightService.deleteFlight(flightId);
    }

    @DeleteMapping("/byCustomerFlightNumber")
    public void deleteFlightByCustomerFlightNumber(String customerFlightNumber){
        flightService.deleteCustomerFlightByFlightNumber(customerFlightNumber);
    }

}
