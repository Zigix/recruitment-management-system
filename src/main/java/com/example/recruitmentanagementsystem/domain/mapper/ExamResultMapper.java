package com.example.recruitmentanagementsystem.domain.mapper;

import com.example.recruitmentanagementsystem.domain.dto.ExamResultView;
import com.example.recruitmentanagementsystem.domain.model.ExamResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExamResultMapper {

    @Mapping(target = "candidateId", expression = "java(examResult.getCandidate().getId())")
    ExamResultView toExamResultView(ExamResult examResult);
}
