package com.example.recruitmentanagementsystem.service;

import com.example.recruitmentanagementsystem.domain.dto.UpdateCandidateRequest;
import com.example.recruitmentanagementsystem.domain.exception.CandidateNotFoundException;
import com.example.recruitmentanagementsystem.domain.mapper.CandidateMapper;
import com.example.recruitmentanagementsystem.domain.model.Candidate;
import com.example.recruitmentanagementsystem.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CandidateService {
    private final CandidateRepository candidateRepository;
    private final AuthService authService;
    private final CandidateMapper candidateMapper;

    @Transactional
    public void update(Long id, UpdateCandidateRequest request) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException("Candidate with id " + id + " not found"));
        if (!candidate.getUser().getId().equals(authService.getLoggedUser().getId())) {
            throw new IllegalStateException("You have no permission");
        }
        candidateMapper.update(request, candidate);
        candidateRepository.save(candidate);
    }

    /*private Candidate toCandidate(Candidate candidate, UpdateCandidateRequest request) {
        if (request.getFirstName() != null) {
            candidate.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            candidate.setLastName(request.getLastName());
        }
        if (request.getCertificateNumber() != null) {
            candidate.setCertificateNumber(request.getCertificateNumber());
        }
        if (request.getAddress() != null) {
            if (request.getAddress().getStreet() != null) {
                candidate.getAddress().setStreet(request.getAddress().getStreet());
            }
            if (request.getAddress().getHomeNumber() != null) {
                candidate.getAddress().setHomeNumber(request.getAddress().getHomeNumber());
            }
            if (request.getAddress().getCity() != null) {
                candidate.getAddress().setCity(request.getAddress().getCity());
            }
            if (request.getAddress().getPostalCode() != null) {
                candidate.getAddress().setPostalCode(request.getAddress().getPostalCode());
            }
        }
        return candidate;
    }*/
}
