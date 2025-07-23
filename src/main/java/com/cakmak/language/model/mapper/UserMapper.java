package com.cakmak.language.model.mapper;

import com.cakmak.language.model.dto.user.UserDto;
import com.cakmak.language.model.dto.user.UserRequest;
import com.cakmak.language.model.dto.user.UserResponse;
import com.cakmak.language.config.BaseMapperConfig;
import com.cakmak.language.model.dto.role.RoleResponseDto;
import com.cakmak.language.model.entity.Role;
import com.cakmak.language.model.entity.Users;
import com.cakmak.language.model.mapper.base.AbstractMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(config = BaseMapperConfig.class)
public abstract class UserMapper extends AbstractMapper<UserRequest, UserDto, Users, UserResponse> {

    public static final UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);

    @Override
    @Named(value = "requestToDto")
    public abstract UserDto mapRequestToDto(UserRequest request);

    @Override
    @Named(value = "dtoToEntity")
    public abstract Users mapDtoToEntity(UserDto dto);
    @Override
    @Named(value = "entityToDto")
    public abstract UserDto mapEntityToDto(Users entity);
    protected List<RoleResponseDto> mapRoles(List<Role> roles) {
        return roles.stream()
                .map(role -> new RoleResponseDto(role.getName().toString()))
                .collect(Collectors.toList());
    }

    @Override
    @Named(value = "dtoToResponse")
    public abstract UserResponse mapDtoToResponse(UserDto dto);
}
