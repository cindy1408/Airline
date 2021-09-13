package com.day9exercise.demo.Flight;

import com.day9exercise.demo.Country.CountryRepositoryPostgres;
import com.day9exercise.demo.Customer.CustomerRepositoryPostgres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

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
        flightRepositoryPostgres.findFlightByCustomersId(userId);
    }

    public void updateFlight(String passport, int customerFlightId){
        customerRepositoryPostgres.findCustomerByPassport(passport).ifPresentOrElse(customer -> {
            System.out.println("Thank you, you have been verified");
        }, () -> {
            System.out.println("We can't find you in our system!");
        });
        flightRepositoryPostgres.findFlightByFlightId(customerFlightId)
                .ifPresentOrElse(flight -> {
                    System.out.println(countryRepositoryPostgres.findAll());
                            System.out.println("Please enter the id of your desired holiday");
                            Scanner scanner = new Scanner(System.in);
                            int userHoliday = scanner.nextInt();
                            flight.setCountryId(userHoliday);
                            flightRepositoryPostgres.save(flight);
                            },
                        () -> System.out.println("We are unable to find your customer id in our database"));
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

    public void deleteCustomerFlightByFlightNumber(String customerFlightNumber){
        flightRepositoryPostgres.findFlightByCustomerFlightNumber(customerFlightNumber);
    }

    Flight doesFlightExist(String flightNumber){
        return FlightDataAccess.getListFlights()
                .stream()
                .filter(flight -> flight.getCustomerFlightNumber() == flightNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Flight cannot be found"));
    }
}
