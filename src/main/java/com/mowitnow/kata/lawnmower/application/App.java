package com.mowitnow.kata.lawnmower.application;

import com.mowitnow.kata.lawnmower.application.input.LawnMowerInput;
import com.mowitnow.kata.lawnmower.application.input.LawnMowerInputUtil;
import com.mowitnow.kata.lawnmower.domain.Command;
import com.mowitnow.kata.lawnmower.domain.Position;
import com.mowitnow.kata.lawnmower.domain.lawn.LawnConstraints;
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
            for (Command command : mowerAction.getCommands()) {
                if (LawnConstraints.isValidMove(currentPosition, ctx.getLawn(), command)) {
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
