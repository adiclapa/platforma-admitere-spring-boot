package com.code.utils;

public enum UserRolesEnum {
    CANDIDAT(1),
    CENTRU(2),
    SECRETAR(3),
    ADMIN(4),
    GENERIC(5);

    public final Integer value;

    private UserRolesEnum(Integer value) {
        this.value = value;
    }

}
