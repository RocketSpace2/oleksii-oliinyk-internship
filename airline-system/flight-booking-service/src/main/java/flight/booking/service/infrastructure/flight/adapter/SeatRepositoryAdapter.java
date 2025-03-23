package flight.booking.service.infrastructure.flight.adapter;

import flight.booking.service.domain.flight.model.Flight;
import flight.booking.service.domain.flight.model.Seat;
import flight.booking.service.domain.flight.ports.output.SeatRepository;
import flight.booking.service.infrastructure.flight.entity.FlightEntity;
import flight.booking.service.infrastructure.flight.entity.SeatEntity;
import flight.booking.service.infrastructure.flight.repository.SeatEntityJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SeatRepositoryAdapter implements SeatRepository {
    @Autowired
    private SeatEntityJpaRepository seatJpaRepository;

    @Override
    public void createSeat(Seat seat) {
        seatJpaRepository.save(SeatEntity.fromDomain(seat));
    }

    @Override
    public List<Seat> findSeats(Flight flight) {
        return seatJpaRepository.findSeatByFlight(FlightEntity.fromDomain(flight))
                .stream()
                .map(seatEntity -> seatEntity.toDomain())
                .collect(Collectors.toList());
    }

    @Override
    public Seat findSeatById(Long id) {
        return seatJpaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Seat was not found")
        ).toDomain();
    }
}
