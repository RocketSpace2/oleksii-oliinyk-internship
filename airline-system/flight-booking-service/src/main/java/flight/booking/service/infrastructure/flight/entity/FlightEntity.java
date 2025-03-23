package flight.booking.service.infrastructure.flight.entity;

import flight.booking.service.domain.flight.model.Flight;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flights")
public class FlightEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "flight_number", nullable = false, length = 10)
    private String flightNumber;

    @Column(name = "airline_name", nullable = false, length = 50)
    private String airlineName;

    @Column(name = "from_location", nullable = false, length = 50)
    private String fromLocation;

    @Column(name = "to_location", nullable = false, length = 50)
    private String toLocation;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "flight_type", nullable = false, length = 20)
    private String flightType;

    public static FlightEntity fromDomain(Flight flight) {
        return new FlightEntity(
                flight.id(),
                flight.flightNumber(),
                flight.airlineName(),
                flight.fromLocation(),
                flight.toLocation(),
                flight.departureTime(),
                flight.arrivalTime(),
                flight.price(),
                flight.flightType()
        );
    }

    public Flight toDomain() {
        return new Flight(
                this.getId(),
                this.getFlightNumber(),
                this.getAirlineName(),
                this.getFromLocation(),
                this.getToLocation(),
                this.getDepartureTime(),
                this.getArrivalTime(),
                this.getPrice(),
                this.getFlightType()
        );
    }
}