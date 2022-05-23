package ee.tenman.exam.service;

import ee.tenman.exam.domain.Role;
import ee.tenman.exam.domain.User;
import ee.tenman.exam.exception.EmailAlreadyTakenException;
import ee.tenman.exam.exception.UsernameAlreadyTakenException;
import ee.tenman.exam.payload.request.LoginRequest;
import ee.tenman.exam.payload.request.SignupRequest;
import ee.tenman.exam.payload.response.JwtResponse;
import ee.tenman.exam.repository.UserRepository;
import ee.tenman.exam.security.jwt.TokenProvider;
import ee.tenman.exam.security.services.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public record AuthService(
        AuthenticationManagerBuilder authenticationManagerBuilder,
        UserRepository userRepository,
        RoleService roleService,
        PasswordEncoder passwordEncoder,
        TokenProvider tokenProvider
) {

    public JwtResponse authenticate(LoginRequest loginRequest) {
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication, true);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return JwtResponse.builder()
                .token(jwt)
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .roles(roles)
                .build();
    }

    public User register(SignupRequest signupRequest) {
        if (userRepository.findByUsername(signupRequest.getUsername()).isPresent()) {
            throw new UsernameAlreadyTakenException(String.format("Error: Username %s is already taken!",
                    signupRequest.getUsername()));
        }

        if (userRepository.findByEmail(signupRequest.getEmail()).isPresent()) {
            throw new EmailAlreadyTakenException(String.format("Error: Email %s is already in use!",
                    signupRequest.getEmail()));
        }

        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        String encodedPassword = passwordEncoder.encode(signupRequest.getPassword());
        user.setPassword(encodedPassword);
        Set<Role> roles = roleService.findAllRolesByRoleTypes(signupRequest.getRoles());
        user.setRoles(roles);

        return userRepository.save(user);
    }
}
