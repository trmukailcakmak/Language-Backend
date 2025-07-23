package com.cakmak.language.service.impl;

import com.cakmak.language.model.type.RoleType;
import com.cakmak.language.exceptions.LanguageException;
import com.cakmak.language.model.entity.Role;
import com.cakmak.language.model.mapper.AuthMapper;
import com.cakmak.language.model.mapper.UserMapper;
import com.cakmak.language.repository.RoleRepository;
import com.cakmak.language.security.JWTGenerator;
import com.cakmak.language.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    private final AuthMapper authMapper= AuthMapper.INSTANCE;

    private final UserMapper userMapper= UserMapper.INSTANCE;

    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator jwtGenerator;
    private final MessageSource messageSource;


    private void throwException(String errCode) {
        String message = this.messageSource.getMessage(errCode, null, Locale.ENGLISH);
        throw new LanguageException(errCode, message);
    }

    @Override
    public Optional<Role> findByName(RoleType roleType) {
        return roleRepository.findByName(roleType);
    }
}
