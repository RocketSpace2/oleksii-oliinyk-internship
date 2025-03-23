package user.service.infrastructure.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import user.service.domain.model.Role;
import user.service.domain.ports.output.RoleRepository;
import user.service.infrastructure.entity.RoleEntity;
import user.service.infrastructure.repository.RoleEntityJpaRepository;

@Component
public class RoleRepositoryAdapter implements RoleRepository {
    @Autowired
    private RoleEntityJpaRepository roleEntityJpaRepository;
    @Override
    public Role findRoleById(Long id) {
        RoleEntity roleEntity = roleEntityJpaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Role doesn't exists.")
        );
        return roleEntity.toDomain();
    }
}
