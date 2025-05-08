package com.ironhack304.lab.repository;

import com.ironhack304.lab.model.Customer;
import com.ironhack304.lab.model.Flight;
import com.ironhack304.lab.model.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightBookingRepository extends JpaRepository<FlightBooking, Integer> {
    List<FlightBooking> findByCustomer(Customer customer);
    List<FlightBooking> findByFlight(Flight flight);
}
