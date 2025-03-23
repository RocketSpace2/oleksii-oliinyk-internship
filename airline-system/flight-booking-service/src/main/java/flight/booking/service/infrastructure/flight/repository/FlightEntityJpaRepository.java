package flight.booking.service.infrastructure.flight.repository;

import flight.booking.service.infrastructure.flight.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FlightEntityJpaRepository extends JpaRepository<FlightEntity, Long>,
                                                   JpaSpecificationExecutor<FlightEntity> {
}
