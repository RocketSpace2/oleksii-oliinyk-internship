package flight.booking.service.domain.flight.ports.input;

import flight.booking.service.domain.flight.model.Flight;
import flight.booking.service.domain.flight.model.Seat;


import java.time.LocalDateTime;
import java.util.List;

public interface FlightPort {
    Flight createFlight(Flight flight);
    void createSeats(Flight flight, Integer totalSeats);
    List<Flight> findAllFlights(String fromLocation, String toLocation, LocalDateTime departureTime);
    List<Seat> findSeats(Flight flight);
    Flight findFlightById(Long id);
    Seat findSeatById(Long id);
    void deleteFlight(Long id);
    void freeSeat(Long seatId);
}
