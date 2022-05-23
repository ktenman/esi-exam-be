package ee.tenman.exam.controller;

import ee.tenman.exam.domain.Role;
import ee.tenman.exam.domain.User;
import ee.tenman.exam.payload.request.LoginRequest;
import ee.tenman.exam.payload.request.SignupRequest;
import ee.tenman.exam.payload.response.JwtResponse;
import ee.tenman.exam.payload.response.MessageResponse;
import ee.tenman.exam.repository.RoleRepository;
import ee.tenman.exam.repository.UserRepository;
import ee.tenman.exam.security.jwt.JwtUtils;
import ee.tenman.exam.security.services.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private static final String ROLE_NOT_FOUND_MESSAGE = "Error: Role is not found.";
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        JwtResponse jwtResponse = JwtResponse.builder()
                .token(jwt)
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .roles(roles)
                .build();

        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.findByUsername(signUpRequest.getUsername()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        user.setPassword(encodedPassword);
        List<Role> rolesList = roleRepository.findAllByRoleTypeIn(signUpRequest.getRoles());
        if (rolesList.isEmpty()) {
            throw new RuntimeException("No roles found");
        }
        user.setRoles(Set.copyOf(rolesList));
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
