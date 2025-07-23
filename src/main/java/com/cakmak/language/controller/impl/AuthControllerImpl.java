package com.cakmak.language.controller.impl;

import com.cakmak.language.model.dto.auth.AuthRequest;
import com.cakmak.language.model.dto.auth.AuthRequestDto;
import com.cakmak.language.model.dto.auth.AuthResponse;
import com.cakmak.language.model.dto.auth.AuthResponseDto;
import com.cakmak.language.model.dto.user.UserDto;
import com.cakmak.language.model.dto.user.UserRequest;
import com.cakmak.language.model.dto.user.UserResponse;
import com.cakmak.language.model.mapper.AuthMapper;
import com.cakmak.language.model.mapper.UserMapper;
import com.cakmak.language.service.AuthService;
import com.cakmak.language.controller.AuthController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthControllerImpl implements AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthControllerImpl.class);
    private final AuthMapper authMapper = AuthMapper.INSTANCE;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final AuthService authService;

    public AuthControllerImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<AuthResponse> signIn(AuthRequest request){
        AuthRequestDto requestDto = authMapper.mapRequestToRequestDto(request);
        AuthResponseDto responseDto = authService.login(requestDto);
        AuthResponse response = authMapper.mapResposeDtoToResponse(responseDto);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<UserResponse> signUp(UserRequest request) {
        logger.info("signUp service request: " + request);
        UserDto requestDto = userMapper.mapRequestToDto(request);
        UserDto responseDto = authService.register(requestDto);
        UserResponse response = userMapper.mapDtoToResponse(responseDto);
        logger.info("signUp service response: " + response);
        return ResponseEntity.ok(response);
    }

}
