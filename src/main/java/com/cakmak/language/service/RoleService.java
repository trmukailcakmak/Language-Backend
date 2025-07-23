package com.cakmak.language.service;


import com.cakmak.language.model.type.RoleType;
import com.cakmak.language.model.entity.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(RoleType role);
}
