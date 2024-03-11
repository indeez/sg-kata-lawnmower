package com.mowitnow.kata.lawnmower.domain.validator;

import com.mowitnow.kata.lawnmower.domain.Command;
import com.mowitnow.kata.lawnmower.domain.Position;
import com.mowitnow.kata.lawnmower.domain.lawn.Lawn;

public class MovingPositionValidator implements Validator<MovingPositionValidator.PositionValidatorArgument> {

    @Override
    public boolean test(PositionValidatorArgument argument) {
        Position forwardPosition = argument.command().getForwardPosition(argument.initial());
        return forwardPosition.x() >= 0 &&
                forwardPosition.x() <= argument.lawn().getWidth() &&
                forwardPosition.y() >= 0 &&
                forwardPosition.y() <= argument.lawn().getHeight();
    }

    public record PositionValidatorArgument(Position initial, Command command, Lawn lawn) {
    }
}
