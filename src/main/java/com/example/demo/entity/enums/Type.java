package com.example.demo.entity.enums;

import lombok.*;

@Getter
@AllArgsConstructor
public enum Type {
    TWO("2D"), TREE("3D"), FOUR("4DX");

    public final String name;
}
