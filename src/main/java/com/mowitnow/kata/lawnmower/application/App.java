package com.mowitnow.kata.lawnmower.application;

import com.mowitnow.kata.lawnmower.application.input.LawnMowerInput;
import com.mowitnow.kata.lawnmower.application.input.LawnMowerInputUtil;
import com.mowitnow.kata.lawnmower.domain.Command;
import com.mowitnow.kata.lawnmower.domain.Position;
import com.mowitnow.kata.lawnmower.domain.validator.Validator;
import com.mowitnow.kata.lawnmower.domain.validator.position.MovingPositionValidator;
import com.mowitnow.kata.lawnmower.domain.validator.position.MovingPositionValidatorParams;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Log4j2
public class App {
    public List<Position> execute(String inputFile) {
        LawnMowerInput ctx = LawnMowerInputUtil.load(inputFile);
        return computeFinalPosition(ctx);
    }

    List<Position> computeFinalPosition(LawnMowerInput ctx) {
        List<Position> result = new ArrayList<>();

        for (var mowerAction : ctx.getSingleMowerActions()) {
            Position currentPosition = mowerAction.getInitial();
            Validator<MovingPositionValidatorParams> validator = new MovingPositionValidator(ctx.getLawn());
            for (Command command : mowerAction.getCommands()) {
                if (validator.test(new MovingPositionValidatorParams(currentPosition, command))) {
                    currentPosition = command.getForwardPosition(currentPosition);
                    log.debug(command + " | " + currentPosition);
                } else {
                    log.debug("apply " + command + " into position " + currentPosition + " is impossible");
                }
            }
            result.add(currentPosition);
        }
        return result;

    }

    public static void main(String[] args) {
        App application = new App();
        application.execute(args[0]).forEach(log::info);
    }
}
