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

    @DeleteMapping("/byFlightNumber")
    public void deleteFlightByFlightNumber(String flightNumber){
        flightService.deleteFlightByFlightNumber(flightNumber);
    }



//@PostMapping //adds a new flight but age and no. of passengers stays as 0 unless you actually hardcode it.
//public List<Flight> createNewFlight(@RequestBody Flight flight){
//    System.out.println("POST REQUEST TO ADD...");
//    System.out.println(flight);
//    flightService.addFlight(flight);
//    return flightService.getListFlights();
//}
//
//@PutMapping
//public void updateFlight(@RequestBody Flight flight){
//        System.out.println("PUT REQUEST TO UPDATE...");
//        System.out.println(flight);
//    }
//
//@DeleteMapping(path= "{flightNumber}")
//public void cancelFlight(@PathVariable("flightNumber") String flightNumber){
//    System.out.println("THE FOLLOWING FLIGHT NUMBER WILL BE DELETED: " + flightNumber);
//    }
}
