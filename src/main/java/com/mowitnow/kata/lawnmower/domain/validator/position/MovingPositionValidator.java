package com.mowitnow.kata.lawnmower.domain.validator.position;

import com.mowitnow.kata.lawnmower.domain.Position;
import com.mowitnow.kata.lawnmower.domain.lawn.Lawn;
import com.mowitnow.kata.lawnmower.domain.validator.Validator;

public class MovingPositionValidator implements Validator<MovingPositionValidatorParams> {

    private final Lawn lawn;

    public MovingPositionValidator(Lawn lawn) {
        this.lawn = lawn;
    }

    @Override
    public boolean test(MovingPositionValidatorParams argument) {
        Position forwardPosition = argument.to().getForwardPosition(argument.from());
        return forwardPosition.x() >= 0 &&
                forwardPosition.x() <= lawn.width() &&
                forwardPosition.y() >= 0 &&
                forwardPosition.y() <= lawn.height();
    }

}
