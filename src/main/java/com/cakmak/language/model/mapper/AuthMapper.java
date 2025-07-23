package com.cakmak.language.model.mapper;

import com.cakmak.language.config.BaseMapperConfig;
import com.cakmak.language.model.dto.auth.AuthRequest;
import com.cakmak.language.model.dto.auth.AuthRequestDto;
import com.cakmak.language.model.dto.auth.AuthResponse;
import com.cakmak.language.model.dto.auth.AuthResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = BaseMapperConfig.class)
public abstract class AuthMapper {

    public static final AuthMapper INSTANCE= Mappers.getMapper(AuthMapper.class);
    public abstract AuthRequestDto mapRequestToRequestDto(AuthRequest authRequest);
    public abstract AuthResponse mapResposeDtoToResponse(AuthResponseDto responseDto);
}
