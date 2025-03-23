package user.service.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import user.service.infrastructure.entity.RoleEntity;

@Repository
public interface RoleEntityJpaRepository extends JpaRepository<RoleEntity, Long> {
}
