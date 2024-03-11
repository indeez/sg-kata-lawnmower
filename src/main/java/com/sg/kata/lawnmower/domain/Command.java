package com.sg.kata.lawnmower.domain;

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
        switch (this) {
            case A:
                switch (initial.getDirection()) {
                    case E:
                        return new Position(initial.getX() + 1, initial.getY(), Direction.E);
                    case W:
                        return new Position(initial.getX() - 1, initial.getY(), Direction.W);
                    case N:
                        return new Position(initial.getX(), initial.getY() + 1, Direction.N);
                    case S:
                        return new Position(initial.getX(), initial.getY() - 1, Direction.S);
                }
            case D:
            case G:
                return new Position(initial.getX(), initial.getY(), initial.getDirection().rotate(this));
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }

}
