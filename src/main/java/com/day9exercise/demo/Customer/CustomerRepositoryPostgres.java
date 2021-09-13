package com.day9exercise.demo.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface CustomerRepositoryPostgres extends JpaRepository<Customer, Integer> {

    Optional<Customer> findCustomerByPassport(String customerPassport);


}

