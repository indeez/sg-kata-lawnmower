package com.sg.kata.lawnmower.application;

import com.sg.kata.lawnmower.application.input.LawnMowerInput;
import com.sg.kata.lawnmower.application.input.LawnMowerInputUtil;
import com.sg.kata.lawnmower.domain.Command;
import com.sg.kata.lawnmower.domain.Position;
import com.sg.kata.lawnmower.domain.lawn.LawnConstraints;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class App {

    public List<Position> execute(String inputFile) {
        LawnMowerInput ctx = LawnMowerInputUtil.load(inputFile);
        return computeFinalPosition(ctx);
    }

    List<Position> computeFinalPosition(LawnMowerInput ctx) {
        List<Position> result = new ArrayList<>();
        for (LawnMowerInput.MowerActions mowerAction : ctx.getMowerActions()) {
            Position currentPosition = mowerAction.getInitial();
            for (Command command : mowerAction.getCommands()) {
                if (LawnConstraints.isValidMove(currentPosition, ctx.getLawn(), command)) {
                    currentPosition = command.getForwardPosition(currentPosition);
                    System.out.println(command + " | " + currentPosition);
                } else {
                    System.out.println("apply " + command + " into position " + currentPosition + " is impossible");
                }
            }
            result.add(currentPosition);
        }
        return result;
    }

    public static void main(String[] args) {
        App application = new App();
        application.execute(args[0]).forEach(System.out::println);
    }
}
