package com.example.kiosk.controller;

import com.example.kiosk.entity.Member;
import com.example.kiosk.dto.SignupRequest;
import com.example.kiosk.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest request) {
        authService.signup(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/me")
    public ResponseEntity<Member> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null)
            return ResponseEntity.status(401).build();
        return ResponseEntity.ok(authService.getMember(userDetails.getUsername()));
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateMember(@AuthenticationPrincipal UserDetails userDetails,
            @RequestBody com.example.kiosk.dto.MemberUpdateRequest request) {
        if (userDetails == null)
            return ResponseEntity.status(401).build();
        authService.updateMember(userDetails.getUsername(), request);
        return ResponseEntity.ok("Updated");
    }
}
