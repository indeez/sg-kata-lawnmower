package com.mowitnow.kata.lawnmower.domain;

import lombok.Getter;

import static java.lang.Math.floorMod;

@Getter
public enum Direction {
    /**
     *
     */
    NORTH('N'),
    /**
     *
     */
    EAST('E'),
    /**
     *
     */
    SOUTH('S'),
    /**
     *
     */
    WEST('W');

    private final char canonicalName;

    Direction(char canonicalName) {
        this.canonicalName = canonicalName;
    }

    public Direction rotate(Command command) {
        return switch (command) {
            case ROTATE_RIGHT -> Direction.values()[floorMod(ordinal() + 1, Direction.values().length)];
            case ROTATE_LEFT -> Direction.values()[floorMod(ordinal() - 1, Direction.values().length)];
            default -> this;
        };
    }


}
