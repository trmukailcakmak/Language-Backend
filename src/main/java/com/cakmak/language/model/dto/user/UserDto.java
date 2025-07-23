package com.cakmak.language.model.dto.user;

import com.cakmak.language.model.dto.base.AbstractDto;
import com.cakmak.language.model.dto.role.RoleResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class UserDto extends AbstractDto {

    private String name;

    private String surname;

    private String profession;

    private String country;

    private String city;

    private String district;

    private String email;

    private String phone;

    private String password;

    private List<RoleResponseDto> roles;
}
