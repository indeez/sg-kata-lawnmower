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

    public Position getForwardPosition(Position from) {
        return switch (this) {
            case A -> switch (from.direction()) {
                case E -> new Position(from.x() + 1, from.y(), Direction.E);
                case W -> new Position(from.x() - 1, from.y(), Direction.W);
                case N -> new Position(from.x(), from.y() + 1, Direction.N);
                case S -> new Position(from.x(), from.y() - 1, Direction.S);
            };
            case D, G -> new Position(from.x(), from.y(), from.direction().rotate(this));
        };
    }

}
