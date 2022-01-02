package com.enset.customerqueriesservice.repositories;

import com.enset.customerqueriesservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}