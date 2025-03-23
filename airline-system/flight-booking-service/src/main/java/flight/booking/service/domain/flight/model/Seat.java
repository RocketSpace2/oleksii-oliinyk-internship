package flight.booking.service.domain.flight.model;

import java.util.Objects;

public record Seat(
        Long id,
        Flight flight,
        String seatNumber,
        Boolean isBooked,
        String seatClass
) {
    public Seat{
        Objects.requireNonNull(flight);
    }
}
