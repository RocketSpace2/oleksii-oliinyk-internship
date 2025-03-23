package flight.booking.service.domain.flight.ports.output;

import flight.booking.service.domain.flight.model.Flight;
import flight.booking.service.domain.flight.model.Seat;

import java.util.List;

public interface SeatRepository {
    void createSeat(Seat Seat);
    List<Seat> findSeats(Flight flight);
    Seat findSeatById(Long id);
}
