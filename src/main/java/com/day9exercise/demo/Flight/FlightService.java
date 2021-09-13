package com.day9exercise.demo.Flight;

import com.day9exercise.demo.Country.CountryRepositoryPostgres;
import com.day9exercise.demo.Customer.CustomerRepositoryPostgres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private final FlightRepositoryPostgres flightRepositoryPostgres;
    private final CountryRepositoryPostgres countryRepositoryPostgres;
    private final CustomerRepositoryPostgres customerRepositoryPostgres;

    @Autowired
    public FlightService(FlightRepositoryPostgres flightRepositoryPostgres, CountryRepositoryPostgres countryRepositoryPostgres, CustomerRepositoryPostgres customerRepositoryPostgres){
        this.flightRepositoryPostgres = flightRepositoryPostgres;
        this.countryRepositoryPostgres = countryRepositoryPostgres;
        this.customerRepositoryPostgres = customerRepositoryPostgres;
    }

    public List<Flight> getListFlights(){return flightRepositoryPostgres.findAll();}

    public Optional<Flight> listCountryFlights(int countryId){return flightRepositoryPostgres.findFlightByCountryId(countryId);}

    public void addNewFlight(Flight flight){
        flightRepositoryPostgres.save(flight);
    }

    public void viewUserFlight(int userId){
        flightRepositoryPostgres.findById(userId)
                .ifPresentOrElse(flight -> {
                    System.out.println(flight);
                }, () -> System.out.println("We are unable to find your flight, please go to customer support for help."));
    }

    public void deleteFlight(int flightId){
        flightRepositoryPostgres.findById(flightId)
                        .ifPresentOrElse(flight -> {
                            flightRepositoryPostgres.delete(flight);
                            System.out.println("Flight has been successfully deleted.");
                        }, () -> {
                            System.out.println("Flight id cannot be found within our system.");
                        });
    }

    public void deleteFlightByFlightNumber(String flightNumber){
        flightRepositoryPostgres.findFlightByFlightNumber(flightNumber);
    }

    Flight doesFlightExist(String flightNumber){
        return FlightDataAccess.getListFlights()
                .stream()
                .filter(flight -> flight.getFlightNumber() == flightNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Flight cannot be found"));
    }
}
