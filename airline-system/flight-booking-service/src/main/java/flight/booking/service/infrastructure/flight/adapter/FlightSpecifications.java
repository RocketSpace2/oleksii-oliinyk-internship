package flight.booking.service.infrastructure.flight.adapter;


import flight.booking.service.infrastructure.flight.entity.FlightEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightSpecifications {

    public static Specification<FlightEntity> matchesCriteria(String from, String to, LocalDateTime departureTime) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (from != null) {
                predicates.add(cb.equal(root.get("fromLocation"), from));
            }
            if (to != null) {
                predicates.add(cb.equal(root.get("toLocation"), to));
            }
            if (departureTime != null) {
                predicates.add(cb.equal(root.get("departureTime"), departureTime));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
