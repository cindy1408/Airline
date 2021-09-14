package com.day9exercise.demo.Customer;

import com.day9exercise.demo.Flight.Flight;
import com.day9exercise.demo.Flight.FlightRepositoryPostgres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerService {

    Scanner scanner = new Scanner(System.in);
    private final CustomerRepositoryPostgres customerRepositoryPostgres;
    private final FlightRepositoryPostgres flightRepositoryPostgres;

    @Autowired
    public CustomerService(CustomerRepositoryPostgres customerRepositoryPostgres, FlightRepositoryPostgres flightRepositoryPostgres){

        this.customerRepositoryPostgres = customerRepositoryPostgres;
        this.flightRepositoryPostgres = flightRepositoryPostgres;
    }

    //GET REQUEST
    public List<Customer> getFullListCustomer(){
        Iterator iterator = customerRepositoryPostgres.findAll().listIterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        return customerRepositoryPostgres.findAll();
    }

    public Optional<Customer> requestedCustomer(String customerPassport) {
        customerRepositoryPostgres.findCustomerByPassport(customerPassport)
                .ifPresentOrElse(customer -> {
                    System.out.println("Customer's id: " + customer.getId());
                    System.out.println("Customer's first name: " + customer.getFirstName());
                    System.out.println("Customer's last name: " + customer.getLastName());
                    System.out.println("Customer's passport: " + customer.getPassport());
                    System.out.println("Customer's date of birth: " + customer.getDob());
                    System.out.println("Customer's age: " + customer.getAge());
                    List<Flight> customersFlight = flightRepositoryPostgres.findAllByCustomersId(customer.getId());
                    for(int i=0; i < customersFlight.size(); i++){
                        System.out.println("Customer's flight number, if any: " + customersFlight.get(i).getCustomerFlightNumber());
                    }

                }, () -> {
                    System.out.println("We were unable to find any customer linked to " + customerPassport);
                });

        return customerRepositoryPostgres.findCustomerByPassport(customerPassport);
    }


    public void greetCustomer(String customerPassport){
        customerRepositoryPostgres.findCustomerByPassport(customerPassport)
                .ifPresentOrElse(customer -> {
                    System.out.println("Hello " + customer.getFirstName() + "!");
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
                                System.out.println("Please enter their updated surname");
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
