package flight.booking.service.infrastructure.booking.entity;


import flight.booking.service.domain.booking.model.Ticket;
import flight.booking.service.infrastructure.flight.entity.FlightEntity;
import flight.booking.service.infrastructure.flight.entity.SeatEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ticket_code", nullable = false, length = 10)
    private String ticketCode = null;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private FlightEntity flightEntity;

    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private SeatEntity seatEntity;

    @Column(name = "booking_date", nullable = false)
    private LocalDateTime bookingDate;

    @Column(name = "status", length = 20, nullable = false)

    private String status;

    @Column(name = "cancellation_date")
    private LocalDateTime cancellationDate;

    public static TicketEntity fromDomain(Ticket ticket) {
        return new TicketEntity(
                ticket.id(),
                ticket.ticketCode(),
                ticket.userId(),
                FlightEntity.fromDomain(ticket.flight()),
                SeatEntity.fromDomain(ticket.seat()),
                ticket.bookingDate(),
                ticket.status(),
                ticket.cancellationDate()
        );
    }

    public Ticket toDomain() {
        return new Ticket(
                this.getId(),
                this.getTicketCode(),
                this.getUserId(),
                this.getFlightEntity().toDomain(),
                this.getSeatEntity().toDomain(),
                this.getBookingDate(),
                this.getStatus(),
                this.getCancellationDate()
        );
    }
}
