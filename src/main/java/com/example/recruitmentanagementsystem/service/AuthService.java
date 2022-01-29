package com.example.recruitmentanagementsystem.service;

import com.example.recruitmentanagementsystem.configuration.JwtTokenUtil;
import com.example.recruitmentanagementsystem.domain.dto.CreateRecruiterRequest;
import com.example.recruitmentanagementsystem.domain.dto.CreateUserRequest;
import com.example.recruitmentanagementsystem.domain.dto.LoginRequest;
import com.example.recruitmentanagementsystem.domain.exception.TokenNotFoundException;
import com.example.recruitmentanagementsystem.domain.model.*;
import com.example.recruitmentanagementsystem.repository.CandidateRepository;
import com.example.recruitmentanagementsystem.repository.UserRepository;
import com.example.recruitmentanagementsystem.repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    public static final String TOKEN_BASE_PATH = "http://localhost:8080/api/auth/verification?token=";
    public static final String BASE_SYSTEM_EMAIL_ADDRESS = "erekrutacja@example.com";

    private final UserRepository userRepository;
    private final CandidateRepository candidateRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final AuthenticationManager authenticationManager;
    private final MailService mailService;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(CreateUserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(generateUsername());
        user.setPassword(generatePassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(Role.CANDIDATE);
        user.setCreatedDate(Instant.now());
        user.setEnabled(false);
        user = userRepository.save(user);

        Candidate candidate = new Candidate();
        candidate.setPesel(request.getPesel());
        candidate.setUser(user);
        candidateRepository.save(candidate);

        String token = generateToken(user);
        String link = TOKEN_BASE_PATH + token;
        mailService.sendMail(getActivationEmail(user.getEmail(), user.getFirstName(), user.getLastName(), link));
    }

    private String generateToken(User user) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return verificationToken.getToken();
    }

    private String generateUsername() {
        String username = null;
        do {
            username = String.valueOf(new Random().nextInt(100000, 999999));
        } while (userRepository.existsByUsername(username));
        return username;
    }

    private String generatePassword() {
        return UUID.randomUUID().toString();
    }

    private NotificationEmail getActivationEmail(String recipient, String firstName, String lastName, String link) {
        String subject = "Aktywuj konto w systemie rekrutacyjnym";
        String text = "Witaj " + firstName + " " + lastName + ",\n\n" +
                "Zalozyles konto w systemie rekrutacyjnym PK, zeby je aktywowac kliknij ponizszy link:\n" +
                link;
        return new NotificationEmail(
                BASE_SYSTEM_EMAIL_ADDRESS,
                recipient,
                subject,
                text);
    }

    @Transactional
    public void verifyAccount(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token)
                .orElseThrow(() -> new TokenNotFoundException("Invalid token"));
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with name - " + username));
        user.setEnabled(true);
        mailService.sendMail(getCredentialsEmail(user));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    private NotificationEmail getCredentialsEmail(User user) {
        String subject = "Konto Aktywowane";
        String text = "Twoje konto w systemie rekrutacyjnym PK zostalo aktywowane. " +
                "Wygenerowano dla Ciebie nazwe uzytkownika oraz haslo poprzez ktore uzyskasz dostep do swojego konta:\n" +
                "Nazwa uzytkownika: " + user.getUsername() + "\n" +
                "Haslo: " + user.getPassword() + "\n" +
                "Zmien swoje haslo przy pierwszym logowaniu do systemu.";
        return new NotificationEmail(
                BASE_SYSTEM_EMAIL_ADDRESS,
                user.getEmail(),
                subject,
                text);
    }

    @Transactional(readOnly = true)
    public User getLoggedUser() {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    @Transactional
    public Map<String, User> login(LoginRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = getLoggedUser();
        return Map.of(jwtTokenUtil.generate(authentication), getLoggedUser());
    }

    public void signUpRecruiter(CreateRecruiterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreatedDate(Instant.now());
        user.setRole(Role.RECRUITER);
        user.setEnabled(true);
        userRepository.save(user);
    }
}
