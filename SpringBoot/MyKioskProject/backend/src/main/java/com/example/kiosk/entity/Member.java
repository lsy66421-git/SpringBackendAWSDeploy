package com.example.kiosk.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username; // Login ID

    @Column(nullable = false)
    private String password;

    private String name;

    private String phoneNumber;
    private String email;
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role; // ADMIN, OWNER, USER

    private String backgroundImage; // Path to user-specific background

    @Column(columnDefinition = "integer default 0")
    private Integer isApproved; // 1: Approved, 0: Pending

    private LocalDateTime approvedAt;

    private LocalDateTime withdrawnAt;
}
