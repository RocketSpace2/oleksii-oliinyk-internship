package user.service.domain.model;

import java.util.Objects;

public record User(Long id, String password, String email, String firstName, String lastName, Role role) {
    public User{
        Objects.requireNonNull(password);
        Objects.requireNonNull(email);
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
    }
}
