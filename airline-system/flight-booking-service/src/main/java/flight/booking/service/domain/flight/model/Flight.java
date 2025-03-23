package flight.booking.service.domain.flight.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public record Flight(
        Long id,
        String flightNumber,
        String airlineName,
        String fromLocation,
        String toLocation,
        LocalDateTime departureTime,
        LocalDateTime arrivalTime,
        BigDecimal price,
        String flightType
) {
    public Flight{
        Objects.requireNonNull(flightNumber);
    }
}
