package com.sg.kata.lawnmower.application.input;

import com.sg.kata.lawnmower.domain.Command;
import com.sg.kata.lawnmower.domain.Position;
import com.sg.kata.lawnmower.domain.lawn.Lawn;
import lombok.Value;

import java.util.List;

@Value
public class LawnMowerInput {
    Lawn lawn;
    List<MowerActions> mowerActions;
    
    @Value public static class MowerActions {
        Position initial;
        List<Command> commands;
    }
}
