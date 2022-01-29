package com.example.recruitmentanagementsystem.api;

import com.example.recruitmentanagementsystem.domain.dto.AnnouncementView;
import com.example.recruitmentanagementsystem.domain.dto.CreateAnnouncementRequest;
import com.example.recruitmentanagementsystem.service.AnnouncementService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/announcements")
@RequiredArgsConstructor
public class AnnouncementApi {
    private final AnnouncementService announcementService;

    @GetMapping("/{id}")
    public ResponseEntity<AnnouncementView> get(@PathVariable Long id) {
        return ResponseEntity.ok(announcementService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<AnnouncementView>> getAll() {
        return ResponseEntity.ok(announcementService.getAll());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('RECRUITER')")
    public ResponseEntity<Void> create(@RequestBody CreateAnnouncementRequest request) {
        announcementService.create(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('RECRUITER')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        announcementService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
