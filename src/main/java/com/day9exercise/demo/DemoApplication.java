package com.day9exercise.demo;
import com.day9exercise.demo.Customer.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Scanner;

@SpringBootApplication

public class DemoApplication {
	private static CustomerController customerController;

	public DemoApplication(CustomerController customerController) {
		this.customerController = customerController;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		Scanner scanner = new Scanner(System.in);

		System.out.println("Sign in as Employee or Customer?\n1. Employee\n2. Customer");
		int input = scanner.nextInt();
		if(input == 1){
			// EMPLOYEE SECTION
			System.out.println("Please enter your username");
			scanner.nextLine();
			String username = scanner.nextLine();
			System.out.println("Please enter your password");
			String password  = scanner.nextLine();
			if(username.equals("username") && password.equals("password")){
				System.out.println("logging successfully.\nWhat would you like to do?\n1. Search customer by passport?\n2. Delete customer by passport?\n3. Amend customer details\n4. View full list of customers");
				int userInput = scanner.nextInt();
				switch (userInput){
					case 1:
						System.out.println("Please enter the customer passport number");
						scanner.nextLine();
						String customerPassport = scanner.nextLine();
						customerController.requestedCustomer(customerPassport);
						break;
					case 2:
						System.out.println("Please enter the customer passport number");
						scanner.nextLine();
						String customerPassportDelete = scanner.nextLine();
						customerController.deleteCustomer(customerPassportDelete);
						break;
					case 3:
						System.out.println("Please type in the customer id number");
						int customerId = scanner.nextInt();
						System.out.println("Please type the required customer information that needs to be updated.\n1. first name\n2. surname\n3. passport number");
						int updateInfo = scanner.nextInt();
						customerController.updateCustomer(customerId, updateInfo);
						break;
					case 4:
						customerController.listAllCustomers();

				}
			} else {
				System.out.println("Invalid logging, please try again");
			}

		} else {

			//CUSTOMER SECTION
			System.out.println("Would you like to book a flight? y/n");

			String answer = scanner.nextLine();
			if (answer.toLowerCase().trim().equals("y")) {
				System.out.println("We will need to take in your information\nWhat is your first name?");
				String firstName = scanner.nextLine();
				System.out.println("What is your surname?");
				String surname = scanner.nextLine();
				System.out.println("What is your date of birth? \nPlease provide it in this format yyyy-mm-dd");
				String dobRaw = scanner.nextLine();
				LocalDate dob = LocalDate.parse(dobRaw);
				System.out.println("Please provide your passport number");
				String passport = scanner.nextLine();
				Customer newCustomer = new Customer(firstName, surname, dob, passport);
				System.out.println("Your information have been saved");
				customerController.addNewCustomer(newCustomer);
				customerController.requestedCustomer(newCustomer.getPassport());
				System.out.println("Thank you for registering, your id is " + newCustomer.getId());
			} else {
				System.out.println("Thank you for your time.");
			}

		}

		//BELOW WORKS!
//		@Bean
//		CommandLineRunner commandLineRunner(CustomerRepositoryPostgres customerRepositoryPostgres){
//		return args -> {
//			Customer jessica = new Customer("Jessica", "Ali", LocalDate.of(2000, 6,14), "ALI9219027490124");
//			customerRepositoryPostgres.save(jessica);
//		};
	}
}

