package com.sg.kata.lawnmower.domain;

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
        switch (command) {
            case D:
                return Direction.values()[Math.floorMod(ordinal() + 1, Direction.values().length)];
            case G:
                return Direction.values()[Math.floorMod(ordinal() - 1, Direction.values().length)];
            default:
                return this;
        }
    }


}
