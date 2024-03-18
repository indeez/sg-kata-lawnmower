package com.mowitnow.kata.lawnmower.app;

import com.mowitnow.kata.lawnmower.app.input.LawnMowerGroup;
import com.mowitnow.kata.lawnmower.domain.Direction;
import com.mowitnow.kata.lawnmower.domain.Position;
import com.mowitnow.kata.lawnmower.domain.lawn.Lawn;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static com.mowitnow.kata.lawnmower.domain.Command.*;
import static com.mowitnow.kata.lawnmower.domain.Direction.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private final String INPUT_CONTENT = """
            5 5
            1 2 N
            GAGAGAGAA
            3 3 E
            AADAADADDA
            """;

    private final String LEFT_BOTTOM_STARTING_BORDER_CONTENT = """
            5 5
            0 0 N
            GAGA            
            """;

    @Test
    public void should_be_true_using_raw_content_file_as_input() {
        App app = new App();
        List<Position> endPositions = app.mow(INPUT_CONTENT);
        assertEquals(List.of(new Position(1, 3, NORTH), new Position(5, 1, EAST)), endPositions);
    }

    @Test
    public void should_be_true_with_kata_provided_specification() {
        App app = new App();
        LawnMowerGroup input = new LawnMowerGroup(new Lawn(5, 5),
                List.of(
                        new LawnMowerGroup.LawnMower(UUID.randomUUID(),
                                new Position(1, 2, Direction.NORTH),
                                List.of(ROTATE_LEFT, FORWARD, ROTATE_LEFT, FORWARD, ROTATE_LEFT, FORWARD, ROTATE_LEFT, FORWARD, FORWARD)),
                        new LawnMowerGroup.LawnMower(UUID.randomUUID(),
                                new Position(3, 3, EAST),
                                List.of(FORWARD, FORWARD, ROTATE_RIGHT, FORWARD, FORWARD, ROTATE_RIGHT, FORWARD, ROTATE_RIGHT, ROTATE_RIGHT, FORWARD))
                ));
        assertEquals(List.of(new Position(1, 3, NORTH), new Position(5, 1, EAST)), app.doMow(input));
    }

    @Test
    public void should_finish_at_same_position_if_in_right_bottom_initially() {
        App app = new App();
        List<Position> endPositions = app.mow(LEFT_BOTTOM_STARTING_BORDER_CONTENT);
        assertEquals(List.of(new Position(0, 0, SOUTH)), endPositions);
    }
}
