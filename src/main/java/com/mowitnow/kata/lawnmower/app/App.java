package com.mowitnow.kata.lawnmower.app;

import com.mowitnow.kata.lawnmower.app.input.LawnMowerGroup;
import com.mowitnow.kata.lawnmower.app.input.LawnMowerInputLoader;
import com.mowitnow.kata.lawnmower.domain.Position;
import com.mowitnow.kata.lawnmower.domain.validator.position.PositionValidator;
import lombok.extern.log4j.Log4j2;

import java.nio.file.Path;
import java.util.*;

import static com.mowitnow.kata.lawnmower.domain.Command.NOP;
import static java.util.stream.Collectors.*;

/**
 *
 */
@Log4j2
public class App {
    List<Position> doMow(LawnMowerGroup lawnMowerGroup) {
        List<Position> answer = new ArrayList<>();

//        Map<UUID, Optional<Position>> p = lawnMowerGroup.lawnMowers()
//                .stream()
//                .collect(groupingBy(LawnMowerGroup.LawnMower::id,
//                        collectingAndThen(flatMapping(lawnMower -> lawnMower.commands()
//                                                .stream()
//                                                .map(cmd -> new Directive<>(cmd, cmd.getForwardPosition(lawnMower.initialPosition(), lawnMowerGroup))),
//                                        reducing((acc, directive) ->
//                                                new Directive<>(
//                                                        directive.commandToApply(), directive.commandToApply().getForwardPosition(acc.position(), lawnMowerGroup)))),
//                                result -> result.map(Directive::position))));

        for (var lawnMower : lawnMowerGroup.lawnMowers()) {
            Position currentPosition = lawnMower.initialPosition();
            var endPosition = lawnMower.commands().stream()
                    .map(cmd -> new Directive<>(cmd, currentPosition))
                    .reduce(new Directive<>(NOP, currentPosition),
                            (accumulator, e) -> new Directive<>(e.commandToApply(), e.commandToApply().getForwardPosition(accumulator.position(),
                                    new PositionValidator(lawnMowerGroup.lawn()))))
                    .position();
            answer.add(endPosition);
        }
        return answer;
    }

    public List<Position> mow(String inputContent) {
        LawnMowerInputLoader loader = new LawnMowerInputLoader();
        LawnMowerGroup ctx = loader.load(inputContent);
        return doMow(ctx);
    }

    public List<Position> mow(Path inputFile) {
        LawnMowerInputLoader loader = new LawnMowerInputLoader();
        LawnMowerGroup ctx = loader.load(inputFile);
        return doMow(ctx);
    }

    record Directive<K, V>(V commandToApply, K position) {
    }

    public static void main(String[] args) {
        App application = new App();
        application.mow(args[0]).forEach(log::info);
    }
}
