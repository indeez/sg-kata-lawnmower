package com.mowitnow.kata.lawnmower.domain;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {

    @Test
    public void should_move_to_north_from_one_position() {
        assertEquals(new Position(1, 2, Direction.NORTH), Command.FORWARD.getForwardPosition(new Position(1, 1, Direction.NORTH)));
    }

    @Test
    public void should_rotate_to_west_without_moving() {
        assertEquals(new Position(1, 1, Direction.WEST), Command.ROTATE_LEFT.getForwardPosition(new Position(1, 1, Direction.NORTH)));
    }


}
