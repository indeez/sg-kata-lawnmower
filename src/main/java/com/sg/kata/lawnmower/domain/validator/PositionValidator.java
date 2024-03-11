package com.sg.kata.lawnmower.domain.validator;

import com.sg.kata.lawnmower.domain.Position;

import javax.xml.validation.Validator;

public class PositionValidator implements ObjectValidator<Position> {
    @Override
    public boolean test(Position position) {
        return false;
    }
}
