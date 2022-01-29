package com.example.recruitmentanagementsystem.api;

import com.example.recruitmentanagementsystem.domain.dto.AddExamResultRequest;
import com.example.recruitmentanagementsystem.domain.dto.CoursePaymentView;
import com.example.recruitmentanagementsystem.domain.dto.ExamResultView;
import com.example.recruitmentanagementsystem.domain.dto.UpdateCandidateRequest;
import com.example.recruitmentanagementsystem.domain.model.CoursePayment;
import com.example.recruitmentanagementsystem.domain.model.ExamResult;
import com.example.recruitmentanagementsystem.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateApi {
    private final CandidateService candidateService;

    @PatchMapping("/data/{id}")
    @PreAuthorize("hasAuthority('CANDIDATE')")
    public ResponseEntity<Void> update(@RequestBody UpdateCandidateRequest request) {
        candidateService.update(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/exam-results")
    @PreAuthorize("hasAuthority('CANDIDATE')")
    public ResponseEntity<Void> addExamResult(@RequestBody @Valid AddExamResultRequest request) {
        candidateService.addExamResult(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}/exam-results")
    public ResponseEntity<List<ExamResultView>> getAllByCandidate(@PathVariable Long id) {
        return ResponseEntity.ok(candidateService.getAllByCandidate(id));
    }

    @GetMapping("/courses")
    public ResponseEntity<List<CoursePaymentView>> getCourses() {
        return ResponseEntity.ok(candidateService.getCourses());
    }
}
