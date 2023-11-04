package com.theoss.jwtbackend.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permissions {

    ADMIN_WORK("admin"),
    USER_WORK("user")

    ;

    @Getter
    private final String permission;
}
