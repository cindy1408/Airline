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
    public List<Customer> listAllCustomers() {
        System.out.println(customerService.getFullListCustomer());
        return customerService.getFullListCustomer();
    }

    @GetMapping(path = "/customer")
    public void requestedCustomer(String customerPassport) {
        customerService.requestedCustomer(customerPassport);
    }

    @GetMapping(path = "/greetCustomer")
    public void greetCustomer(String customerPassport) {
        customerService.greetCustomer(customerPassport);
    }

    @PostMapping
    public Customer addNewCustomer(Customer newCustomer) {
        return customerService.addNewCustomer(newCustomer);
    }

    @PutMapping
    public void updateCustomer(int customerId, int customerUpdateRequired){
        customerService().updateCustomer(customerId, customerUpdateRequired);
    }


    @DeleteMapping
    public void deleteCustomer(String customerPassport){
        customerService.deleteCustomer(customerPassport);
    }

}
