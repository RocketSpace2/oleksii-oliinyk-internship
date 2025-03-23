package user.service.domain.ports.output;

import user.service.domain.model.User;
import user.service.infrastructure.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {
    Optional<UserEntity> findUserForRegistration(String email);
    User findUser(String email);
    User createUser(User user);
}
