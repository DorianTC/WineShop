package com.wineshop.demo.web.controller;

import com.wineshop.demo.business.exception.UnauthorizedException;
import com.wineshop.demo.business.exception.UserAlreadyExistsException;
import com.wineshop.demo.business.service.UserService;
import com.wineshop.demo.web.dto.request.RegisterUserRequest;
import com.wineshop.demo.web.dto.response.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthController {
    private final UserService userService;
    @PostMapping
    public ResponseEntity<?> create(@RequestBody RegisterUserRequest request) {
        try {
            UserResponse response = userService.save(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);  // 201 + UserResponse

        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());  // 400 + mesaj
        }

    }

    @PostMapping ("/login")
    public ResponseEntity<?> login(@RequestBody RegisterUserRequest request) {
        try {
            UserResponse response =userService.login(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (UsernameNotFoundException | UnauthorizedException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
