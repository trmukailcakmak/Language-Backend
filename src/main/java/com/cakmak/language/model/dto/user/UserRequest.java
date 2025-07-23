package com.cakmak.language.model.dto.user;

import com.cakmak.language.model.dto.base.AbstractRequest;
import lombok.Data;

@Data
public class UserRequest extends AbstractRequest {

    private String name;

    private String surname;

    private String profession;

    private String country;

    private String city;

    private String district;

    private String email;

    private String phone;

    private String password;
}
