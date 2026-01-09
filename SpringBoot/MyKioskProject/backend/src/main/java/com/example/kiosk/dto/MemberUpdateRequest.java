package com.example.kiosk.dto;

import lombok.Data;

@Data
public class MemberUpdateRequest {
    private String password;
    private String phoneNumber;
    private String email;
    private String address;
}
