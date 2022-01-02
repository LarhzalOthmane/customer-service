package com.enset.customerqueriesservice;

import com.enset.customerqueriesservice.entities.Customer;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerQueriesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerQueriesServiceApplication.class, args);
        Customer customer = new Customer();
        customer.setEmail("Titoss.titime@gmail.com");
        System.out.println(customer.getEmail());
    }

    @Bean
    CommandBus commandBus() {
        return SimpleCommandBus.builder().build();
    }

}
