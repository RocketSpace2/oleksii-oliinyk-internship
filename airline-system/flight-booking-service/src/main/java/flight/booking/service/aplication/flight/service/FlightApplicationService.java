package flight.booking.service.aplication.flight.service;

import flight.booking.service.aplication.flight.dto.FlightDTO;
import flight.booking.service.aplication.flight.dto.GetFlightRequest;
import flight.booking.service.aplication.flight.dto.SeatDTO;
import flight.booking.service.domain.flight.model.Flight;
import flight.booking.service.domain.flight.ports.input.FlightPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightApplicationService {
    @Autowired
    private FlightPort flightPort;

    public FlightDTO createFlight(String flightNumber, String airlineName, String fromLocation,
                                     String toLocation, LocalDateTime departureTime, LocalDateTime arrivalTime,
                                     BigDecimal price, String flightType, Integer totalSeats
    ){
        FlightDTO flightDTO = new FlightDTO(
                null,
                flightNumber,
                airlineName,
                fromLocation,
                toLocation,
                departureTime,
                arrivalTime,
                price,
                flightType
        );

        flightDTO = FlightDTO.fromDomain(flightPort.createFlight(flightDTO.toDomain()));

        flightPort.createSeats(flightDTO.toDomain(),totalSeats);

        return flightDTO;
    }

    public List<GetFlightRequest> searchFlights(String fromLocation, String toLocation, LocalDateTime departureTime){
        List<Flight> flights = flightPort.findAllFlights(fromLocation, toLocation, departureTime);

        List<FlightDTO> flightsDTO = flights.stream().map(FlightDTO::fromDomain).toList();

        return flightsDTO.stream().map(this::mapToGetFlightRequest).collect(Collectors.toList());
    }

    private GetFlightRequest mapToGetFlightRequest(FlightDTO flightDTO){
        GetFlightRequest getFlightRequest = new GetFlightRequest(
                flightDTO.getId(),
                flightDTO.getFlightNumber(),
                flightDTO.getAirlineName(),
                flightDTO.getFromLocation(),
                flightDTO.getToLocation(),
                flightDTO.getDepartureTime(),
                flightDTO.getArrivalTime(),
                flightDTO.getPrice(),
                flightDTO.getFlightType(),
                null,
                null,
                null
        );


        List<SeatDTO> seats = flightPort.findSeats(flightDTO.toDomain())
                .stream()
                .map(SeatDTO::fromDomain)
                .toList();

        getFlightRequest.setTotalSeats(seats.size());
        getFlightRequest.setFreeSeats((int) seats.stream().filter(seat -> !seat.getIsBooked()).count());
        getFlightRequest.setSeats(seats);

        return getFlightRequest;
    }

    public GetFlightRequest findFlightById(Long id){
        FlightDTO flightDTO = FlightDTO.fromDomain(flightPort.findFlightById(id));
        return mapToGetFlightRequest(flightDTO);
    }

    public FlightDTO updateFlight(FlightDTO flightDTO){
        return FlightDTO.fromDomain(flightPort.createFlight(flightDTO.toDomain()));
    }

    public void deleteFlight(Long id){
        flightPort.deleteFlight(id);
    }
}
