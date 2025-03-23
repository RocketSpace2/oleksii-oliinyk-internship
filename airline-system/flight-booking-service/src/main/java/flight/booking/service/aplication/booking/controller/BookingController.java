package flight.booking.service.aplication.booking.controller;

import flight.booking.service.aplication.booking.dto.CreateTicketRequest;
import flight.booking.service.aplication.booking.dto.GetTicketResponse;
import flight.booking.service.aplication.booking.service.BookingApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class BookingController {
    @Autowired
    private BookingApplicationService bookingApplicationService;

    @PostMapping
    public GetTicketResponse orderTicket(
            @RequestBody CreateTicketRequest createTicketRequest,
            @RequestHeader("User-Id") String userId
    ){
        return bookingApplicationService.orderTicket(
                Long.valueOf(userId),
                createTicketRequest.getFlightId(),
                createTicketRequest.getSeatId()
        );
    }

    @PatchMapping("/cancel/{ticketId}")
    public GetTicketResponse cancelTicket(@PathVariable Long ticketId){
        return bookingApplicationService.updateTicketStatus(ticketId, "CANCELED");
    }

    @PatchMapping("/confirm/{ticketId}")
    public GetTicketResponse confirmTicket(@PathVariable Long ticketId){
        return bookingApplicationService.updateTicketStatus(ticketId, "CONFIRMED");
    }

    @GetMapping
    public List<GetTicketResponse> findTickets(
            @RequestParam(name = "flightId", required = false) Long flightId,
            @RequestHeader("User-Id") String userId
    ){
        return bookingApplicationService.findTickets(
                flightId,
                Long.valueOf(userId)
        );
    }
}
