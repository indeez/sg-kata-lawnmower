package com.mowitnow.kata.lawnmower.application;

import com.mowitnow.kata.lawnmower.application.input.LawnMowerInput;
import com.mowitnow.kata.lawnmower.application.input.LawnMowerInputUtil;
import com.mowitnow.kata.lawnmower.domain.Command;
import com.mowitnow.kata.lawnmower.domain.Position;
import com.mowitnow.kata.lawnmower.domain.validator.Validator;
import com.mowitnow.kata.lawnmower.domain.validator.position.MovingPositionValidator;
import com.mowitnow.kata.lawnmower.domain.validator.position.MovingPositionValidatorParams;
import lombok.extern.log4j.Log4j2;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Log4j2
public class App {
    public List<Position> mow(String inputContent) {
        LawnMowerInput ctx = LawnMowerInputUtil.load(inputContent);
        return computeFinalPosition(ctx);
    }

    public List<Position> mow(Path inputFile) {
        LawnMowerInput ctx = LawnMowerInputUtil.load(inputFile);
        return computeFinalPosition(ctx);
    }

    List<Position> computeFinalPosition(LawnMowerInput ctx) {
        List<Position> answer = new ArrayList<>();
        for (var mowerAction : ctx.singleMowerActions()) {
            Position currentPosition = mowerAction.initial();
            Validator<MovingPositionValidatorParams> validator = new MovingPositionValidator(ctx.lawn());
            for (Command command : mowerAction.commands()) {
                if (validator.test(new MovingPositionValidatorParams(currentPosition, command))) {
                    currentPosition = command.getForwardPosition(currentPosition);
                    log.debug(command + " | " + currentPosition);
                } else {
                    log.debug("apply " + command + " into position " + currentPosition + " is impossible");
                }
            }
            answer.add(currentPosition);
        }
        return answer;
    }

    public static void main(String[] args) {
        App application = new App();
        application.mow(args[0]).forEach(log::info);
    }
}
