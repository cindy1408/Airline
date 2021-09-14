package com.day9exercise.demo.Flight;

import com.day9exercise.demo.Country.CountryRepositoryPostgres;
import com.day9exercise.demo.Customer.CustomerRepositoryPostgres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
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

    public List<Flight> getListFlights(){
        Iterator flight = flightRepositoryPostgres.findAll().iterator();
        while(flight.hasNext()){
            System.out.println(flight.next());
        }
        return flightRepositoryPostgres.findAll();
    }

    public Optional<Flight> listCountryFlights(int countryId){return flightRepositoryPostgres.findFlightByCountryId(countryId);}

    public void addNewFlight(Flight flight){
        flightRepositoryPostgres.save(flight);
    }

    public void viewUserFlight(int userId){
        flightRepositoryPostgres.findFlightByCustomersId(userId)
                        .ifPresentOrElse(flight -> {
                            System.out.println("Details for customer id: " + flight.getCustomersId());
                            System.out.println("Your flight id is: " + flight.getFlightId());
                            System.out.println("Your flight number is: " + flight.getCustomerFlightNumber());
                            System.out.println("Total amount of passenger: " + flight.getNumberOfPassenger());
                            System.out.println("Return expected departure time: " + flight.getReturnTimeDeparture());
                            System.out.println("Return expected ticket arrival time: " + flight.getReturnTimeArrival());
                            System.out.println("Total price " + flight.getTotalPrice());
                            customerRepositoryPostgres.findById(flight.getCustomersId())
                                            .ifPresentOrElse(customer -> {
                                                System.out.println("First name : " + customer.getFirstName());
                                                System.out.println("Last name: " + customer.getLastName());
                                                System.out.println("Date of birth: " + customer.getDob());
                                                System.out.println("Passport number: " + customer.getPassport());
                                            }, () -> {
                                                System.out.println("There was an issue finding your customer id in our system.");
                                            });
                            countryRepositoryPostgres.findById(flight.getCountryId())
                                    .ifPresentOrElse(country -> {
                                        System.out.println("Destination: " + country.getName());
                                        System.out.println("Expected Departure time: " + country.getTimeDeparture());
                                        System.out.println("Expected Arrival time: " + country.getTimeArrival());
                                    }, () -> {
                                        System.out.println("Sorry we could not find your country id");
                                    });
                        }, () -> {
                            System.out.println("Sorry we are unable to find your user id.");
                        });
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
        flightRepositoryPostgres.findFlightByCustomerFlightNumber(customerFlightNumber)
                .ifPresentOrElse(flight -> {
                    flightRepositoryPostgres.delete(flight);
                    System.out.println("Your flight has been successfully cancelled");
                }, () -> {
                    System.out.println("We cannot find you flight number in our database");
                });
    }

}
