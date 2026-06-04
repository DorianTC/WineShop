package com.wineshop.demo.web.controller;
import com.wineshop.demo.business.service.UserService;
import com.wineshop.demo.web.dto.MessageResponse;
import com.wineshop.demo.web.dto.UserDTO;
import com.wineshop.demo.web.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor


public class UserController {
    private final UserService userService;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.delete(id));
    }

    @PatchMapping("/update")
    public ResponseEntity<UserResponse> update(
            @RequestBody UserDTO dto) {
        UserResponse userResponse = userService.update(dto);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }
}
