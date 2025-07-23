package com.cakmak.language.service;


import com.cakmak.language.model.dto.auth.AuthRequestDto;
import com.cakmak.language.model.dto.auth.AuthResponseDto;
import com.cakmak.language.model.dto.user.UserDto;

public interface AuthService {
    AuthResponseDto login(AuthRequestDto authRequestDto);
    UserDto register(UserDto userRequestDto);
}
