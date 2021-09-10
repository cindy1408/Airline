package com.day9exercise.demo.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerRepositoryPostgres extends JpaRepository<Customer, Integer> {

//    int insertCustomer(UUID id, Customer newCustomer);
//
//    default int insertCustomer(Customer newCustomer){
//        UUID id = UUID.randomUUID();
//        return insertCustomer(id, newCustomer);
//    }
////    default int insertCustomer(Customer newCustomer){
////
////    }
//     List<Customer> selectAllCustomers();
}

