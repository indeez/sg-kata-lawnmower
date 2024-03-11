package com.mowitnow.kata.lawnmower.domain;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class DirectionTest {

    @Test
    public void should_look_east_if_rotate_d_from_north() {
        assertSame(Direction.EAST, Direction.NORTH.rotate(Command.ROTATE_RIGHT));
    }

    @Test
    public void should_look_west_if_rotate_d_from_south() {
        assertSame(Direction.WEST, Direction.SOUTH.rotate(Command.ROTATE_RIGHT));
    }

    @Test
    public void should_look_east_if_rotate_g_from_south() {
        assertSame(Direction.EAST, Direction.SOUTH.rotate(Command.ROTATE_LEFT));
    }

    @Test
    public void should_look_south_if_rotate_g_from_west() {
        assertSame(Direction.SOUTH, Direction.WEST.rotate(Command.ROTATE_LEFT));
    }
}
