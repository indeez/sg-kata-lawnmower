package com.mowitnow.kata.lawnmower.application;

import com.mowitnow.kata.lawnmower.application.input.LawnMowerInput;
import com.mowitnow.kata.lawnmower.domain.Direction;
import com.mowitnow.kata.lawnmower.domain.Position;
import com.mowitnow.kata.lawnmower.domain.lawn.Lawn;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.mowitnow.kata.lawnmower.domain.Command.*;
import static com.mowitnow.kata.lawnmower.domain.Direction.EAST;
import static com.mowitnow.kata.lawnmower.domain.Direction.NORTH;
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


    @Test
    public void should_be_true_using_raw_content_file_as_input() {
        App app = new App();
        List<Position> endPositions = app.mow(INPUT_CONTENT);
        assertEquals(List.of(new Position(1, 3, NORTH), new Position(5, 1, EAST)), endPositions);
    }

    @Test
    public void should_be_true_with_kata_provided_specification() {
        App app = new App();
        LawnMowerInput input = new LawnMowerInput(new Lawn(5, 5),
                List.of(
                        new LawnMowerInput.SingleMowerActions(
                                new Position(1, 2, Direction.NORTH),
                                List.of(ROTATE_LEFT, FORWARD, ROTATE_LEFT, FORWARD, ROTATE_LEFT, FORWARD, ROTATE_LEFT, FORWARD, FORWARD)),
                        new LawnMowerInput.SingleMowerActions(
                                new Position(3, 3, EAST),
                                List.of(FORWARD, FORWARD, ROTATE_RIGHT, FORWARD, FORWARD, ROTATE_RIGHT, FORWARD, ROTATE_RIGHT, ROTATE_RIGHT, FORWARD))
                ));
        assertEquals(List.of(new Position(1, 3, NORTH), new Position(5, 1, EAST)), app.doMow(input));
    }
}
