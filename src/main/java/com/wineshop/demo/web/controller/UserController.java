package com.wineshop.demo.web.controller;
import com.wineshop.demo.business.exception.messages.ExceptionMessages;
import com.wineshop.demo.business.service.UserService;
import com.wineshop.demo.web.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor


public class UserController {
    private final UserService userService;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        String message = userService.delete(id);

        if (message.equals(ExceptionMessages.USER_NOT_FOUND)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        return ResponseEntity.ok(message);
    }

    @PatchMapping("/update")
    public ResponseEntity<String> update(
            @RequestBody UserDTO dto) {

        String message = userService.update(dto);

        if (message.equals(ExceptionMessages.EMAIL_ADDRESS_NOT_FOUND)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        return ResponseEntity.ok(message);
    }
}
