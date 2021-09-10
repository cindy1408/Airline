package com.day9exercise.demo;
import com.day9exercise.demo.Customer.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.day9exercise.demo"})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		}

		@Bean
		CommandLineRunner commandLineRunner(CustomerRepositoryPostgres customerRepositoryPostgres){
		return args -> {
			Customer newCustomer = new Customer("Harry", "Jones", LocalDate.of(1994, 12,21), "JONES19027490124");
			customerRepositoryPostgres.save(newCustomer);
		};

//		JdbcTemplate jdbcTemplate = new JdbcTemplate();
//		CustomerRepository customerRepository = new CustomerRepository(jdbcTemplate);
//		CustomerService customerService = new CustomerService(customerRepository);
//		CustomerController customerController = new CustomerController(customerService);
//
//		customerController.listCustomers();
//
//		Start start = new Start(customerController);
//		start.welcome(customerRepository);

	}

}
