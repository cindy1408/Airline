package com.day9exercise.demo;

import com.day9exercise.demo.Customer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Scanner;

@Component
public class Start {
    private CustomerService customerService;
    @Autowired
    CustomerController customerController = new CustomerController(customerService);
    Scanner scanner = new Scanner(System.in);

    public Start(CustomerController customerController) {
        this.customerController = customerController;
    }

    public Start() {

    }

    public void welcome() {
        System.out.println("Would you like to book a flight? y/n");
//        scanner.nextLine();
        String answer = scanner.nextLine();
        if(answer.toLowerCase().trim().equals("y")){
            System.out.println("We will need to take in your information\nWhat is your first name?");
            String firstName = scanner.nextLine();
            System.out.println("What is your surname?");
            String surname = scanner.nextLine();
            System.out.println("What is your date of birth? \nPlease provide it in this format yyyy-mm-dd");
            String dobRaw = scanner.nextLine();
            LocalDate dob = LocalDate.parse(dobRaw);
            System.out.println("Please provide your passport number");
            String passport = scanner.nextLine();
            Customer newCustomer = new Customer(firstName, surname, dob,passport);
            System.out.println("Your information have been saved");
            System.out.println(newCustomer);
            customerController.listCustomers();
            customerController.addNewCustomer(newCustomer);

        } else {
            System.out.println("Thank you for your time.");
        }

    }

}
