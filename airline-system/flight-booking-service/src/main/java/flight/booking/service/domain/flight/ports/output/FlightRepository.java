package flight.booking.service.domain.flight.ports.output;


import flight.booking.service.domain.flight.model.Flight;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository {
    Flight createFlight(Flight flight);
    Flight findFlight(Long id);
    List<Flight> findAllFlights(String fromLocation, String toLocation, LocalDateTime departureTime);
    void deleteFlight(Long id);
}
