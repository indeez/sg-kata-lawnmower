package com.mowitnow.kata.lawnmower.domain.validator;

import com.mowitnow.kata.lawnmower.domain.Command;
import com.mowitnow.kata.lawnmower.domain.Direction;
import com.mowitnow.kata.lawnmower.domain.Position;
import com.mowitnow.kata.lawnmower.domain.lawn.Lawn;
import com.mowitnow.kata.lawnmower.domain.validator.position.MovingPositionValidator;
import com.mowitnow.kata.lawnmower.domain.validator.position.MovingPositionValidatorParams;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MovingPositionValidatorTest {

    @Test
    public void should_be_invalid_to_go_south_from_position_0_0() {
        MovingPositionValidator validator = new MovingPositionValidator(new Lawn(5, 5));
        assertFalse(validator.test(new MovingPositionValidatorParams(new Position(0, 0, Direction.S), Command.A)));
    }

    @Test
    public void should_be_invalid_to_go_north_from_position_0_5() {
        MovingPositionValidator validator = new MovingPositionValidator(new Lawn(5, 5));
        assertFalse(validator.test(new MovingPositionValidatorParams(new Position(0, 5, Direction.N), Command.A)));
    }

    @Test
    public void should_be_valid_to_go_north_from_position_0_0() {
        MovingPositionValidator validator = new MovingPositionValidator(new Lawn(5, 5));
        assertTrue(validator.test(new MovingPositionValidatorParams(new Position(0, 0, Direction.N), Command.A)));
    }

    @Test
    public void should_be_valid_to_go_north_from_position_3_3() {
        MovingPositionValidator validator = new MovingPositionValidator(new Lawn(5, 5));
        assertTrue(validator.test(new MovingPositionValidatorParams(new Position(3, 3, Direction.N), Command.A)));
    }
}
