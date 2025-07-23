package com.cakmak.language.controller;

import com.cakmak.language.model.dto.auth.AuthRequest;
import com.cakmak.language.model.dto.auth.AuthResponse;
import com.cakmak.language.model.dto.user.UserRequest;
import com.cakmak.language.model.dto.user.UserResponse;
import com.cakmak.language.constant.EndPointConstant;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = EndPointConstant.AUTH_CONTROLLER, produces={MediaType.APPLICATION_JSON_VALUE})
public interface AuthController {
    @PostMapping(value = EndPointConstant.SIGN_IN)
    @ResponseBody ResponseEntity<AuthResponse> signIn(@RequestBody AuthRequest request);
    @PostMapping(value =EndPointConstant.SIGN_UP)
    ResponseEntity<UserResponse> signUp(@RequestBody UserRequest request);
}
