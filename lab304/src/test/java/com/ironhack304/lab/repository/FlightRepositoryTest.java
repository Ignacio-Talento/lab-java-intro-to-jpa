package com.ironhack304.lab.repository;

import com.ironhack304.lab.model.Flight;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FlightRepositoryTest {

    @Autowired
    private FlightRepository flightRepository;

    @Test
    public void testCreateFlight() {
        Flight flight = new Flight("AA123", "Boeing 737", 180, 1200);
        Flight savedFlight = flightRepository.save(flight);
        assertThat(savedFlight.getFlightId()).isNotNull();
        assertThat(savedFlight.getFlightNumber()).isEqualTo("AA123");
        assertThat(savedFlight.getAircraft()).isEqualTo("Boeing 737");
        assertThat(savedFlight.getTotalAircraftSeats()).isEqualTo(180);
        assertThat(savedFlight.getFlightMileage()).isEqualTo(1200);
    }

    @Test
    public void testFindFlightByFlightNumber() {
        Flight flight1 = new Flight("AA123", "Boeing 737", 180, 1200);
        Flight flight2 = new Flight("AA456", "Boeing 747", 160, 1000);
        Flight savedFlight1 = flightRepository.save(flight1);
        Flight savedFlight2 = flightRepository.save(flight2);
        assertThat(flightRepository.findByFlightNumber("AA123")).isEqualTo(savedFlight1);
    }

    @Test
    public void testFindFlightsByAircraftContainingBoeing() {
        // Create test flights with different aircraft types
        Flight flight1 = new Flight("AA123", "Boeing 737", 180, 1200);
        Flight flight2 = new Flight("DL456", "Airbus A320", 150, 900);
        Flight flight3 = new Flight("UA789", "Boeing 787", 250, 2500);
        Flight flight4 = new Flight("LH101", "Airbus A380", 500, 5000);

        Flight savedFlight1 = flightRepository.save(flight1);
        Flight savedFlight2 = flightRepository.save(flight2);
        Flight savedFlight3 = flightRepository.save(flight3);
        Flight savedFlight4 = flightRepository.save(flight4);

        List<Flight> allFlights = flightRepository.findAll();
        List<Flight> boeingFlights = allFlights.stream()
                .filter(flight -> flight.getAircraft().contains("Boeing"))
                .toList();

        assertThat(boeingFlights).hasSize(2);
        assertThat(boeingFlights).extracting(Flight::getFlightNumber)
                .containsExactlyInAnyOrder("AA123", "UA789");
    }

    @Test
    public void testFindFlightsWithMileageGreaterThan500() {
        // Create test flights with different mileages
        Flight flight1 = new Flight("AA123", "Boeing 737", 180, 1200);
        Flight flight2 = new Flight("DL456", "Airbus A320", 150, 400);
        Flight flight3 = new Flight("UA789", "Boeing 787", 250, 2500);
        Flight flight4 = new Flight("LH101", "Airbus A380", 500, 500);

        Flight savedFlight1 = flightRepository.save(flight1);
        Flight savedFlight2 = flightRepository.save(flight2);
        Flight savedFlight3 = flightRepository.save(flight3);
        Flight savedFlight4 = flightRepository.save(flight4);

        List<Flight> allFlights = flightRepository.findAll();
        List<Flight> longDistanceFlights = allFlights.stream()
                .filter(flight -> flight.getFlightMileage() > 500)
                .toList();

        // Verify we found the correct flights
        assertThat(longDistanceFlights).hasSize(2);
        assertThat(longDistanceFlights).extracting(Flight::getFlightNumber)
                .containsExactlyInAnyOrder("AA123", "UA789");
    }
}