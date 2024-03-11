package com.mowitnow.kata.lawnmower.domain;

import lombok.Getter;

import static java.lang.Math.addExact;
import static java.lang.Math.subtractExact;

@Getter
public enum Command {

    /**
     * Turn at left without moving
     */
    ROTATE_LEFT('G'),
    /**
     * Turn at right without moving
     */
    ROTATE_RIGHT('D'),
    /**
     * keep moving from one position
     */
    FORWARD('A');

    private final char canonicalName;

    Command(char canonicalName) {
        this.canonicalName = canonicalName;
    }

    public static Command valueOf(char canonicalName) {
        return switch (canonicalName) {
            case 'G' -> ROTATE_LEFT;
            case 'D' -> ROTATE_RIGHT;
            case 'A' -> FORWARD;
            default -> throw new IllegalArgumentException("Unexpected value: " + canonicalName);
        };
    }

    public Position getForwardPosition(Position from) {
        return switch (this) {
            case FORWARD -> switch (from.direction()) {
                case EAST -> new Position(addExact(from.x(), 1), from.y(), Direction.EAST);
                case WEST -> new Position(subtractExact(from.x(), 1), from.y(), Direction.WEST);
                case NORTH -> new Position(from.x(), addExact(from.y(), 1), Direction.NORTH);
                case SOUTH -> new Position(from.x(), subtractExact(from.y(), 1), Direction.SOUTH);
            };
            case ROTATE_RIGHT, ROTATE_LEFT -> new Position(from.x(), from.y(), from.direction().rotate(this));
        };
    }

}
