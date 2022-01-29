package com.example.recruitmentanagementsystem.repository;

import com.example.recruitmentanagementsystem.domain.model.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResult, Long> {
    List<ExamResult> findAllByCandidateId(Long id);
}
