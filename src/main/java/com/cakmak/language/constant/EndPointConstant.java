package com.cakmak.language.constant;

import org.springframework.stereotype.Component;

@Component
public class EndPointConstant {
    // abstract class
    public static final String CREATE = "/create";
    public static final String UPDATE = "/update";
    public static final String GET_ALL = "/get-all";

    public static final String GET_ALL_PAGEABLE = "/get-all-pageable";
    public static final String GET_BY_ID = "/get-by-id/{id}";
    public static final String DELETE_BY_ID = "/delete-by-id/{id}";

    public static final String AUTH_CONTROLLER = "/api/auth";

    // Auth
    public static final String SIGN_IN = "/sign-in";
    public static final String SIGN_UP = "/sign-up";









}
