package com.day9exercise.demo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeData")
public class CustomerRepository {

    private static List<Customer> customerDb = new ArrayList<>();

    private JdbcTemplate jdbcTemplate;
    @Autowired
    public CustomerRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    public void customerDb(){
        Customer customer1 = new Customer("Henry","Horrid", LocalDate.of(1994, 12, 10), "AHJSFKHAS");
        Customer customer2 = new Customer("Sara","Jones", LocalDate.of(1999, 8, 24), "AHJSFKHAS");
        customerDb.add(customer1);
        customerDb.add(customer2);

    }

    //GET REQUEST
    public List<Customer> selectAllCustomers(){
//        return customerDb;
        String sql = "SELECT * FROM customer";
        List<Customer> results = jdbcTemplate.queryForList(sql, Customer.class);
        return results;
    }

//    public int insertCustomer(UUID id, Customer newCustomer) {
//        customerDb.add(id, newCustomer);
//        return 1;
//    }


    //GET REQUEST
    Optional<Customer> selectedCustomer(Integer id){
        return customerDb.stream()
                .filter(customer -> customer.getId() == id)
                .findFirst();
    }

    //POST REQUEST
    public int insertNewCustomer(Customer newCustomer){
        customerDb.add(newCustomer);
        String sql = "INSERT INTO customer(firstName, lastName, dob, age, passport) VALUES(?, ?, ?, ?, ?)";
        int result = jdbcTemplate.update(sql, newCustomer.getFirstName(), newCustomer.getLastName(), newCustomer.getDob(), newCustomer.getAge(), newCustomer.getPassport());
        return result;
    }
}
