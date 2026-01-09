package com.example.kiosk.service;

import com.example.kiosk.dto.SignupRequest;
import com.example.kiosk.entity.Member;
import com.example.kiosk.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignupRequest request) {
        if (memberRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        Member member = Member.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .role(com.example.kiosk.entity.Role.USER)
                .isApproved(0) // Default to Pending
                .build();
        memberRepository.save(member);
    }

    public Member getMember(String username) {
        return memberRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @org.springframework.transaction.annotation.Transactional
    public void updateMember(String username, com.example.kiosk.dto.MemberUpdateRequest request) {
        Member member = getMember(username);
        if (request.getPassword() != null && !request.getPassword().trim().isEmpty()) {
            member.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        if (request.getPhoneNumber() != null)
            member.setPhoneNumber(request.getPhoneNumber());
        if (request.getEmail() != null)
            member.setEmail(request.getEmail());
        if (request.getAddress() != null)
            member.setAddress(request.getAddress());
        memberRepository.save(member);
    }
}
