package com.mowitnow.kata.lawnmower.domain;

import lombok.Getter;

@Getter
public enum Direction {
    /**
     *
     */
    N("North"),
    /**
     *
     */
    E("East"),
    /**
     *
     */
    S("South"),
    /**
     *
     */
    W("West");

    private final String longName;

    Direction(String direction) {
        this.longName = direction;
    }

    public Direction rotate(Command command) {
        return switch (command) {
            case D -> Direction.values()[Math.floorMod(ordinal() + 1, Direction.values().length)];
            case G -> Direction.values()[Math.floorMod(ordinal() - 1, Direction.values().length)];
            default -> this;
        };
    }


}
