package flight.booking.service.aplication.flight.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreateFlightRequest {
    private String flightNumber;
    private String airlineName;
    private String fromLocation;
    private String toLocation;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private BigDecimal price;
    private String flightType;
    private Integer totalSeats;

    @JsonCreator
    public CreateFlightRequest(
            @JsonProperty("flightNumber") String flightNumber,
            @JsonProperty("airlineName") String airlineName,
            @JsonProperty("fromLocation") String fromLocation,
            @JsonProperty("toLocation") String toLocation,
            @JsonProperty("departureTime") LocalDateTime departureTime,
            @JsonProperty("arrivalTime") LocalDateTime arrivalTime,
            @JsonProperty("price") BigDecimal price,
            @JsonProperty("flightType") String flightType,
            @JsonProperty("totalSeats") Integer totalSeats
    ) {
        this.flightNumber = flightNumber;
        this.airlineName = airlineName;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.flightType = flightType;
        this.totalSeats =totalSeats;
    }
}
