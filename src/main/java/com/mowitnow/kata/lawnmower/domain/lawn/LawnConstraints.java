package com.mowitnow.kata.lawnmower.domain.lawn;

import com.mowitnow.kata.lawnmower.domain.Command;
import com.mowitnow.kata.lawnmower.domain.Position;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LawnConstraints {

    public static boolean isValidMove(@NonNull Position from, @NonNull Lawn lawn, @NonNull Command to) {
        Position forwardPosition = to.getForwardPosition(from);
        return forwardPosition.x() >= 0 &&
                forwardPosition.x() <= lawn.width() &&
                forwardPosition.y() >= 0 &&
                forwardPosition.y() <= lawn.height();
    }

}
