package com.day9exercise.demo.Country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CountryService {
    private final CountryRepositoryPostgres countryRepositoryPostgres;
    Scanner scanner = new Scanner(System.in);

    @Autowired
    public CountryService(CountryRepositoryPostgres countryRepositoryPostgres) {
        this.countryRepositoryPostgres = countryRepositoryPostgres;
    }

    //GET REQUEST
    public List<Country> getAllCountries(){
        return countryRepositoryPostgres.findAll();
    }

    //GET REQUEST
    public Optional<Country> requestedCountry(int countryId){
        return countryRepositoryPostgres.findById(countryId);
    }

    //POST REQUEST
    public Country addNewCountry(Country newCountry){
        return countryRepositoryPostgres.save(newCountry);
    }

    //PUT REQUEST
    public void updateCountry(int countryId, int requestedUpdate){
        countryRepositoryPostgres.findById(countryId)
                .ifPresentOrElse(country -> {
                    switch(requestedUpdate){
                        case 1:
                            System.out.println("Current Country name is " + country.getName() + "\nwould you like to change it?y/n");
                            String ans = scanner.nextLine();
                            if(ans.toLowerCase().trim().equals("y")){
                                System.out.println("Please enter the updated name of the country");
                                String updatedName = scanner.nextLine();
                                country.setName(updatedName);
                                countryRepositoryPostgres.save(country);
                                System.out.println("Thank you, the country name has been updated to " + country.getName());
                            }
                            break;
                        case 2:
                            System.out.println("Current estimated time is " + country.getEstimatedTravelMinutes() + "\nWould you like to change it?y/n");
                            String input = scanner.nextLine();
                            if(input.toLowerCase().trim().equals("y")){
                                System.out.println("Please enter the estimated duration in minutes");
                                int updatedEstimatedDuration = scanner.nextInt();
                                country.setEstimatedTravelMinutes(updatedEstimatedDuration);
                                countryRepositoryPostgres.save(country);
                                System.out.println("Thank you, the country estimated duration has been updated to " + country.getEstimatedTravelMinutes());
                            }
                            break;
                        case 3:
                            System.out.println("Current price is " + country.getPrice() + "\nWould you like to change it?y/n");
                            String userInput = scanner.nextLine();
                            if(userInput.toLowerCase().trim().equals("y")){
                                System.out.println("Please enter the updated price");
                                double updatedPrice = scanner.nextDouble();
                                country.setPrice(updatedPrice);
                                countryRepositoryPostgres.save(country);
                                System.out.println("Thank you, the price has been updated to " + country.getPrice());
                            }
                            break;
                        default:
                            System.out.println("Please select one of the available options.");
                    }
                }, () -> {
                    System.out.println(countryId + " cannot be found in our database.");
                });
    }

    //DELETE REQUEST
    public void deleteCountry(int countryId){
        countryRepositoryPostgres.findById(countryId)
                .ifPresentOrElse(country -> {
                    System.out.println("Are you sure you want to delete " + country + "?");
                    String ans = scanner.nextLine();
                    if(ans.toLowerCase().trim().equals("y")){
                        countryRepositoryPostgres.delete(country);
                        System.out.println(country + " has been successfully deleted.");
                    } else {
                        System.out.println(country + " has not been deleted.");
                    }
                }, () -> {
                    System.out.println("Country id is not registered in our system");
                });
    }
}
