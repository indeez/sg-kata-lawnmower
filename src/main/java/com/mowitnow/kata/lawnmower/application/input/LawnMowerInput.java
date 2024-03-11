package com.mowitnow.kata.lawnmower.application.input;

import com.mowitnow.kata.lawnmower.domain.Command;
import com.mowitnow.kata.lawnmower.domain.Position;
import com.mowitnow.kata.lawnmower.domain.lawn.Lawn;

import java.util.List;

public record LawnMowerInput(Lawn lawn, List<SingleMowerActions> singleMowerActions) {
    public record SingleMowerActions(Position initial, List<Command> commands) {
    }
}
