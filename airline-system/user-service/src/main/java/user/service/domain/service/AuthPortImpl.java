package user.service.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import user.service.domain.model.User;
import user.service.domain.ports.input.AuthPort;
import user.service.infrastructure.adapter.RoleRepositoryAdapter;
import user.service.infrastructure.adapter.UserRepositoryAdapter;

@Service
public class AuthPortImpl implements AuthPort {
    @Autowired
    private UserRepositoryAdapter userRepositoryAdapter;
    @Autowired
    private RoleRepositoryAdapter roleRepositoryAdapter;

    @Override
    public User registerUser(User user) {
        User userToCreate = new User(
                user.id(),
                user.password(),
                user.email(),
                user.firstName(),
                user.lastName(),
                roleRepositoryAdapter.findRoleById(1L)
        );

        return userRepositoryAdapter.createUser(userToCreate);
    }

    @Override
    public void verifyUserForRegistration(String email) {
        if (userRepositoryAdapter.findUserForRegistration(email).isPresent()) {
            throw new RuntimeException("User with such email already exists.");
        }
    }

    @Override
    public User findUserToLogin(String email) {
        return userRepositoryAdapter.findUser(email);
    }
}
