package com.example.recruitmentanagementsystem.api;

import com.example.recruitmentanagementsystem.domain.dto.UpdateCandidateRequest;
import com.example.recruitmentanagementsystem.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateApi {
    private final CandidateService candidateService;

    @PatchMapping("/data/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody UpdateCandidateRequest request) {
        candidateService.update(id, request);
        return ResponseEntity.ok().build();
    }
}
