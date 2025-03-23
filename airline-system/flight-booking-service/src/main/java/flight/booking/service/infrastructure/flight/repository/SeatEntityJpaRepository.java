package flight.booking.service.infrastructure.flight.repository;

import flight.booking.service.domain.flight.model.Flight;
import flight.booking.service.infrastructure.flight.entity.FlightEntity;
import flight.booking.service.infrastructure.flight.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatEntityJpaRepository extends JpaRepository<SeatEntity, Long> {
    List<SeatEntity> findSeatByFlight(FlightEntity flightEntity);
}
