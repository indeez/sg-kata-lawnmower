package com.mowitnow.kata.lawnmower.app.input;

import com.mowitnow.kata.lawnmower.domain.Command;
import com.mowitnow.kata.lawnmower.domain.Position;
import com.mowitnow.kata.lawnmower.domain.lawn.Lawn;

import java.util.List;
import java.util.UUID;

public record LawnMowerGroup(Lawn lawn, List<LawnMower> lawnMowers) {
    public record LawnMower(UUID id, Position initialPosition, List<Command> commands) {
    }
}
