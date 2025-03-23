package user.service.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import user.service.domain.model.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users") // or whatever the actual table name is
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity roleEntity;

    public UserEntity fromDomain(User user) {
        return new UserEntity(
                user.id(),
                user.password(),
                user.email(),
                user.firstName(),
                user.lastName(),
                RoleEntity.fromDomain(user.role())
        );
    }

    public User toDomain() {
        return new User(
                this.getId(),
                this.getPassword(),
                this.getEmail(),
                this.getFirstName(),
                this.getLastName(),
                this.getRoleEntity().toDomain()
        );
    }
}
