package com.ironhack304.lab.repository;

import com.ironhack304.lab.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    Optional<Flight> findByFlightNumber(String flightNumber);
    List<Flight> findByAircraft(String aircraft);
    List<Flight> findByTotalAircraftSeatsGreaterThan(Integer seats);
    List<Flight> findByFlightMileageLessThanEqual(Integer mileage);
}
