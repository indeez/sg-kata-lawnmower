package com.mowitnow.kata.lawnmower.domain.lawn;

import com.mowitnow.kata.lawnmower.domain.Command;
import com.mowitnow.kata.lawnmower.domain.Position;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LawnConstraints {

    public static boolean isValidMove(@NonNull Position initial, @NonNull Lawn lawn, @NonNull Command command) {
        Position forwardPosition = command.getForwardPosition(initial);
        return forwardPosition.x() >= 0 &&
                forwardPosition.x() <= lawn.getWidth() &&
                forwardPosition.y() >= 0 &&
                forwardPosition.y() <= lawn.getHeight();
    }

}
