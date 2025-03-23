package flight.booking.service.infrastructure.flight.entity;

import flight.booking.service.domain.flight.model.Seat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "seats")
public class SeatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private FlightEntity flight;

    @Column(name = "seat_number", nullable = false, length = 5)
    private String seatNumber;

    @Column(name = "is_booked", nullable = false)
    private Boolean isBooked;

    @Column(name = "seat_class", nullable = false, length = 20)
    private String seatClass;

    public static SeatEntity fromDomain(Seat seat) {
        return new SeatEntity(
                seat.id(),
                FlightEntity.fromDomain(seat.flight()),
                seat.seatNumber(),
                seat.isBooked(),
                seat.seatClass()
        );
    }

    public Seat toDomain() {
        FlightEntity flightEntity = new FlightEntity();
        return new Seat(
                this.getId(),
                this.getFlight().toDomain(),
                this.getSeatNumber(),
                this.getIsBooked(),
                this.getSeatClass()
        );
    }
}
