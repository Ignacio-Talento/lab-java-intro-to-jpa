package com.ironhack304.lab.repository;

import com.ironhack304.lab.model.Customer;
import com.ironhack304.lab.model.CustomerStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer("John Doe", CustomerStatus.GOLD, 5000);

        Customer savedCustomer = customerRepository.save(customer);

        assertThat(savedCustomer.getCustomerId()).isNotNull();
        assertThat(savedCustomer.getCustomerName()).isEqualTo("John Doe");
        assertThat(savedCustomer.getCustomerStatus()).isEqualTo(CustomerStatus.GOLD);
        assertThat(savedCustomer.getTotalCustomerMileage()).isEqualTo(5000);
    }

    @Test
    public void testFindCustomerByName() {
        Customer customer1 = new Customer("Alice Smith", CustomerStatus.SILVER, 3000);
        Customer customer2 = new Customer("Bob Smith", CustomerStatus.NONE, 1000);

        Customer savedCustomer1 = customerRepository.save(customer1);
        Customer savedCustomer2 = customerRepository.save(customer2);

        List<Customer> foundCustomers = customerRepository.findByCustomerNameContainingIgnoreCase("Smith");

        assertThat(foundCustomers).hasSize(2);
        assertThat(foundCustomers).extracting(Customer::getCustomerName).containsExactlyInAnyOrder("Alice Smith", "Bob Smith");
    }

    @Test
    public void testFindCustomersByStatus() {
        // Create test customers with different statuses
        Customer customer1 = new Customer("Alice Smith", CustomerStatus.SILVER, 3000);
        Customer customer2 = new Customer("Bob Smith", CustomerStatus.NONE, 1000);
        Customer customer3 = new Customer("Charlie Brown", CustomerStatus.GOLD, 7000);
        Customer customer4 = new Customer("Diana Prince", CustomerStatus.GOLD, 9000);

        // Save customers
        Customer savedCustomer1 = customerRepository.save(customer1);
        Customer savedCustomer2 = customerRepository.save(customer2);
        Customer savedCustomer3 = customerRepository.save(customer3);
        Customer savedCustomer4 = customerRepository.save(customer4);

        // Find customers with GOLD status
        List<Customer> goldCustomers = customerRepository.findByCustomerStatus(CustomerStatus.GOLD);

        // Verify we found the correct customers
        assertThat(goldCustomers).hasSize(2);
        assertThat(goldCustomers).extracting(Customer::getCustomerName)
                .containsExactlyInAnyOrder("Charlie Brown", "Diana Prince");
    }


}