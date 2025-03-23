package user.service.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import user.service.domain.model.Role;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "role_name", length = 20, nullable = false)
    private String roleName;

    public static RoleEntity fromDomain(Role role) {
        return new RoleEntity(
                role.id(),
                role.roleName()
        );
    }

    public Role toDomain() {
        return new Role(
                this.getId(),
                this.getRoleName()
        );
    }
}
