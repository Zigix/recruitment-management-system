package com.example.recruitmentanagementsystem.service;

import com.example.recruitmentanagementsystem.domain.dto.AddExamResultRequest;
import com.example.recruitmentanagementsystem.domain.dto.CoursePaymentView;
import com.example.recruitmentanagementsystem.domain.dto.ExamResultView;
import com.example.recruitmentanagementsystem.domain.dto.UpdateCandidateRequest;
import com.example.recruitmentanagementsystem.domain.exception.CandidateNotFoundException;
import com.example.recruitmentanagementsystem.domain.mapper.CandidateMapper;
import com.example.recruitmentanagementsystem.domain.mapper.ExamResultMapper;
import com.example.recruitmentanagementsystem.domain.model.*;
import com.example.recruitmentanagementsystem.repository.CandidateRepository;
import com.example.recruitmentanagementsystem.repository.CoursePaymentRepository;
import com.example.recruitmentanagementsystem.repository.ExamResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateService {
    private final CandidateRepository candidateRepository;
    private final AuthService authService;
    private final CandidateMapper candidateMapper;
    private final ExamResultRepository examResultRepository;
    private final ExamResultMapper examResultMapper;
    private final CoursePaymentRepository coursePaymentRepository;

    @Transactional
    public void update(UpdateCandidateRequest request) {
        Candidate candidate = candidateRepository.findByUserId(authService.getLoggedUser().getId())
                .orElseThrow(() ->
                        new CandidateNotFoundException("Candidate with id " +
                                authService.getLoggedUser().getId() +
                                " not found"));
        if (!candidate.getUser().getId().equals(authService.getLoggedUser().getId())) {
            throw new IllegalStateException("You have no permission");
        }
        candidateMapper.update(request, candidate);
        candidateRepository.save(candidate);
    }

    @Transactional
    public void addExamResult(AddExamResultRequest request) {
        Candidate candidate = candidateRepository.findByUserId(authService.getLoggedUser().getId())
                .orElseThrow(() ->
                        new CandidateNotFoundException("Candidate with id " +
                                authService.getLoggedUser().getId() +
                                " not found"));
        ExamResult examResult = new ExamResult();
        examResult.setSubject(request.getSubject());
        examResult.setExtended(request.isExtended());
        examResult.setPercentResult(request.getPercentResult());
        examResult.setCandidate(candidate);
        examResultRepository.save(examResult);
    }

    @Transactional
    public List<ExamResultView> getAllByCandidate(Long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() ->
                        new CandidateNotFoundException("Candidate with id " + id + " not found"));
        User user = authService.getLoggedUser();
        if (!candidate.getUser().getId().equals(user.getId()) && user.getRole().equals(Role.CANDIDATE)) {
            throw new IllegalStateException("You hava no permission");
        }
        return examResultRepository.findAllByCandidateId(id)
                .stream()
                .map(examResultMapper::toExamResultView)
                .toList();
    }

    @Transactional
    public List<CoursePaymentView> getCourses() {
        Candidate candidate = candidateRepository.findByUserId(authService.getLoggedUser().getId())
                .orElseThrow(() -> new CandidateNotFoundException("Candidate not found"));
        return coursePaymentRepository.findAllByCandidateId(candidate.getId())
                .stream()
                .map(coursePayment -> {
                    CoursePaymentView coursePaymentView = new CoursePaymentView();
                    coursePaymentView.setName(coursePayment.getDegreeCourse().getName());
                    coursePaymentView.setDepartment(coursePayment.getDegreeCourse().getDepartment());
                    coursePaymentView.setId(coursePayment.getPayment().getId());
                    coursePaymentView.setAccountNumber(coursePayment.getPayment().getAccountNumber());
                    coursePaymentView.setAmount(coursePayment.getPayment().getAmount());
                    return coursePaymentView;
                }).toList();
    }
}
