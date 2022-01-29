package com.example.recruitmentanagementsystem.service;

import com.example.recruitmentanagementsystem.domain.exception.CandidateNotFoundException;
import com.example.recruitmentanagementsystem.domain.exception.DegreeCourseNotFoundException;
import com.example.recruitmentanagementsystem.domain.model.Candidate;
import com.example.recruitmentanagementsystem.domain.model.CoursePayment;
import com.example.recruitmentanagementsystem.domain.model.DegreeCourse;
import com.example.recruitmentanagementsystem.domain.model.Payment;
import com.example.recruitmentanagementsystem.repository.CandidateRepository;
import com.example.recruitmentanagementsystem.repository.CoursePaymentRepository;
import com.example.recruitmentanagementsystem.repository.DegreeCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DegreeCourseService {
    private final DegreeCourseRepository degreeCourseRepository;
    private final CoursePaymentRepository coursePaymentRepository;
    private final CandidateRepository candidateRepository;
    private final AuthService authService;

    @Transactional(readOnly = true)
    public List<DegreeCourse> getAll() {
        return degreeCourseRepository.findAll();
    }

    @Transactional
    public void register(Long id) {
        DegreeCourse degreeCourse = degreeCourseRepository.findById(id)
                .orElseThrow(() -> new DegreeCourseNotFoundException("Degree course with id " + id + " not found"));
        Candidate candidate = candidateRepository.findByUserId(authService.getLoggedUser().getId())
                .orElseThrow(() -> new CandidateNotFoundException("Candidate not found"));
        Payment payment = new Payment();
        payment.setAccountNumber("1234567890987656");
        payment.setAmount(25.0);
        payment.setPaid(false);

        CoursePayment coursePayment = new CoursePayment();
        coursePayment.setDegreeCourse(degreeCourse);
        coursePayment.setPayment(payment);
        coursePayment.setCandidate(candidate);
        coursePaymentRepository.save(coursePayment);
    }
}
