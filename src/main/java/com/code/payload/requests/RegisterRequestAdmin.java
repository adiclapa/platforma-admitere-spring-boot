package com.code.payload.requests;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegisterRequestAdmin {
    private final String email;
    private final String nume;
    private final String prenume;
    private final Integer rol;
}
