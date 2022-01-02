package com.enset.customerqueriesservice.controllers;

import com.enset.commonapi.queries.GetAllCustomersQuery;
import com.enset.commonapi.queries.GetCustomerQuery;
import com.enset.customerqueriesservice.entities.Customer;
import lombok.AllArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/query/customers")
@AllArgsConstructor
public class CustomerQueryController {

    private QueryGateway gateway;

    @GetMapping("/")
    public List<Customer> getAllCustomers() {
        List<Customer> customers = gateway
                .query(
                        new GetAllCustomersQuery(),
                        ResponseTypes.multipleInstancesOf(Customer.class)
                ).join();
        return customers;
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable String id) {
        Customer customer = gateway
                .query(
                        new GetCustomerQuery(id),
                        ResponseTypes.instanceOf(Customer.class)
                ).join();
        return customer;
    }

}
