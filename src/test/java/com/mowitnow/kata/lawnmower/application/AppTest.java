package com.mowitnow.kata.lawnmower.application;

import com.mowitnow.kata.lawnmower.application.input.LawnMowerInput;
import com.mowitnow.kata.lawnmower.domain.Direction;
import com.mowitnow.kata.lawnmower.domain.Position;
import com.mowitnow.kata.lawnmower.domain.lawn.Lawn;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.mowitnow.kata.lawnmower.domain.Command.*;
import static com.mowitnow.kata.lawnmower.domain.Direction.E;
import static com.mowitnow.kata.lawnmower.domain.Direction.N;
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
    public void shouldAnswerWithTrue() {
        App app = new App();
        LawnMowerInput input = new LawnMowerInput(new Lawn(5, 5),
                List.of(
                        new LawnMowerInput.SingleMowerActions(
                                new Position(1, 2, Direction.N),
                                List.of(G, A, G, A, G, A, G, A, A)),
                        new LawnMowerInput.SingleMowerActions(
                                new Position(3, 3, E),
                                List.of(A, A, D, A, A, D, A, D, D, A))
                ));

        assertEquals(List.of(new Position(1, 3, N), new Position(5, 1, E)), app.computeFinalPosition(input));

    }
}
