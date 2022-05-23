package ee.tenman.exam.controller;

import ee.tenman.exam.domain.User;
import ee.tenman.exam.payload.request.LoginRequest;
import ee.tenman.exam.payload.request.SignupRequest;
import ee.tenman.exam.payload.response.JwtResponse;
import ee.tenman.exam.repository.RoleRepository;
import ee.tenman.exam.repository.UserRepository;
import ee.tenman.exam.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.authenticate(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        User registeredUser = authService.register(signupRequest);
        return ResponseEntity.ok(registeredUser);
    }
}
