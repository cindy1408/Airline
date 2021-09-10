package com.day9exercise.demo.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepositoryPostgres customerRepositoryPostgres;

    @Autowired
    public CustomerService(CustomerRepositoryPostgres customerRepositoryPostgres){

        this.customerRepositoryPostgres = customerRepositoryPostgres;
    }

    //GET REQUEST
    public List<Customer> getFullListCustomer(){
        return customerRepositoryPostgres.findAll();
    }

    //POST REQUEST
    public Customer addNewCustomer(Customer newCustomer){
       return customerRepositoryPostgres.save(newCustomer);
    }

}
