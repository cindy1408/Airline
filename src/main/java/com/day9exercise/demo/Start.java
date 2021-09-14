package com.day9exercise.demo;

import com.day9exercise.demo.Country.Country;
import com.day9exercise.demo.Country.CountryController;
import com.day9exercise.demo.Customer.Customer;
import com.day9exercise.demo.Customer.CustomerController;
import com.day9exercise.demo.Employee.Employee;
import com.day9exercise.demo.Employee.EmployeeController;
import com.day9exercise.demo.Flight.Flight;
import com.day9exercise.demo.Flight.FlightController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Start {

    public void restartEmployeeSection(CustomerController customerController, CountryController countryController, EmployeeController employeeController, FlightController flightController){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Is there anything else you would like to do?");
        String input = scanner.nextLine();
        if(input.toLowerCase().trim().equals("y")){
            employeeSection(customerController, countryController, employeeController, flightController);
        } else {
            System.exit(0);
        }
    }

    public void restart(CustomerController customerController, CountryController countryController, EmployeeController employeeController, FlightController flightController){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Is there anything else you would like to do?");
        String input = scanner.nextLine();
        if(input.toLowerCase().trim().equals("y")){
            start(customerController, countryController, employeeController, flightController);
        } else {
            System.exit(0);
        }
    }

    public void start(CustomerController customerController, CountryController countryController, EmployeeController employeeController, FlightController flightController){
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
            String username = scanner.nextLine().toLowerCase().trim();
            System.out.println("Please enter your password (case sensitive) ");
            String password  = scanner.nextLine();

            AtomicBoolean loggingsuccessful = employeeController.checkEmployee(id, username, password);

            if(loggingsuccessful.get()){
                employeeSection(customerController, countryController, employeeController, flightController);

            } else {
                System.out.println("Invalid logging, please try again");
                start(customerController, countryController, employeeController, flightController);
            }

        } else if (input == 2 ) {
            customerSection(customerController,  countryController, employeeController, flightController);
            restart(customerController, countryController, employeeController, flightController);

        } else {
            System.out.println("Please enter a valid number from the list provided");
            start(customerController, countryController, employeeController, flightController);
        }

    }

    ///////////////////                     EMPLOYEE SECTION                      /////////////////////////
    public void employeeSection(CustomerController customerController, CountryController countryController, EmployeeController employeeController, FlightController flightController){
        Scanner scanner = new Scanner(System.in);
            System.out.println("\nWhat would you like to do?\n1. Search customer by passport?\n2. Delete customer by passport?\n3. Amend customer details\n4. View full list of customers\n5. Add new country destination\n6. Delete country from list\n7. Update country\n8. Find country details by country id\n9. Add new employee\n10. Employees full list\n11. Update current employees\n");
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
                String customerPassportDelete = scanner.nextLine().trim();
                Optional<Customer> deleteCustomer = customerController.requestedCustomer(customerPassportDelete);
                System.out.println("Are you sure you want to delete: " + deleteCustomer);
                String customerInput = scanner.nextLine().toLowerCase().trim();
                if(customerInput.equals("y")){
                    customerController.deleteCustomer(customerPassportDelete);
                }
                restartEmployeeSection(customerController, countryController, employeeController, flightController);
                break;
            case 3:
                System.out.println("Please type in the customer id number");
                int customerId = scanner.nextInt();
                System.out.println("Please type the required customer information that needs to be updated.\n1. first name\n2. surname\n3. passport number");
                int updateInfo = scanner.nextInt();
                customerController.updateCustomer(customerId, updateInfo);
                restartEmployeeSection(customerController, countryController, employeeController, flightController);
                break;
            case 4:
                customerController.listAllCustomers();
                restartEmployeeSection(customerController, countryController, employeeController, flightController);
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
                System.out.println("Please enter the date of flight in format yyyy-mm-ddTHH:MM");
                scanner.nextLine();
                String dateTime = scanner.nextLine();
                LocalDateTime arrivalDateTime = LocalDateTime.parse(dateTime);
                LocalDateTime departureDateTime = arrivalDateTime.plusMinutes(estimatedDuration);
                System.out.println("Please enter the flight number associated to this journey");
                String flightNumber = scanner.nextLine();
                Country newCountry = new Country(countryName, estimatedDuration, price, arrivalDateTime, departureDateTime, flightNumber);
                countryController.insertNewCountry(newCountry);
                restartEmployeeSection(customerController, countryController, employeeController, flightController);
                break;
            case 6:
                System.out.println("Please enter the country id you want to delete from the system");
                int countryId = scanner.nextInt();
                countryController.deleteCountry(countryId);
                restartEmployeeSection(customerController, countryController, employeeController, flightController);
                break;
            case 7:
                System.out.println("Please enter the country id you want to update");
                int updateCountry = scanner.nextInt();
                System.out.println("Is this the specific country you want to update?\n" + countryController.requestedCountry(updateCountry));
                scanner.nextLine();
                String userAnswer = scanner.nextLine().toLowerCase().trim();
                if(userAnswer.equals("y")){
                    System.out.println("which of the following do you need to update?\n1. Country name?\n2. Estimated Travel?\n3. Price\n4. Flight Number\n5. Time and Date departure\n");
                    int requestedUpdate = scanner.nextInt();
                    countryController.updateCountry(updateCountry, requestedUpdate);
                    restartEmployeeSection(customerController, countryController, employeeController, flightController);
                } else {
                    restartEmployeeSection(customerController, countryController, employeeController, flightController);
                }
                break;
            case 8:
                System.out.println("Please enter the country id");
                scanner.nextLine();
                int requestedCountryId = scanner.nextInt();
                countryController.requestedCountry(requestedCountryId);
                restartEmployeeSection(customerController, countryController, employeeController, flightController);
                break;
            case 9:
                registeringEmployee(employeeController);
                restartEmployeeSection(customerController, countryController, employeeController, flightController);
                break;
            case 10:
                employeeController.employeesFullList();
                restartEmployeeSection(customerController, countryController, employeeController, flightController);
                break;
            case 11:
                System.out.println("Please enter your employee id");
                int employeeId = scanner.nextInt();
                System.out.println("Please enter whether you want to update\n1. Username\n2. Password\n3. First name\n4. Surname\n5. Current Employee");
                int update = scanner.nextInt();
                employeeController.updateCurrentEmployees(employeeId, update);
                restartEmployeeSection(customerController, countryController, employeeController, flightController);
                break;
            default:
                employeeSection(customerController, countryController, employeeController, flightController);
        }
    }


    ///////////////////////////          CUSTOMER SECTION          //////////////////////////

    public void customerSection(CustomerController customerController, CountryController countryController, EmployeeController employeeController, FlightController flightController){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select the following options.\n1. View a flight\n2. Book a flight\n3.Update a flight\n4. Cancel a flight");
        int userOption = scanner.nextInt();
        switch (userOption){
            case 1:
                System.out.println("Please enter your customer id");
                int customerId = scanner.nextInt();
                flightController.viewUserFlight(customerId);
                System.out.println();
                restart(customerController, countryController, employeeController, flightController);
                break;
            case 2:
                    bookingFlightAndCheckCustomer(customerController, countryController, employeeController, flightController);
                    break;
            case 3:
                System.out.println("Please enter your passport number");
                scanner.nextLine();
                String passport = scanner.nextLine();
                customerController.requestedCustomer(passport);
                System.out.println("Please enter your flight id");
                int customerflightId = scanner.nextInt();
                flightController.updatedFlight(passport, customerflightId);

                break;
            case 4:
                System.out.println("Please enter your customer id number");
                scanner.nextLine();
                scanner.nextLine();
                System.out.println("Please enter the flight number you want to cancel");
                String flightNumber = scanner.nextLine();
                flightController.deleteFlightByCustomerFlightNumber(flightNumber);
        }

    }

    public void bookingFlightAndCheckCustomer(CustomerController customerController, CountryController countryController, EmployeeController employeeController, FlightController flightController){
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many people are you purchasing for... yourself included");
        int numberOfPassenger = scanner.nextInt();
        for(int i=1; i <= numberOfPassenger; i++){
            System.out.println("Is the person you're purchasing for registered?");
            scanner.nextLine();
            String registered = scanner.nextLine();
            checkIfCustomerIsRegistered(registered, numberOfPassenger, customerController, countryController, employeeController, flightController);
        }
        System.out.println("Thank you for purchasing.");

    }

    public void checkIfCustomerIsRegistered(String registered, int numberOfPassenger, CustomerController customerController, CountryController countryController, EmployeeController employeeController, FlightController flightController){
        Scanner scanner = new Scanner(System.in);
        if(registered.toLowerCase().trim().equals("y")){
            System.out.println("Please enter your passport number");
            String passportNumber = scanner.nextLine();
            Optional<Customer> existingCustomer1 = customerController.requestedCustomer(passportNumber);
            Customer existingCustomer = new Customer(existingCustomer1.get().getFirstName(), existingCustomer1.get().getLastName(), existingCustomer1.get().getDob(), existingCustomer1.get().getPassport());
            customerController.greetCustomer(passportNumber);
            bookFlight(existingCustomer, numberOfPassenger, passportNumber, customerController, countryController, employeeController, flightController);
        } else {
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

            bookFlight(newCustomer, numberOfPassenger, passport, customerController, countryController, employeeController, flightController);
        }
    }

    public void bookFlight (Customer newCustomer, int numberOfPassenger, String passportNumber, CustomerController customerController, CountryController countryController, EmployeeController employeeController, FlightController flightController){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Here are the list of countries, where would you like to go?");
        countryController.listAllCountries();
        System.out.println("Please type the id of your chosen option");
        int chosenHoliday = scanner.nextInt();
        Optional<Customer> customerDetails = customerController.requestedCustomer(passportNumber);
        System.out.println(customerDetails);
        Optional<Country> countryDetails = countryController.requestedCountry(chosenHoliday);
        int customerId = customerDetails.get().getId();
        int countryId = countryDetails.get().getId();
        double countryPrice = countryDetails.get().getPrice();
        double totalPrice = countryPrice*numberOfPassenger;
        String flightNumber = countryDetails.get().getFlightNumber();
        String customerFlightNumber = findRandomSeatNumber(flightNumber);
        boolean returnFlights = false;
        System.out.println("Would you like to purchase a return ticket?");
        scanner.nextLine();
        String returnTicket = scanner.nextLine();
        if(returnTicket.toLowerCase().equals("y")){
            System.out.println("Please enter the date and time you want to return in this format YYYY-MM-DDTHH:MM");
            String rawReturnTimeDate = scanner.nextLine();
            LocalDateTime returnTimeDateDeparture = LocalDateTime.parse(rawReturnTimeDate);
            LocalDateTime returnTimeDateArrival = returnTimeDateDeparture.plusMinutes(countryDetails.get().getEstimatedTravelMinutes());
            returnFlights = true;
            Flight newFlight = new Flight(countryId, customerId, returnFlights, returnTimeDateDeparture, returnTimeDateArrival, numberOfPassenger, totalPrice, customerFlightNumber);
            flightController.addNewFlight(newFlight);
        } else {
            Flight newFlight = new Flight(countryId, customerId, returnFlights, numberOfPassenger, totalPrice, customerFlightNumber);
            flightController.addNewFlight(newFlight);
        }
    }

    public void registeringEmployee(EmployeeController employeeController){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your username (new user)");
        String newUsername = scanner.nextLine();
        System.out.println("Please create your password (new user)");
        String newPassword = scanner.nextLine();
        System.out.println("Please confirm your password (new user)");
        String passwordConfirmed = scanner.nextLine();
        if(newPassword.equals(passwordConfirmed)){
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
        } else {
            System.out.println("Your password does not match.");
            registeringEmployee(employeeController);
        }
    }


    public String findRandomSeatNumber(String flightNumber){
        Random r = new Random();
        char row = (char)(r.nextInt(26) + 'a');
        int randomNumber = (int) (Math.random() * 2 + 1);
        String customerFlightNumber;
        customerFlightNumber = flightNumber + " " + row + randomNumber;
        return customerFlightNumber;
    }

}
