package com.day9exercise.demo.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RequestMapping("api/customers")
@RestController
public record CustomerController(CustomerService customerService) {

    @Autowired
    public CustomerController {
    }

    @GetMapping
    public List<Customer> listCustomers() {
        return customerService.getFullListCustomer();
    }

    @GetMapping(path = "/customer")
    public void requestedCustomer(String customerPassport) {
        customerService.requestedCustomer(customerPassport);
    }

    @PostMapping
    public Customer addNewCustomer(Customer newCustomer) {
        return customerService.addNewCustomer(newCustomer);
    }



    @DeleteMapping
    public void deleteCustomer(String customerPassport){
        customerService.deleteCustomer(customerPassport);
    }

}
