package com.example.recruitmentanagementsystem.domain.mapper;

import com.example.recruitmentanagementsystem.domain.dto.UpdateCandidateRequest;
import com.example.recruitmentanagementsystem.domain.model.Candidate;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface CandidateMapper {

    @BeanMapping(nullValueCheckStrategy = ALWAYS, nullValuePropertyMappingStrategy = IGNORE)
    void update(UpdateCandidateRequest request, @MappingTarget Candidate candidate);
}
