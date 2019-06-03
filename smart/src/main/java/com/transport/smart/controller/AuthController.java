package com.transport.smart.controller;

import com.transport.smart.message.GenericMessage;
import com.transport.smart.message.LoginRequest;
import com.transport.smart.message.RegisterRequest;
import com.transport.smart.model.Role;
import com.transport.smart.model.User;
import com.transport.smart.repository.RouteRepository;
import com.transport.smart.repository.UserRepository;
import com.transport.smart.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user/")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    JwtTokenProvider jwtProvider;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        String jwt = jwtProvider.createToken(loginRequest.getEmail(), userRepository.findByEmail(loginRequest.getEmail()).getRoles());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if(authentication.isAuthenticated())
            return ResponseEntity.ok().body(new GenericMessage(jwt));
        return ResponseEntity.status(401).body(new GenericMessage("Bad credentials"));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest){
        if(userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new GenericMessage("Email already exists"));
        }
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setPassword(encoder.encode(registerRequest.getPassword()));
        user.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));
        userRepository.save(user);

        return ResponseEntity.ok().body(new GenericMessage("User registered successfully"));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable("email") String email){
        if(!userRepository.existsByEmail(email)){
            return ResponseEntity.badRequest().body(new GenericMessage("email not found"));
        }
        return ResponseEntity.ok().body(userRepository.findByEmail(email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") UUID id){
        if(!userRepository.existsById(id)){
            ResponseEntity.status(404).body(new GenericMessage("User not found"));
        }
        return ResponseEntity.ok().body(userRepository.findById(id));
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userRepository.findAll();

    }

    @GetMapping("/id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String adminAccess() {
        return ">>> Admin Contents";
    }
}
