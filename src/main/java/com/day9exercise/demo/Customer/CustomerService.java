package com.day9exercise.demo.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void requestedCustomer(String customerPassport){
        customerRepositoryPostgres.findCustomerByPassport(customerPassport)
                .ifPresentOrElse(customer -> {
                    System.out.println(customer);
                }, () -> {
                    System.out.println("Customer with " + customerPassport + "is not within our database.");
                });
    }

    //POST REQUEST
    public Customer addNewCustomer(Customer newCustomer){
       return customerRepositoryPostgres.save(newCustomer);
    }

    //DELETE REQUEST
    public void deleteCustomer(String customerPassport){
        customerRepositoryPostgres.findCustomerByPassport(customerPassport)
                .ifPresentOrElse(findCustomer -> {
                    customerRepositoryPostgres.delete(findCustomer);
                    System.out.println("Customer with " + customerPassport + " has been successfully deleted.");
                }, () -> {
                    System.out.println("Customer with passport number " + customerPassport + " does not exist");
                });
    }

}
