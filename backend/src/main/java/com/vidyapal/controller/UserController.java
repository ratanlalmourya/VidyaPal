package com.vidyapal.controller;

import com.vidyapal.dto.ErrorResponse;
import com.vidyapal.dto.LoginRequest;
import com.vidyapal.dto.RegisterRequest;
import com.vidyapal.model.User;
import com.vidyapal.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest request) {
        if (userService.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorResponse(HttpStatus.CONFLICT.value(),
                            "A user with this email already exists"));
        }
        User created = userService.registerUser(request);
        return ResponseEntity.created(URI.create("/api/users/" + created.getId())).body(created);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        return userService.findByEmail(request.getEmail())
                .filter(user -> user.getPassword().equals(request.getPassword()))
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), "Invalid credentials")));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfile(@PathVariable Long id) {
        return userService.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                                "User not found with id " + id)));
    }
}
