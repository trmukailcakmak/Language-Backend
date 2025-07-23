package com.cakmak.language.model.dto.auth;

import lombok.Data;

@Data
public class AuthRequest {

    private String username;

    private String password;
}
