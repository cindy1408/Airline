package com.day9exercise.demo.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class EmployeeService {
    Scanner scanner = new Scanner(System.in);
    private final EmployeeRepositoryPostgres employeeRepositoryPostgres;

    @Autowired
    public EmployeeService(EmployeeRepositoryPostgres employeeRepositoryPostgres) {
        this.employeeRepositoryPostgres = employeeRepositoryPostgres;
    }

    //GET REQUEST
    public List<Employee> employeeFullList(){
        return employeeRepositoryPostgres.findAll();
    }

    public AtomicBoolean checkEmployeeLogin(int id, String username, String password){
        AtomicBoolean loggingSuccessful = new AtomicBoolean(false);
        employeeRepositoryPostgres.findById(id)
                .ifPresentOrElse(employee -> {
                    if(employee.getUsername().equals(username) && employee.getPassword().equals(password)){
                        System.out.println("Welcome " + employee.getEmployeeFirstName() + "! You have successfully logged in.");
                        loggingSuccessful.set(true);
                    }
                }, () -> {
                    System.out.println("The details you've entered are incorrect.");
                });
        return loggingSuccessful;
    }


    //POST REQUEST
    public void addNewEmployee(Employee newEmployee){
        employeeRepositoryPostgres.save(newEmployee);
    }

    //PUT REQUEST
    public void updateCurrentEmployee(int id, int updateEmployee){
        employeeRepositoryPostgres.findById(id)
                .ifPresentOrElse(employee -> {
                    switch (updateEmployee){
                        case 1:
                            System.out.println("Your current username is " + employee.getUsername() + "\nDo you want to change it? y/n");
                            String input = scanner.nextLine();
                            if(input.toLowerCase().trim().equals("y")){
                                System.out.println("Please enter your updated username");
                                String updatedUsername = scanner.nextLine();
                                if(updatedUsername != null){
                                    employee.setUsername(updatedUsername);
                                    System.out.println("Your details has been updated.");
                                    System.out.println(employee);
                                }
                            }
                            break;
                        case 2:
                            System.out.println("Your current password is " + employee.getPassword() + "\nDo you want to change it? y/n");
                            String password = scanner.nextLine();
                            if(password.toLowerCase().trim().equals("y")){
                                System.out.println("Please enter your updated password");
                                String updatedPassword = scanner.nextLine();
                                if(updatedPassword != null){
                                    employee.setPassword(updatedPassword);
                                    System.out.println("Your details has been updated.");
                                    System.out.println(employee);
                                }
                            }
                            break;
                        case 3:
                            System.out.println("Your current first name is " + employee.getEmployeeFirstName() + "\nDo you want to change it? y/n");
                            String employeeInput = scanner.nextLine();
                            if(employeeInput.toLowerCase().trim().equals("y")){
                                System.out.println("Please enter your updated first name");
                                String updatedFirstName = scanner.nextLine();
                                if(updatedFirstName != null){
                                    employee.setEmployeeFirstName(updatedFirstName);
                                    System.out.println("Your details has been updated.");
                                    System.out.println(employee);
                                }
                            }
                            break;
                        case 4:
                            System.out.println("Your current surname is " + employee.getEmployeeLastName() + "\nDo you want to change it? y/n");
                            String userInput = scanner.nextLine();
                            if(userInput.toLowerCase().trim().equals("y")){
                                System.out.println("Please enter your updated password");
                                String updatedLastName = scanner.nextLine();
                                if(updatedLastName != null){
                                    employee.setEmployeeLastName(updatedLastName);
                                    System.out.println("Your details has been updated.");
                                    System.out.println(employee);
                                }
                            }
                            break;
                        case 5:
                            System.out.println("Your current surname is " + employee.isCurrentEmployee() + "\nDo you want to change it? y/n");
                            String employInput = scanner.nextLine();
                            if(employInput.toLowerCase().trim().equals("y")){
                                System.out.println("Are you currently working here? y/n");
                                String updatedCurrentEmployee = scanner.nextLine();
                                boolean currentEmployee = false;
                                if(updatedCurrentEmployee.toLowerCase().trim().equals("y")){
                                    currentEmployee = true;
                                    employee.setCurrentEmployee(currentEmployee);
                                    System.out.println("Your details has been updated.");
                                    System.out.println(employee);
                                } else {
                                    currentEmployee = false;
                                    employee.setCurrentEmployee(currentEmployee);
                                    System.out.println("Your datils has been updated.");
                                    System.out.println(employee);
                                }
                            }
                            break;
                    }
                }, () -> {
                    System.out.println("The user id is not registered in our system.");
                });
    }


}
