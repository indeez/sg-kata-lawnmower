package com.mowitnow.kata.lawnmower.application.input;

import com.mowitnow.kata.lawnmower.domain.Command;
import com.mowitnow.kata.lawnmower.domain.Position;
import com.mowitnow.kata.lawnmower.domain.lawn.Lawn;
import lombok.Value;

import java.util.List;

@Value
public class LawnMowerInput {
    Lawn lawn;
    List<SingleMowerActions> singleMowerActions;

    @Value
    public static class SingleMowerActions {
        Position initial;
        List<Command> commands;
    }
}
