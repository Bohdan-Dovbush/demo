package com.example.demo.entity.enums;

import lombok.*;

@Getter
@AllArgsConstructor
public enum Language {
    UKRAINE("Ukraine"), ENGLISH("English"), RUSSIAN("Russian");

    public final String name;
}
