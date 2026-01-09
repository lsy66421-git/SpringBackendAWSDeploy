package com.example.kiosk.dto;

import com.example.kiosk.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
    private String username;
    private String password;
    private String name;
    private Role role; // USER, OWNER, ADMIN
}
