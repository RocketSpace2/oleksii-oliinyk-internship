package flight.booking.service.aplication.flight.controller;


import flight.booking.service.aplication.flight.dto.CreateFlightRequest;
import flight.booking.service.aplication.flight.dto.FlightDTO;
import flight.booking.service.aplication.flight.dto.GetFlightRequest;
import flight.booking.service.aplication.flight.service.FlightApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/flights")
public class FlightController {
    @Autowired
    private FlightApplicationService flightApplicationService;

    @PostMapping
    public FlightDTO createFlight(@RequestBody CreateFlightRequest request) {
        return flightApplicationService.createFlight(
                request.getFlightNumber(),
                request.getAirlineName(),
                request.getFromLocation(),
                request.getToLocation(),
                request.getDepartureTime(),
                request.getArrivalTime(),
                request.getPrice(),
                request.getFlightType(),
                request.getTotalSeats()
        );
    }

    @GetMapping
    public List<GetFlightRequest> getFlights(
            @RequestParam(required = false) String fromLocation,
            @RequestParam(required = false) String toLocation,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureTime
    ) {
        return flightApplicationService.searchFlights(fromLocation, toLocation, departureTime);
    }

    @GetMapping("/{id}")
    public GetFlightRequest getFlightById(@PathVariable Long id) {
        return flightApplicationService.findFlightById(id);
    }

    @PutMapping()
    public FlightDTO updateFlight(@RequestBody FlightDTO flightDTO) {
        return flightApplicationService.updateFlight(flightDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightApplicationService.deleteFlight(id);
    }
}
