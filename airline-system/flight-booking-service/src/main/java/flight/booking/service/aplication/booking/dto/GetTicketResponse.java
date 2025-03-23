package flight.booking.service.aplication.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTicketResponse {
    String flightNumber;
    String airlineName;
    String fromLocation;
    String toLocation;
    LocalDateTime departureTime;
    LocalDateTime arrivalTime;
    String flightType;
    String seatNumber;
    String seatClass;
    String ticketCode;
    LocalDateTime bookingDate;

}
