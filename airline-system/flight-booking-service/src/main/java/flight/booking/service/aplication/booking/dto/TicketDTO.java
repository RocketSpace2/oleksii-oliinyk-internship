package flight.booking.service.aplication.booking.dto;

import flight.booking.service.aplication.flight.dto.FlightDTO;
import flight.booking.service.aplication.flight.dto.SeatDTO;
import flight.booking.service.domain.booking.model.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TicketDTO {
    private Long id;
    private String ticketCode;
    private Long userid;
    private FlightDTO flightDTO;
    private SeatDTO seatDTO;
    private LocalDateTime bookingDate;
    private String status;
    private LocalDateTime cancellationDate;

    public static TicketDTO fromDomain(Ticket ticket) {
        return new TicketDTO(
                ticket.id(),
                ticket.ticketCode(),
                ticket.userId(),
                FlightDTO.fromDomain(ticket.flight()),
                SeatDTO.fromDomain(ticket.seat()),
                ticket.bookingDate(),
                ticket.status(),
                ticket.cancellationDate()
        );
    }

    public Ticket toDomain() {
        return new Ticket(
                null,
                this.getTicketCode(),
                this.getUserid(),
                this.getFlightDTO().toDomain(),
                this.getSeatDTO().toDomain(),
                this.getBookingDate(),
                this.getStatus(),
                this.getCancellationDate()
        );
    }
}
