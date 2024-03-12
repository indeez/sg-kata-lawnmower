package com.mowitnow.kata.lawnmower.application;

import com.mowitnow.kata.lawnmower.application.input.LawnMowerInput;
import com.mowitnow.kata.lawnmower.application.input.LawnMowerInputLoader;
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
        LawnMowerInput ctx = LawnMowerInputLoader.load(inputContent);
        return doMow(ctx);
    }

    public List<Position> mow(Path inputFile) {
        LawnMowerInput ctx = LawnMowerInputLoader.load(inputFile);
        return doMow(ctx);
    }

    List<Position> doMow(LawnMowerInput ctx) {
        List<Position> answer = new ArrayList<>();
        for (var mowerAction : ctx.singleMowerActions()) {
            Position currentPosition = mowerAction.initial();
            Validator<MovingPositionValidatorParams> validator = new MovingPositionValidator(ctx.lawn());
            var endPosition = mowerAction.commands().stream()
                    .map(cmd -> new Pair<>(currentPosition,cmd))
                    .filter(pair -> validator.test(new MovingPositionValidatorParams(pair.left(),pair.right())))
                    .reduce(new Pair<>(currentPosition,Command.NOP),(acc, e) -> new Pair<>(e.right().getForwardPosition(acc.left()),e.right()))
                    .left();
            answer.add(endPosition);
        }
        return answer;
    }

    record Pair<K,V>(K left, V right) {};

    public static void main(String[] args) {
        App application = new App();
        application.mow(args[0]).forEach(log::info);
    }
}
