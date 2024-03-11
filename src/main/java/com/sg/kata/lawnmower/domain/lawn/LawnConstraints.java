package com.sg.kata.lawnmower.domain.lawn;

import com.sg.kata.lawnmower.domain.Command;
import com.sg.kata.lawnmower.domain.Position;

public class LawnConstraints {

    public static boolean isValidMove(Position initial, Lawn lawn, Command command) {
        //TODO Preconditions
        Position forwardPosition = command.getForwardPosition(initial);
        return forwardPosition.getX() >= 0 &&
                forwardPosition.getX() <= lawn.getWidth() &&
                forwardPosition.getY() >= 0 &&
                forwardPosition.getY() <= lawn.getHeight();
    }

}
