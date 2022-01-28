package com.example.recruitmentanagementsystem.repository;

import com.example.recruitmentanagementsystem.domain.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

}