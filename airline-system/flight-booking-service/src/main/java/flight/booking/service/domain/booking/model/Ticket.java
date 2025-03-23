package flight.booking.service.domain.booking.model;

import flight.booking.service.domain.flight.model.Flight;
import flight.booking.service.domain.flight.model.Seat;

import java.time.LocalDateTime;

public record Ticket(
        Long id,
        String ticketCode,
        Long userId,
        Flight flight,
        Seat seat,
        LocalDateTime bookingDate,
        String status,
        LocalDateTime cancellationDate
) {
}
