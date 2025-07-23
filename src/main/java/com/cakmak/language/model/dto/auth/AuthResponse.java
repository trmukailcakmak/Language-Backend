package com.cakmak.language.model.dto.auth;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String tokenType;
    private String email;
}
