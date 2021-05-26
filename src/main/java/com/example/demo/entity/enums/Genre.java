package com.example.demo.entity.enums;

import lombok.*;

@Getter
@AllArgsConstructor
public enum Genre {
    HORROR("Horror"), ACTION("Action"), COMEDY("Comedy"),
    THRILLER("Thriller"), ROMANCE("Romance"), CRIME("Crime"),
    FANTASY("Fantasy"), BIOPIC("Biopic"), WESTERN("Western"),
    LOVESTORY("Love story"), DRAMA("Drama"), ANIMATION("Animation");

    public final String name;
}
