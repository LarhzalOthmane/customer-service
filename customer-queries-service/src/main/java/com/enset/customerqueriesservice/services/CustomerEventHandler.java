package com.enset.customerqueriesservice.services;

import com.enset.commonapi.events.CustomerCreatedEvent;
import com.enset.commonapi.events.CustomerUpdatedEvent;
import com.enset.commonapi.queries.GetAllCustomersQuery;
import com.enset.commonapi.queries.GetCustomerQuery;
import com.enset.customerqueriesservice.entities.Customer;
import com.enset.customerqueriesservice.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class CustomerEventHandler {

    private CustomerRepository repository;

    @EventHandler
    public void on(CustomerCreatedEvent event) {
        log.info("------------- CustomerCreatedEvent fired up -------------");
        Customer customer = new Customer(
                event.getId(),
                event.getName(),
                event.getEmail()
        );
        repository.save(customer);
    }

    @EventHandler
    public void on(CustomerUpdatedEvent event) {
        log.info("------------- CustomerUpdatedEvent fired up -------------");
        Customer customer = new Customer(
                event.getId(),
                event.getName(),
                event.getEmail()
        );
        repository.save(customer);
    }

}
