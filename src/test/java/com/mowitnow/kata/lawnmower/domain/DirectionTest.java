package com.mowitnow.kata.lawnmower.domain;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class DirectionTest {

    @Test
    public void should_look_east_if_rotate_d_from_north() {
        assertSame(Direction.E, Direction.N.rotate(Command.D));
    }

    @Test
    public void should_look_west_if_rotate_d_from_south() {
        assertSame(Direction.W, Direction.S.rotate(Command.D));
    }

    @Test
    public void should_look_east_if_rotate_g_from_south() {
        assertSame(Direction.E, Direction.S.rotate(Command.G));
    }

    @Test
    public void should_look_south_if_rotate_g_from_west() {
        assertSame(Direction.S, Direction.W.rotate(Command.G));
    }
}
