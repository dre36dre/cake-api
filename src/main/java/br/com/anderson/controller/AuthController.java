package br.com.anderson.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.anderson.dto.LoginRequest;
import br.com.anderson.entity.User;
import br.com.anderson.repository.UserRepository;
import br.com.anderson.security.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    
    
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    
    
    @PostMapping("/create")
    public ResponseEntity<?> createUser() {

        String encodedPassword = passwordEncoder.encode("123456");

        User user = new User();
        user.setEmail("admin@email.com");
        user.setPassword(encodedPassword);
        user.setRole("ADMIN");

        userRepository.save(user);

        return ResponseEntity.ok("User created");
    }


    
    public AuthController(AuthenticationManager authenticationManager,
            JwtService jwtService) {
    	this.authenticationManager = authenticationManager;
    	this.jwtService = jwtService;
		this.passwordEncoder = null;
		this.userRepository = null;
}
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        String token = jwtService.generateToken(request.getEmail());

        return ResponseEntity.ok(Map.of("token", token));
    }
}
