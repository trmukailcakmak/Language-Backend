package com.cakmak.language.service.impl;

import com.cakmak.language.constant.MessageKey;
import com.cakmak.language.exceptions.LanguageException;
import com.cakmak.language.model.dto.auth.AuthRequestDto;
import com.cakmak.language.model.dto.user.UserDto;
import com.cakmak.language.model.mapper.UserMapper;
import com.cakmak.language.model.type.RoleType;
import com.cakmak.language.service.AuthService;
import com.cakmak.language.service.RoleService;
import com.cakmak.language.util.StringUtil;
import com.cakmak.language.model.dto.auth.AuthResponseDto;
import com.cakmak.language.model.entity.Role;
import com.cakmak.language.model.entity.Users;
import com.cakmak.language.model.mapper.AuthMapper;
import com.cakmak.language.repository.UserRepository;
import com.cakmak.language.security.JWTGenerator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final AuthMapper authMapper= AuthMapper.INSTANCE;

    private final UserMapper userMapper= UserMapper.INSTANCE;

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator jwtGenerator;
    private final MessageSource messageSource;

    @Override
    public AuthResponseDto login(AuthRequestDto requestDto) {
        loginValidation(requestDto);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDto.getUsername(),
                        requestDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new AuthResponseDto(jwtGenerator.generateToken(authentication), requestDto.getUsername());
    }

    @Override
    public UserDto register(UserDto dto) {
        return savePerson(dto, RoleType.USER);
    }

    private UserDto savePerson(UserDto dto, RoleType roleType) {
        registerValidation(dto);

        Users user = userMapper.mapDtoToEntity(dto);
        user.setPassword(passwordEncoder.encode((dto.getPassword())));

        Optional<Role> roles = roleService.findByName(roleType);
        if (roles.isPresent()) {
            throwException(MessageKey.ERR07);
        }
        user.setRoles(Collections.singletonList(roles.get()));

        user = userRepository.save(user);

        return userMapper.mapEntityToDto(user);
    }
    private void loginValidation(AuthRequestDto dto) {
        if (!StringUtil.isNullOrEmpty(dto.getUsername()) || !StringUtil.isNullOrEmpty(dto.getPassword())) {
            throwException(MessageKey.ERR01);
        }
    }

    private void registerValidation(UserDto dto) {
        if (!StringUtil.isNullOrEmpty(dto.getEmail())) {
            throwException(MessageKey.ERR04);
        } else if (isEmailExist(dto.getEmail())) {
            throwException(MessageKey.ERR02);
        } else if (Objects.nonNull(dto.getPhone()) && isPhoneExist(dto)) {
            throwException(MessageKey.ERR03);
        }
    }

    private Boolean isPhoneExist(UserDto dto) {
        return userRepository.existsByPhone(dto.getPhone());
    }

    private Boolean isEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }

    private void throwException(String errCode) {
        String message = this.messageSource.getMessage(errCode, null, Locale.ENGLISH);
        throw new LanguageException(errCode, message);
    }
}
