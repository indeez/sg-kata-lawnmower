package com.mowitnow.kata.lawnmower.domain.validator.position;

import com.mowitnow.kata.lawnmower.domain.Position;
import com.mowitnow.kata.lawnmower.domain.lawn.Lawn;
import com.mowitnow.kata.lawnmower.domain.validator.Validator;

public class PositionValidator implements Validator<Position> {

    private final Lawn lawn;

    public PositionValidator(Lawn lawn) {
        this.lawn = lawn;
    }

    @Override
    public boolean test(Position position) {
        return position.x() >= 0 &&
                position.x() <= lawn.width() &&
                position.y() >= 0 &&
                position.y() <= lawn.height();
    }

}
