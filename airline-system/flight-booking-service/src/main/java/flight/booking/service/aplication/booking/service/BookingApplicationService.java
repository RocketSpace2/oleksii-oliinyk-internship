package flight.booking.service.aplication.booking.service;

import flight.booking.service.aplication.booking.dto.GetTicketResponse;
import flight.booking.service.aplication.booking.dto.TicketDTO;
import flight.booking.service.aplication.flight.dto.FlightDTO;
import flight.booking.service.aplication.flight.dto.SeatDTO;
import flight.booking.service.domain.booking.ports.input.BookingPort;
import flight.booking.service.domain.booking.ports.output.TicketRepository;
import flight.booking.service.domain.flight.ports.input.FlightPort;
import flight.booking.service.domain.flight.ports.output.FlightRepository;
import flight.booking.service.domain.flight.ports.output.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingApplicationService {
    @Autowired
    private BookingPort bookingPort;
    @Autowired
    private FlightPort flightPort;



    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private FlightRepository flightRepository;

    public GetTicketResponse orderTicket(Long userId, Long flightId, Long seatId) {
        FlightDTO flightDTO = FlightDTO.fromDomain(flightPort.findFlightById(flightId));
        SeatDTO seatDTO = SeatDTO.fromDomain(flightPort.findSeatById(seatId));

        TicketDTO ticketDTO = TicketDTO.fromDomain(bookingPort.orderTicket(
                userId,
                flightDTO.toDomain(),
                seatDTO.toDomain()
        ));

        return mapToGetTicketRequest(ticketDTO);
    }

    public GetTicketResponse updateTicketStatus(Long ticketId, String status){
        if(status.equals("CANCELED")){
            flightPort.freeSeat(TicketDTO.fromDomain(bookingPort.findTicket(ticketId)).getId());
        }

        return mapToGetTicketRequest(TicketDTO.fromDomain(bookingPort.updateTicketStatus(ticketId, status)));
    }

    public List<GetTicketResponse> findTickets(Long flightId, Long userId) {
        List<TicketDTO> tickets = bookingPort.findTickets(flightId, userId)
                .stream()
                .map(TicketDTO::fromDomain)
                .toList();

        return tickets.stream().map(this::mapToGetTicketRequest).collect(Collectors.toList());
    }

    private GetTicketResponse mapToGetTicketRequest(TicketDTO ticketDTO){
        return new GetTicketResponse(
                ticketDTO.getFlightDTO().getFlightNumber(),
                ticketDTO.getFlightDTO().getAirlineName(),
                ticketDTO.getFlightDTO().getFromLocation(),
                ticketDTO.getFlightDTO().getToLocation(),
                ticketDTO.getFlightDTO().getDepartureTime(),
                ticketDTO.getFlightDTO().getArrivalTime(),
                ticketDTO.getFlightDTO().getFlightType(),
                ticketDTO.getSeatDTO().getSeatNumber(),
                ticketDTO.getSeatDTO().getSeatClass(),
                ticketDTO.getTicketCode(),
                ticketDTO.getBookingDate()
        );
    }
}