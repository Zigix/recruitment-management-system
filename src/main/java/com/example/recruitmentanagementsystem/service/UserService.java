package com.example.recruitmentanagementsystem.service;

import com.example.recruitmentanagementsystem.domain.dto.ChangePasswordRequest;
import com.example.recruitmentanagementsystem.domain.model.User;
import com.example.recruitmentanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
