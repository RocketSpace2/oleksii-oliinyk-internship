package flight.booking.service.infrastructure.booking.adapter;


import flight.booking.service.infrastructure.booking.entity.TicketEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BookingSpecifications {

    public static Specification<TicketEntity> matchesCriteria(Long flightId, Long userId) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (flightId != null) {
                predicates.add(cb.equal(root.get("flightEntity").get("id"), flightId));
            }
            predicates.add(cb.equal(root.get("userId"), userId));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
