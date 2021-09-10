package com.day9exercise.demo.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RequestMapping("api/customers")
@RestController
public class CustomerController {

    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> listCustomers(){
        return customerService.getFullListCustomer();
    }

    @PostMapping
    public Customer addNewCustomer(Customer newCustomer){
        return customerService.addNewCustomer(newCustomer);
    }

}
