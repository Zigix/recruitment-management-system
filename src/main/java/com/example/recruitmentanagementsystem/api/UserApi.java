package com.example.recruitmentanagementsystem.api;

import com.example.recruitmentanagementsystem.domain.dto.ChangePasswordRequest;
import com.example.recruitmentanagementsystem.domain.dto.RecruiterView;
import com.example.recruitmentanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApi {
    private final UserService userService;

    @GetMapping("/recruiters")
    @PreAuthorize("hasAuthority('CANDIDATE')")
    public ResponseEntity<List<RecruiterView>> getAllRecruiters() {
        return ResponseEntity.ok(userService.getAllRecruiters());
    }

    @PatchMapping
    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordRequest request) {
        userService.changePassword(request);
        return ResponseEntity.ok().build();
    }
}
