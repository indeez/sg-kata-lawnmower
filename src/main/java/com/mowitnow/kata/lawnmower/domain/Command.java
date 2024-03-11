package com.mowitnow.kata.lawnmower.domain;

public enum Command {

    /**
     * GAUCHE
     */
    G,
    /**
     * DROITE
     */
    D,
    /**
     * AVANCE
     */
    A;

    public Position getForwardPosition(Position initial) {
        return switch (this) {
            case A -> switch (initial.direction()) {
                case E -> new Position(initial.x() + 1, initial.y(), Direction.E);
                case W -> new Position(initial.x() - 1, initial.y(), Direction.W);
                case N -> new Position(initial.x(), initial.y() + 1, Direction.N);
                case S -> new Position(initial.x(), initial.y() - 1, Direction.S);
            };
            case D, G -> new Position(initial.x(), initial.y(), initial.direction().rotate(this));
            default -> throw new IllegalStateException("Unexpected value: " + this);
        };
    }

}
