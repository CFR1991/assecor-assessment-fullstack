package org.codechallenge.backend.favouritecolours.model;

import lombok.Getter;

@Getter
public enum Colour {
    BLAU("1", "blau"),
    GRUEN("2", "grün"),
    VIOLETT("3", "violett"),
    ROT("4", "rot"),
    GELB("5", "gelb"),
    TUERKIS("6", "türkis"),
    WEISS("7", "weiß");

    private final String id;
    private final String name;

    Colour(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Colour fromName(String name) {
        for (Colour colour : values()) {
            if (colour.getName().equals(name)) {
                return colour;
            }
        }
        throw new IllegalArgumentException("Invalid colour name: " + name);
    }

    public static Colour fromId(String id) {
        for (Colour colour : values()) {
            if (colour.getId().equals(id)) {
                return colour;
            }
        }
        throw new IllegalArgumentException("Invalid colour id: " + id);
    }
}