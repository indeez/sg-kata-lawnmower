package com.mowitnow.kata.lawnmower.domain.validator;

import com.mowitnow.kata.lawnmower.domain.Direction;
import com.mowitnow.kata.lawnmower.domain.Position;
import com.mowitnow.kata.lawnmower.domain.lawn.Lawn;
import com.mowitnow.kata.lawnmower.domain.validator.position.PositionValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PositionValidatorTest {

    @Test
    public void should_be_invalid_to_go_south_from_position_minus_1_minus_1() {
        PositionValidator validator = new PositionValidator(new Lawn(5, 5));
        assertFalse(validator.test(new Position(-1, -1, Direction.SOUTH)));
    }

    @Test
    public void should_be_invalid_to_go_north_from_position_0_6() {
        PositionValidator validator = new PositionValidator(new Lawn(5, 5));
        assertFalse(validator.test(new Position(0, 6, Direction.NORTH)));
    }

    @Test
    public void should_be_valid_to_go_north_from_position_0_0() {
        PositionValidator validator = new PositionValidator(new Lawn(5, 5));
        assertTrue(validator.test(new Position(0, 0, Direction.NORTH)));
    }

    @Test
    public void should_be_valid_to_go_north_from_position_3_3() {
        PositionValidator validator = new PositionValidator(new Lawn(5, 5));
        assertTrue(validator.test(new Position(3, 3, Direction.NORTH)));
    }
}
