package flight.booking.service.aplication.flight.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import flight.booking.service.domain.flight.model.Flight;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class FlightDTO {
    private Long id;
    private String flightNumber;
    private String airlineName;
    private String fromLocation;
    private String toLocation;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private BigDecimal price;
    private String flightType;

    @JsonCreator
    public FlightDTO(
            @JsonProperty("id") Long id,
            @JsonProperty("flightNumber") String flightNumber,
            @JsonProperty("airlineName") String airlineName,
            @JsonProperty("fromLocation") String fromLocation,
            @JsonProperty("toLocation") String toLocation,
            @JsonProperty("departureTime") LocalDateTime departureTime,
            @JsonProperty("arrivalTime") LocalDateTime arrivalTime,
            @JsonProperty("price") BigDecimal price,
            @JsonProperty("flightType") String flightType
    ) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.airlineName = airlineName;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.flightType = flightType;
    }

    public static FlightDTO fromDomain(Flight flight) {
        return new FlightDTO(
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
                id,
                flightNumber,
                airlineName,
                fromLocation,
                toLocation,
                departureTime,
                arrivalTime,
                price,
                flightType
        );
    }
}