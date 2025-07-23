package com.cakmak.language.model.dto.user;

import com.cakmak.language.model.dto.base.AbstractResponse;
import com.cakmak.language.model.dto.role.RoleResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class UserResponse extends AbstractResponse {

    private String name;

    private String surname;

    private String profession;

    private String country;

    private String city;

    private String district;

    private String email;

    private String phone;

    private List<RoleResponseDto> roles;
}
