package flight.booking.service.domain.booking.ports.input;

import flight.booking.service.domain.booking.model.Ticket;
import flight.booking.service.domain.flight.model.Flight;
import flight.booking.service.domain.flight.model.Seat;

import java.util.List;

public interface BookingPort {
    Ticket orderTicket(Long userId, Flight flight, Seat seat);
    Ticket updateTicketStatus(Long ticketId, String status);
    List<Ticket> findTickets(Long flightId, Long userId);
    Ticket findTicket(Long ticketId);
}
