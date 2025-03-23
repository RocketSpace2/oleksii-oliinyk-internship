package flight.booking.service.domain.booking.service;

import flight.booking.service.domain.booking.model.Ticket;
import flight.booking.service.domain.booking.ports.input.BookingPort;

import flight.booking.service.domain.booking.ports.output.TicketRepository;
import flight.booking.service.domain.flight.model.Flight;
import flight.booking.service.domain.flight.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingPortImpl implements BookingPort {
    private static final String SYMBOLS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final SecureRandom random = new SecureRandom();

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Ticket orderTicket(Long userId, Flight flight, Seat seat) {
        Ticket ticket = new Ticket(
                null,
                generateTicketCode(),
                userId,
                flight,
                seat,
                LocalDateTime.now(),
                "PENDING",
                null
        );

        return ticketRepository.createTicket(ticket);
    }

    public static String generateTicketCode() {
        //Ticket code will look for example like "AB12DC"
        String str = "";

        //Generation of two letters
        str = str.concat(String.valueOf(SYMBOLS.charAt(random.nextInt(SYMBOLS.length()))));
        str = str.concat(String.valueOf(SYMBOLS.charAt(random.nextInt(SYMBOLS.length()))));

        //Generation of two numbers
        str = str.concat(String.valueOf(NUMBERS.charAt(random.nextInt(NUMBERS.length()))));
        str = str.concat(String.valueOf(NUMBERS.charAt(random.nextInt(NUMBERS.length()))));

        //Generation of two letters
        str = str.concat(String.valueOf(SYMBOLS.charAt(random.nextInt(SYMBOLS.length()))));
        str = str.concat(String.valueOf(SYMBOLS.charAt(random.nextInt(SYMBOLS.length()))));

        return str;
    }

    @Override
    public Ticket updateTicketStatus(Long ticketId, String status) {
        Ticket ticket = ticketRepository.findTicket(ticketId);

        Ticket ticketToCreate = new Ticket(
                ticket.id(),
                ticket.ticketCode(),
                ticket.userId(),
                ticket.flight(),
                ticket.seat(),
                ticket.bookingDate(),
                status,
                status.equals("CANCELED") ? LocalDateTime.now() : null
        );
        return ticketRepository.createTicket(ticketToCreate);
    }

    @Override
    public List<Ticket> findTickets(Long flightId, Long userId) {
        return ticketRepository.findTickets(flightId, userId);
    }

    @Override
    public Ticket findTicket(Long ticketId) {
        return ticketRepository.findTicket(ticketId);
    }
}
