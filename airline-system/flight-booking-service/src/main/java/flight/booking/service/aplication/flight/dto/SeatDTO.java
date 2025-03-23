package flight.booking.service.aplication.flight.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import flight.booking.service.domain.flight.model.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatDTO {
    private Long id;
    private FlightDTO flightDTO;
    private String seatNumber;
    private Boolean isBooked;
    private String seatClass;

//    @JsonCreator
//    public SeatDTO(
//            @JsonProperty("id") Long id,
//            @JsonProperty("seatNumber") String seatNumber,
//            @JsonProperty("isBooked") Boolean isBooked,
//            @JsonProperty("seatClass") String seatClass
//    ) {
//        this.id = id;
//        this.seatNumber = seatNumber;
//        this.isBooked = isBooked;
//        this.seatClass = seatClass;
//    }

    public static SeatDTO fromDomain(Seat seat) {
        return new SeatDTO(
                seat.id(),
                FlightDTO.fromDomain(seat.flight()),
                seat.seatNumber(),
                seat.isBooked(),
                seat.seatClass()
        );
    }

    public Seat toDomain() {
        return new Seat(this.id,
                this.flightDTO.toDomain(),
                this.seatNumber,
                this.getIsBooked(),
                this.getSeatClass()
        );
    }
}
