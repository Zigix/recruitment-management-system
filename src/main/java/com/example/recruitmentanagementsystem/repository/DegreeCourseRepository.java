package com.example.recruitmentanagementsystem.repository;

import com.example.recruitmentanagementsystem.domain.model.DegreeCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DegreeCourseRepository extends JpaRepository<DegreeCourse, Long> {
}
