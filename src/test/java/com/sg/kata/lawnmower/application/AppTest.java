package com.sg.kata.lawnmower.application;

import com.sg.kata.lawnmower.application.input.LawnMowerInput;
import com.sg.kata.lawnmower.domain.Direction;
import com.sg.kata.lawnmower.domain.Position;
import com.sg.kata.lawnmower.domain.lawn.Lawn;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static com.sg.kata.lawnmower.domain.Command.*;
import static com.sg.kata.lawnmower.domain.Direction.E;
import static com.sg.kata.lawnmower.domain.Direction.N;

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

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        App app = new App();
        LawnMowerInput input = new LawnMowerInput(new Lawn(5, 5),
                List.of(
                        new LawnMowerInput.MowerActions(
                                new Position(1, 2, Direction.N),
                                List.of(G, A, G, A, G, A, G, A, A)),
                        new LawnMowerInput.MowerActions(
                                new Position(3, 3, E),
                                List.of(A, A, D, A, A, D, A, D, D, A))
                        ));

        Assert.assertTrue(List.of(new Position(1, 3, N),
                new Position(5, 1, E)).equals(app.computeFinalPosition(input)));

    }
}
