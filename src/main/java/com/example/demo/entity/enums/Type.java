package com.example.demo.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Type {
    TWO("2D"), TREE("3D"), FOUR("4DX");

    private final String name;
}
