package com.day9exercise.demo.Flight;

import com.day9exercise.demo.Country.Country;
import com.day9exercise.demo.Customer.Customer;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightDataAccess {

    private static List<Flight> listFlights;

    public FlightDataAccess() {

        List<Customer> customers = new ArrayList<>(List.of(new Customer("Mary", "Anderson", LocalDate.of(2000, 6, 16), "HN46K93B"),
                new Customer("Sahra", "Osman", LocalDate.of(1999, 05, 24), "HD65J2B3")));

            Customer customer = new Customer("Samira", "Sagadi", LocalDate.of(1992,12,2), "ER85NY3D");
            customers.add(customer);

        Flight flight = new Flight(new Country("Malta"), List.of(customer), 2.50,
                LocalDateTime.of(2021, 10, 25, 10, 25), LocalDateTime.of(2021, 10, 25, 17, 55), false, 86.05, "MALTA21");

        Flight flight1 = new Flight(new Country("Turkey"), customers, 2.50,
                LocalDateTime.of(2021, 10, 25, 10, 25), LocalDateTime.of(2021, 10, 25, 17, 55),true, LocalDateTime.of(2021, 11, 23, 14,25), LocalDateTime.of(2021, 11, 23,17,15), 86.85, "TURK21");


        listFlights = new ArrayList<>();
        listFlights.add(flight);
        listFlights.add(flight1);
    }

    public static List<Flight> getListFlights() {
        return listFlights;
    }

    public List<Flight> addFlight(Flight flight){
        listFlights.add(flight);
        return listFlights;
    }

    public static List<Flight> cancelFlight(Flight flight){
        listFlights.remove(flight);
        return listFlights;
    }
}