package com.restaurant.controller;


import com.restaurant.config.JwtProvider;
import com.restaurant.dto.AuthResponse;
import com.restaurant.dto.LoginRequest;
import com.restaurant.exception.BadCredentialsExceptions;
import com.restaurant.model.User;
import com.restaurant.repository.UserRepository;
import com.restaurant.service.user.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsServiceImpl userServiceImpl;

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {
        String email = user.getEmail();
        String password = user.getPassword();
        String fullNam = user.getUsername();
        String role = user.getRole();

        User isEmailExist = userRepo.findByEmail(email);
        if (isEmailExist != null) {
            throw new Exception("Email Already Exists");
        }

        //Create New User
        user.setPassword(passwordEncoder.encode(password));
        User savedUser = userRepo.save(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = JwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Register Successfully");
        authResponse.setStatus(true);
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);

    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthResponse> signIn(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        log.info("username {} and password {}", username, password);

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = JwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();

        authResponse.setMessage("Login Success");
        authResponse.setJwt(token);
        authResponse.setStatus(true);
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = userServiceImpl.loadUserByUsername(username);
        log.info("Login userDetails {}", userDetails);
        if (userDetails == null) {
            log.info("Email Not valid");
            throw new BadCredentialsExceptions("Invalid Username or Password");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            log.info("Password Not valid");
            throw new BadCredentialsExceptions("Invalid username or password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
