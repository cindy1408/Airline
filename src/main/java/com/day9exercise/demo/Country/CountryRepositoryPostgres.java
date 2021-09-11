package com.day9exercise.demo.Country;

import com.day9exercise.demo.Customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepositoryPostgres extends JpaRepository<Country, Integer> {

    Optional<Country> findCountryByName(String countryName);
}
