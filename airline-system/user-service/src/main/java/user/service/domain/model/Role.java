package user.service.domain.model;

import java.util.Objects;

public record Role(Long id, String roleName) {
    public Role{
        Objects.requireNonNull(id);
        Objects.requireNonNull(roleName);
    }
}
