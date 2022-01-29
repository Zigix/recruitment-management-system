package com.example.recruitmentanagementsystem.api;

import com.example.recruitmentanagementsystem.domain.model.DegreeCourse;
import com.example.recruitmentanagementsystem.service.DegreeCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/degree-courses")
@RequiredArgsConstructor
public class DegreeCourseApi {
    private final DegreeCourseService degreeCourseService;

    @GetMapping
    public ResponseEntity<List<DegreeCourse>> getAll() {
        return ResponseEntity.ok(degreeCourseService.getAll());
    }

    @PostMapping("/register/{id}")
    @PreAuthorize("hasAuthority('CANDIDATE')")
    public ResponseEntity<Void> register(@PathVariable Long id) {
        degreeCourseService.register(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
