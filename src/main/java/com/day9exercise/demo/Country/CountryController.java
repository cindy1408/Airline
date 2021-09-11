package com.day9exercise.demo.Country;

import com.day9exercise.demo.Customer.Customer;
import com.day9exercise.demo.Customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RequestMapping("api/country")
@RestController
public record CountryController(CountryService countryService) {

    @Autowired
    public CountryController {

    }
    @GetMapping
    public List<Country> listAllCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping("/country")
    public void requestedCountry(int countryId){
        countryService.requestedCountry(countryId);
    }

    @PostMapping
    public void insertNewCountry(Country newCountry){
        countryService.addNewCountry(newCountry);
    }

    @PutMapping
    public void updateCountry(int countryId, int requestedUpdate){
        countryService.updateCountry(countryId, requestedUpdate);
    }

    @DeleteMapping
    public void deleteCountry(int countryId){
        countryService.deleteCountry(countryId);
    }

}

