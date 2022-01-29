package com.example.recruitmentanagementsystem.api;

import com.example.recruitmentanagementsystem.domain.dto.AuthenticationResponse;
import com.example.recruitmentanagementsystem.domain.dto.CreateRecruiterRequest;
import com.example.recruitmentanagementsystem.domain.dto.CreateUserRequest;
import com.example.recruitmentanagementsystem.domain.dto.LoginRequest;
import com.example.recruitmentanagementsystem.domain.model.User;
import com.example.recruitmentanagementsystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApi {
    private final AuthService authService;

    @PostMapping("/sign-up-recruiter")
    public ResponseEntity<Void> signUpRecruiter(@RequestBody @Valid CreateRecruiterRequest request) {
        authService.signUpRecruiter(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

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

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
