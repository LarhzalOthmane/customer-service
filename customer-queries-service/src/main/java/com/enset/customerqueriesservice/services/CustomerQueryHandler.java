package com.enset.customerqueriesservice.services;

import com.enset.commonapi.queries.GetAllCustomersQuery;
import com.enset.commonapi.queries.GetCustomerQuery;
import com.enset.customerqueriesservice.entities.Customer;
import com.enset.customerqueriesservice.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class CustomerQueryHandler {

    private CustomerRepository repository;

    @QueryHandler
    public List<Customer> on(GetAllCustomersQuery query) {
        return repository.findAll();
    }

    @QueryHandler
    public Customer on(GetCustomerQuery query) {
        return repository.findById(query.getId()).get();
    }
}
