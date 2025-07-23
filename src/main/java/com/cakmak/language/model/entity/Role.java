package com.cakmak.language.model.entity;

import com.cakmak.language.model.entity.base.AbstractEntity;
import com.cakmak.language.model.type.RoleType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    private RoleType name;
}
