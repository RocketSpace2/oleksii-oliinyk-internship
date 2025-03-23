package flight.booking.service.domain.booking.ports.output;

import flight.booking.service.domain.booking.model.Ticket;

import java.util.List;

public interface TicketRepository {
    Ticket createTicket(Ticket ticket);
    List<Ticket> findTickets(Long flightId, Long userId);
    Ticket findTicket(Long ticketId);
}
