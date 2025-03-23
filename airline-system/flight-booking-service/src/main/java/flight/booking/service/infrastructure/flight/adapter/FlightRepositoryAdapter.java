package flight.booking.service.infrastructure.flight.adapter;

import flight.booking.service.domain.flight.model.Flight;
import flight.booking.service.domain.flight.ports.output.FlightRepository;
import flight.booking.service.infrastructure.flight.entity.FlightEntity;
import flight.booking.service.infrastructure.flight.repository.FlightEntityJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlightRepositoryAdapter implements FlightRepository {
    @Autowired
    private FlightEntityJpaRepository flightJpaRepository;

    @Override
    public Flight createFlight(Flight flight) {
        FlightEntity flightEntity = flightJpaRepository.save(FlightEntity.fromDomain(flight));
        return flightEntity.toDomain();
    }

    @Override
    public List<Flight> findAllFlights(String fromLocation, String toLocation, LocalDateTime departureTime) {
        List<FlightEntity> flightsFromDB = flightJpaRepository.findAll(
                FlightSpecifications.matchesCriteria(fromLocation, toLocation, departureTime)
        );

        return flightsFromDB.stream()
                .map(flightEntity -> flightEntity.toDomain())
                .collect(Collectors.toList());
    }

    @Override
    public Flight findFlight(Long id) {
        FlightEntity flightEntity = flightJpaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Flight was not found")
        );
        return flightEntity.toDomain();
    }

    @Override
    public void deleteFlight(Long id) {
        flightJpaRepository.deleteById(id);
    }
}
