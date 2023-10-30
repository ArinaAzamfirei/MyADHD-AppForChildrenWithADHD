package my.adhd.MyADHD.api.auth;

import lombok.RequiredArgsConstructor;

import my.adhd.MyADHD.jwt.JwtService;
import my.adhd.MyADHD.models.Role;
import my.adhd.MyADHD.models.User;
import my.adhd.MyADHD.models.auth.AuthenticationRequest;
import my.adhd.MyADHD.models.auth.AuthenticationResponse;
import my.adhd.MyADHD.models.auth.RegisterRequest;
import my.adhd.MyADHD.api.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {


        var user = User.builder()
                .firstname(Base64.getEncoder().encodeToString(request.getFirstname().getBytes()))
                .lastname(Base64.getEncoder().encodeToString(request.getLastname().getBytes()))
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("Authorities", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));

        var jwtToken = jwtService.generateToken(extraClaims, user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(("User not found")));


        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("Authorities", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));

        var jwtToken = jwtService.generateToken(extraClaims, user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userId(user.getId())
                .build();
    }
}
