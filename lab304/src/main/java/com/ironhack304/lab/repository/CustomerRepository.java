package com.ironhack304.lab.repository;

import com.ironhack304.lab.model.Customer;
import com.ironhack304.lab.model.CustomerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByCustomerStatus(CustomerStatus status);
    List<Customer> findByTotalCustomerMileageGreaterThan(Integer mileage);
    List<Customer> findByCustomerNameContainingIgnoreCase(String name);
}
