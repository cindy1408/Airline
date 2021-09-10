package com.day9exercise.demo;
import com.day9exercise.demo.Customer.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Scanner;

@SpringBootApplication

public class DemoApplication {
	private static CustomerController customerController;

	private CustomerService customerService;

	public DemoApplication(CustomerController customerController) {
		this.customerController = customerController;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		Scanner scanner = new Scanner(System.in);

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
			System.out.println(newCustomer);
			customerController.listCustomers();
			customerController.addNewCustomer(newCustomer);

		} else {
			System.out.println("Thank you for your time.");
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

