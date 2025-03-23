package flight.booking.service.infrastructure.booking.adapter;

import flight.booking.service.domain.booking.model.Ticket;
import flight.booking.service.domain.booking.ports.output.TicketRepository;
import flight.booking.service.infrastructure.booking.entity.TicketEntity;
import flight.booking.service.infrastructure.booking.repository.TicketEntityJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TicketRepositoryAdapter implements TicketRepository {
    @Autowired
    private TicketEntityJpaRepository ticketEntityJpaRepository;
    @Override
    public Ticket createTicket(Ticket ticket) {
        return ticketEntityJpaRepository.save(TicketEntity.fromDomain(ticket)).toDomain();
    }

    @Override
    public List<Ticket> findTickets(Long flightId, Long userId) {
        return ticketEntityJpaRepository.findAll(
                BookingSpecifications.matchesCriteria(flightId, userId)
        ).stream().map(TicketEntity::toDomain).toList();
    }


    @Override
    public Ticket findTicket(Long ticketId) {
        return ticketEntityJpaRepository.findById(ticketId).orElseThrow(
                () -> new RuntimeException("Ticket was not found")
        ).toDomain();
    }
}
