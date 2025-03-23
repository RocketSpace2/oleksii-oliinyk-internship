package flight.booking.service.domain.flight.service;


import flight.booking.service.domain.flight.model.Flight;
import flight.booking.service.domain.flight.model.Seat;
import flight.booking.service.domain.flight.ports.input.FlightPort;
import flight.booking.service.domain.flight.ports.output.FlightRepository;
import flight.booking.service.domain.flight.ports.output.SeatRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightPortImpl implements FlightPort {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private SeatRepository seatRepository;

    @Override
    public Flight createFlight(Flight flight) {
        return flightRepository.createFlight(flight);
    }

    @Override
    public void createSeats(Flight flight, Integer totalSeats) {
        int seatsPerRow = 5;

        for (int i = 0; i < totalSeats; i++) {
            int row = (i / seatsPerRow) + 1;
            char seatLetter = (char) ('A' + (i % seatsPerRow));
            String seatNumber = row + String.valueOf(seatLetter);

            seatRepository.createSeat(new Seat(
                    null,
                    flight,
                    seatNumber,
                    false,
                    determineSeatClass(row, seatNumber)
            ));
        }
    }

    private String determineSeatClass(int row, String seatNumber){
        if(row == 1){
            return "FIRST";
        } else if (seatNumber.matches("2A|2E")) {
            return "ECONOMY";
        }else{
            return "BUSINESS";
        }
    }

    @Override
    public List<Flight> findAllFlights(String fromLocation, String toLocation, LocalDateTime departureTime) {
        return flightRepository.findAllFlights(fromLocation, toLocation, departureTime);
    }

    @Override
    public List<Seat> findSeats(Flight flight) {
        return seatRepository.findSeats(flight);
    }


    @Override
    public Flight findFlightById(Long id) {
        return flightRepository.findFlight(id);
    }

    @Override
    public Seat findSeatById(Long id) {
        return seatRepository.findSeatById(id);
    }

    @Override
    public void deleteFlight(Long id) {
        flightRepository.deleteFlight(id);
    }

    @Override
    public void freeSeat(Long seatId) {
        Seat seat = seatRepository.findSeatById(seatId);

        Seat seatToCreate = new Seat(
                seat.id(),
                seat.flight(),
                seat.seatNumber(),
                false,
                seat.seatClass()
        );
        seatRepository.createSeat(seatToCreate);
    }
}
