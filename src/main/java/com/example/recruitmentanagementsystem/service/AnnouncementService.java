package com.example.recruitmentanagementsystem.service;

import com.example.recruitmentanagementsystem.domain.dto.AnnouncementView;
import com.example.recruitmentanagementsystem.domain.dto.CreateAnnouncementRequest;
import com.example.recruitmentanagementsystem.domain.exception.AnnouncementNotFoundException;
import com.example.recruitmentanagementsystem.domain.mapper.AnnouncementMapper;
import com.example.recruitmentanagementsystem.domain.model.Announcement;
import com.example.recruitmentanagementsystem.repository.AnnouncementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementService {
    private final AuthService authService;
    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;

    @Transactional(readOnly = true)
    public AnnouncementView get(Long id) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() ->
                        new AnnouncementNotFoundException("Announcement with id " + id + " not found"));
        return announcementMapper.toAnnouncementView(announcement);
    }

    @Transactional
    public void create(CreateAnnouncementRequest request) {
        Announcement announcement = announcementMapper.toAnnouncement(request, authService.getLoggedUser());
        announcementRepository.save(announcement);
    }

    @Transactional
    public void delete(Long id) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() ->
                        new AnnouncementNotFoundException("Announcement with id " + id + " not found"));
        if (!announcement.getUser().getUsername().equals(authService.getLoggedUser().getUsername())) {
            throw new IllegalStateException("You have no permission");
        }
        announcementRepository.delete(announcement);
    }

    @Transactional(readOnly = true)
    public List<AnnouncementView> getAll() {
        return announcementRepository.findAll()
                .stream()
                .map(announcementMapper::toAnnouncementView)
                .toList();
    }
}
