package user.service.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import user.service.application.dto.AuthResponse;
import user.service.application.dto.LoginRequest;
import user.service.application.dto.RegisterDTO;
import user.service.application.service.AuthApplicationService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthApplicationService authApplicationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {
        return ResponseEntity.ok(
                authApplicationService.registerUser(
                        registerDTO.getEmail(),
                        registerDTO.getPassword(),
                        registerDTO.getFirstName(),
                        registerDTO.getLastName()
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String token = authApplicationService.login(loginRequest);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
