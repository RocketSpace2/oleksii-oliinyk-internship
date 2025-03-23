package user.service.infrastructure.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import user.service.domain.model.User;
import user.service.domain.ports.output.UserRepository;
import user.service.infrastructure.entity.UserEntity;
import user.service.infrastructure.repository.UserEntityJpaRepository;

import java.util.Optional;

@Component
public class UserRepositoryAdapter implements UserRepository {
    @Autowired
    private UserEntityJpaRepository userEntityJpaRepository;

    @Override
    public Optional<UserEntity> findUserForRegistration(String email) {
        return userEntityJpaRepository.findByEmail(email);
    }

    @Override
    public User findUser(String email) {
        UserEntity userEntity = userEntityJpaRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException(" User with such email was not found")
        );
        return userEntity.toDomain();
    }

    @Override
    public User createUser(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity = userEntityJpaRepository.save(userEntity.fromDomain(user));
        return userEntity.toDomain();
    }
}
