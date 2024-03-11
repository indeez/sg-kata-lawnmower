package com.mowitnow.kata.lawnmower.application.input;

import com.mowitnow.kata.lawnmower.application.input.exceptions.InvalidInputFileException;
import com.mowitnow.kata.lawnmower.domain.Command;
import com.mowitnow.kata.lawnmower.domain.Direction;
import com.mowitnow.kata.lawnmower.domain.Position;
import com.mowitnow.kata.lawnmower.domain.lawn.Lawn;
import lombok.experimental.UtilityClass;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@UtilityClass
public class LawnMowerInputUtil {

    private static final Pattern POSITION_PATTERN = Pattern.compile("^(\\d)\\s(\\d)\\s(\\w)", Pattern.CASE_INSENSITIVE);

    //FIXME use Regex for file parsing ?
    public LawnMowerInput load(String inputFile) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(inputFile));
            //FIXME Objects.checkFromIndexSize()

            //FIXME use regex ?
            int maxHeight = Integer.parseInt(lines.get(0).split("\\s")[0]);
            int maxWidth = Integer.parseInt(lines.get(0).split("\\s")[1]);
            Lawn lawn = new Lawn(maxHeight, maxWidth);

            int i = 2;
            List<LawnMowerInput.SingleMowerActions> mowerActionInputCtxes = new ArrayList<>();
            while (i < lines.size()) {
                String[] split = lines.get(i).split("\\s");
                int x = Integer.parseInt(split[0]);
                int y = Integer.parseInt(split[1]);
                Direction direction = Direction.valueOf(split[2]);
                Position mowerPosition = new Position(x, y, direction);

                List<Command> commands = lines.get(i + 1).chars()
                        .mapToObj(chr -> Command.valueOf(String.valueOf(chr).toUpperCase()))
                        .collect(Collectors.toList());
                mowerActionInputCtxes.add(new LawnMowerInput.SingleMowerActions(mowerPosition,commands));

                i += 2;
            }
            return new LawnMowerInput(lawn, mowerActionInputCtxes);

        } catch (Exception e) {
            throw new InvalidInputFileException();
        }
    }
}
