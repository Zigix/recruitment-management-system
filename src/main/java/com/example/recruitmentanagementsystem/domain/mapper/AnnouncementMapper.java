package com.example.recruitmentanagementsystem.domain.mapper;

import com.example.recruitmentanagementsystem.domain.dto.AnnouncementView;
import com.example.recruitmentanagementsystem.domain.dto.CreateAnnouncementRequest;
import com.example.recruitmentanagementsystem.domain.model.Announcement;
import com.example.recruitmentanagementsystem.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnnouncementMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "title", source = "request.title")
    @Mapping(target = "priority", expression = "java(request.getPriority())")
    @Mapping(target = "text", source = "request.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "user", source = "user")
    Announcement toAnnouncement(CreateAnnouncementRequest request, User user);

    @Mapping(target = "priority", expression = "java(announcement.getPriority().name())")
    @Mapping(target = "creator", expression = "java(announcement.getUser().getUsername())")
    AnnouncementView toAnnouncementView(Announcement announcement);
}
