package user.service.domain.ports.output;

import user.service.domain.model.Role;
import user.service.infrastructure.entity.RoleEntity;

import java.util.Optional;

public interface RoleRepository {
    Role findRoleById(Long id);
}
