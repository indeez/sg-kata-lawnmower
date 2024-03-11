package com.mowitnow.kata.lawnmower.domain.validator.position;

import com.mowitnow.kata.lawnmower.domain.Command;
import com.mowitnow.kata.lawnmower.domain.Position;

public record MovingPositionValidatorParams(Position from, Command command) {
}
