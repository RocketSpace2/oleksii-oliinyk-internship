package user.service.domain.ports.input;


import user.service.domain.model.User;

public interface AuthPort {
    User registerUser(User user);
    void verifyUserForRegistration(String email);
    User findUserToLogin(String email);
}
