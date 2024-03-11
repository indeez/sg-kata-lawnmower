package com.sg.kata.lawnmower.domain;

import org.junit.Assert;
import org.junit.Test;

public class DirectionTest {

    @Test
    public void should_look_east_if_rotate_d_from_north() {
        Assert.assertSame("",Direction.E,Direction.N.rotate(Command.D));
    }

    @Test
    public void should_look_west_if_rotate_d_from_south() {
        Assert.assertSame("",Direction.W,Direction.S.rotate(Command.D));
    }

    @Test
    public void should_look_east_if_rotate_g_from_south() {
        Assert.assertSame("",Direction.E,Direction.S.rotate(Command.G));
    }

    @Test
    public void should_look_south_if_rotate_g_from_west() {
        Assert.assertSame("",Direction.S,Direction.W.rotate(Command.G));
    }
}
