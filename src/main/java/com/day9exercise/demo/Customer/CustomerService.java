package com.day9exercise.demo.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CustomerService {

    Scanner scanner = new Scanner(System.in);
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

    //PUT REQUEST
    public void updateCustomer(int customerId, int customerUpdateRequired){
            customerRepositoryPostgres.findById(customerId)
                    .ifPresentOrElse(requiredCustomer -> {
                        switch(customerUpdateRequired){
                            case 1:
                                System.out.println("Currently the customer first name is " + requiredCustomer.getFirstName());
                                System.out.println("Please enter their updated first name");
                                String customerUpdatedFirstName = scanner.nextLine();
                                if(customerUpdatedFirstName != null){
                                    requiredCustomer.setFirstName(customerUpdatedFirstName);
                                    customerRepositoryPostgres.save(requiredCustomer);
                                    System.out.println("Customer's first name has been updated");
                                } else {
                                    System.out.println("Please enter first name");
                                }
                                break;
                            case 2:
                                System.out.println("Currently the customer surname is " + requiredCustomer.getLastName());
                                System.out.println("Please enter their updated first name");
                                String customerUpdatedLastName = scanner.nextLine();
                                if(customerUpdatedLastName != null){
                                    requiredCustomer.setLastName(customerUpdatedLastName);
                                    customerRepositoryPostgres.save(requiredCustomer);
                                    System.out.println("Customer's surname has been updated");
                                } else {
                                    System.out.println("Please enter surname");
                                }
                                break;
                            case 3:
                                System.out.println("Currently the customer passport is " + requiredCustomer.getPassport());
                                System.out.println("Please enter their updated passport number");
                                String customerUpdatedPassport = scanner.nextLine();
                                if(customerUpdatedPassport != null){
                                    requiredCustomer.setPassport(customerUpdatedPassport);
                                    customerRepositoryPostgres.save(requiredCustomer);
                                    System.out.println("Customer's passport number has been updated");
                                } else {
                                    System.out.println("Please enter passport");
                                }
                                break;
                        }
                    }, () -> {
                        System.out.println("Customer with " + customerId + " is not registered within our system.");
                    });

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
