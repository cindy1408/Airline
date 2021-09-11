//package com.day9exercise.demo.Customer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//
//@Repository
//public class CustomerRepository {
//
//    private static List<Customer> customerDb = new ArrayList<>();
//
//    private JdbcTemplate jdbcTemplate;
//    @Autowired
//    public CustomerRepository(JdbcTemplate jdbcTemplate){
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//
//    //GET REQUEST
//    public List<Customer> selectAllCustomers(){
////        return customerDb;
//        String sql = "SELECT * FROM customer";
//        List<Customer> results = jdbcTemplate.queryForList(sql, Customer.class);
//        return results;
//    }
//
//
//    //GET REQUEST
//    Optional<Customer> selectedCustomer(Integer id){
//        return customerDb.stream()
//                .filter(customer -> customer.getId() == id)
//                .findFirst();
//    }
//
//    //POST REQUEST
//    public int insertNewCustomer(Customer newCustomer){
//        customerDb.add(newCustomer);
//        String sql = "INSERT INTO customer(firstName, lastName, dob, age, passport) VALUES(?, ?, ?, ?, ?)";
//        int result = jdbcTemplate.update(sql, newCustomer.getFirstName(), newCustomer.getLastName(), newCustomer.getDob(), newCustomer.getAge(), newCustomer.getPassport());
//        return result;
//    }
//}
