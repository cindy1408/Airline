package com.day9exercise.demo;
import com.day9exercise.demo.Country.Country;
import com.day9exercise.demo.Country.CountryController;
import com.day9exercise.demo.Country.CountryRepositoryPostgres;
import com.day9exercise.demo.Customer.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Scanner;

@SpringBootApplication

public class DemoApplication {
	private static CustomerController customerController;
	private static CountryController countryController;

	public DemoApplication(CustomerController customerController, CountryController countryController) {
		this.customerController = customerController;
		this.countryController = countryController;
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
				System.out.println("logging successfully.\nWhat would you like to do?\n1. Search customer by passport?\n2. Delete customer by passport?\n3. Amend customer details\n4. View full list of customers\n5. Add new country destination\n6. Delete country from list\n7. Update country");
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
						break;
					case 5:
						System.out.println("You would like to add a new country destination");
						System.out.println("Please enter the name of the new Country");
						scanner.nextLine();
						String countryName = scanner.nextLine();
						System.out.println("Please enter the estimated duration in minutes");
						int estimatedDuration = scanner.nextInt();
						System.out.println("Please enter the price");
						double price = scanner.nextInt();
						Country newCountry = new Country(countryName, estimatedDuration, price);
						countryController.insertNewCountry(newCountry);
						break;
					case 6:
						System.out.println("Please enter the country id you want to delete from the system");
						int countryId = scanner.nextInt();
						countryController.deleteCountry(countryId);
						break;
					case 7:
						System.out.println("Please enter the country id you want to update");
						int updateCountry = scanner.nextInt();
						System.out.println("which of the following do you need to update?\n1. Country name?\n2. Estimated Travel?\n3.Price");
						int requestedUpdate = scanner.nextInt();
						countryController.updateCountry(updateCountry, requestedUpdate);
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

