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

    public static Direction valueOf(char direction){
        return switch (direction){
            case 'N' -> NORTH;
            case 'E'  -> EAST;
            case 'S' -> SOUTH;
            case 'W' -> WEST;
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        };
    }

    public Direction rotate(Command command) {
        return switch (command) {
            case ROTATE_RIGHT -> Direction.values()[floorMod(ordinal() + 1, Direction.values().length)];
            case ROTATE_LEFT -> Direction.values()[floorMod(ordinal() - 1, Direction.values().length)];
            default -> this;
        };
    }


}
