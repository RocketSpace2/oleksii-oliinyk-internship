package user.service.application.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import user.service.application.dto.LoginRequest;
import user.service.application.dto.RegisterDTO;
import user.service.application.dto.UserDTO;
import user.service.domain.ports.input.AuthPort;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class AuthApplicationService {
    private final String SECRET_KEY = "3hvmah0D8Oj1kn8lK07V7vMdtzKkeXPDju2JxfGx1uOhYJ2q9nBPmFAy8LBJoiQ9MyGQjBCmK/UHxUIGXMVRPw==";

    @Autowired
    private AuthPort authPort;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public RegisterDTO registerUser(String email, String password, String firstName, String lastName) {
        UserDTO userDTO = new UserDTO(
                email,
                password,
                firstName,
                lastName
        );

        authPort.verifyUserForRegistration(userDTO.getEmail());

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDTO = UserDTO.fromDomain(authPort.registerUser(userDTO.toDomain()));

        return new RegisterDTO(
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getFirstName(),
                userDTO.getLastName()
        );
    }

    public String login(LoginRequest loginRequest) {
        UserDTO userDTO = UserDTO.fromDomain(
                authPort.findUserToLogin(
                        loginRequest.getEmail()
                )
        );

        if (!passwordEncoder.matches(loginRequest.getPassword(), userDTO.getPassword())) {
            throw new RuntimeException("Password is incorrect.");
        }

        return generateToken(userDTO);
    }

    private String generateToken(UserDTO userDTO) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + (1800000)); // 30 minutes

        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setSubject(String.valueOf(userDTO.getId()))// store user’s ID in token subject
                .claim("role", userDTO.getRoleName())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }
}
