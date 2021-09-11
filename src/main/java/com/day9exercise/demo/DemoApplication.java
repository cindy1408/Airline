package com.day9exercise.demo;
import com.day9exercise.demo.Country.Country;
import com.day9exercise.demo.Country.CountryController;
import com.day9exercise.demo.Customer.*;
import com.day9exercise.demo.Employee.Employee;
import com.day9exercise.demo.Employee.EmployeeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

@SpringBootApplication
public class DemoApplication {
	private static CustomerController customerController;
	private static CountryController countryController;
	private static EmployeeController employeeController;

	public DemoApplication(CustomerController customerController, CountryController countryController, EmployeeController employeeController) {
		this.customerController = customerController;
		this.countryController = countryController;
		this.employeeController = employeeController;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		Scanner scanner = new Scanner(System.in);

		System.out.println("Sign in as Employee or Customer?\n1. Employee\n2. Customer");
		int input = scanner.nextInt();
		if(input == 1){
			// EMPLOYEE SECTION
			System.out.println("Please enter your employee id");
			scanner.nextLine();
			int id = scanner.nextInt();
			System.out.println("Please enter your username");
			scanner.nextLine();
			String username = scanner.nextLine();
			System.out.println("Please enter your password");
			String password  = scanner.nextLine();

			AtomicBoolean loggingsuccessful = employeeController.checkEmployee(id, username, password);
			System.out.println(loggingsuccessful);

			if(loggingsuccessful.get()){
				System.out.println("logging successfully.\nWhat would you like to do?\n1. Search customer by passport?\n2. Delete customer by passport?\n3. Amend customer details\n4. View full list of customers\n5. Add new country destination\n6. Delete country from list\n7. Update country\n8. Find country details by name\n9. Add new employee\n10. Employees full list\n11. Update current employees");
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
						break;
					case 8:
						System.out.println("Please enter the country id");
						scanner.nextLine();
						int requestedCountryId = scanner.nextInt();
						countryController.requestedCountry(requestedCountryId);
						break;
					case 9:
						System.out.println("Please enter your username");
						scanner.nextLine();
						String newUsername = scanner.nextLine();
						System.out.println("Please enter your password");
						String newPassword = scanner.nextLine();
						System.out.println("Please enter your first name");
						String newFirstName = scanner.nextLine();
						System.out.println("Please enter your surname");
						String newSurname = scanner.nextLine();
						System.out.println("Please enter your National Insurance number");
						String newNationalInsurance = scanner.nextLine();
						System.out.println("Are you a current employee? y/n");
						String answer = scanner.nextLine();
						Boolean currentEmployee = false;
						if(answer.toLowerCase().trim().equals("y")){
							currentEmployee = true;
						} else {
							currentEmployee = false;
						}
						Employee newEmployee = new Employee(newUsername, newPassword, newFirstName, newSurname, newNationalInsurance, currentEmployee);
						employeeController.insertNewEmployee(newEmployee);
						System.out.println("Thank you, your employee id is " + newEmployee.getId());
						break;
					case 10:
						employeeController.employeesFullList();
						break;
					case 11:
						System.out.println("Please enter your employee id");
						int employeeId = scanner.nextInt();
						System.out.println("Please enter whether you want to update\n1. Username\n2. Password\n3. First name\n4. Surname\n5. Current Employee");
						int update = scanner.nextInt();
						employeeController.updateCurrentEmployees(employeeId, update);
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

