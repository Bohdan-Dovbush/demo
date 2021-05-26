package com.example.demo.entity.enums;

import lombok.*;

@Getter
@AllArgsConstructor
public enum Gender {
    FEMALE("Female"), MALE("Male");

    public final String name;
}
