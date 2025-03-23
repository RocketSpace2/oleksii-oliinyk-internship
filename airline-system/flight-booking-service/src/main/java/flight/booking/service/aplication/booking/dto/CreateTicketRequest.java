package flight.booking.service.aplication.booking.dto;


import flight.booking.service.aplication.flight.dto.FlightDTO;
import flight.booking.service.aplication.flight.dto.SeatDTO;
import flight.booking.service.infrastructure.flight.entity.FlightEntity;
import flight.booking.service.infrastructure.flight.entity.SeatEntity;
import lombok.Data;

@Data
public class CreateTicketRequest {
    private Long flightId;
    private Long seatId;
}
