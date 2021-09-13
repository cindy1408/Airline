package com.day9exercise.demo.Flight;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlightRepositoryPostgres extends JpaRepository<Flight, Integer> {
    Optional<Flight> findFlightByCountryId(int countryId);
    Optional<Flight> findFlightByCustomerFlightNumber(String flightNumber);
}
