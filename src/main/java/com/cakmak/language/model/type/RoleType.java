package com.cakmak.language.model.type;

public enum RoleType {
  USER("USER"),ADMIN("ADMIN");

  private String code;

  private RoleType(String code){
    this.code = code;
  }
}
