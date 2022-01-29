package com.example.recruitmentanagementsystem.service;

import com.example.recruitmentanagementsystem.domain.dto.ChangePasswordRequest;
import com.example.recruitmentanagementsystem.domain.dto.RecruiterView;
import com.example.recruitmentanagementsystem.domain.model.Role;
import com.example.recruitmentanagementsystem.domain.model.User;
import com.example.recruitmentanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void changePassword(ChangePasswordRequest request) {
        User user = userRepository.findByUsername(authService.getLoggedUser().getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new BadCredentialsException("Old password doesn't match");
        }
        if (!request.getNewPassword().equals(request.getReNewPassword())) {
            throw new BadCredentialsException("New password doesn't match");
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    @Transactional
    public List<RecruiterView> getAllRecruiters() {
        return userRepository.findAllByRole(Role.RECRUITER)
                .stream()
                .map(user -> {
                    RecruiterView recruiterView = new RecruiterView();
                    recruiterView.setId(user.getId());
                    recruiterView.setEmail(user.getEmail());
                    return recruiterView;
                }).toList();
    }
}
