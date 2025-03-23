package flight.booking.service.aplication.flight.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class GetFlightRequest {
    private Long id;
    private String flightNumber;
    private String airlineName;
    private String fromLocation;
    private String toLocation;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private BigDecimal price;
    private String flightType;
    private Integer totalSeats;
    private Integer freeSeats;
    private List<SeatDTO> seats;
}
