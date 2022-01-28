package com.example.recruitmentanagementsystem.api;

import com.example.recruitmentanagementsystem.domain.dto.CreateUserRequest;
import com.example.recruitmentanagementsystem.service.AuthService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApi {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody @Valid CreateUserRequest request) {
        authService.signUp(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/verification")
    public ResponseEntity<String> verifyAccount(@RequestParam("token") String token) {
        authService.verifyAccount(token);
        return ResponseEntity.ok("Konto aktywowane");
    }
}
