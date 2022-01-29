package com.example.recruitmentanagementsystem.repository;

import com.example.recruitmentanagementsystem.domain.model.CoursePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursePaymentRepository extends JpaRepository<CoursePayment, Long> {
    List<CoursePayment> findAllByCandidateId(Long id);
}
