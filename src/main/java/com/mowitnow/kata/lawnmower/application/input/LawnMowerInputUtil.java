package com.mowitnow.kata.lawnmower.application.input;

import com.mowitnow.kata.lawnmower.domain.Command;
import com.mowitnow.kata.lawnmower.domain.Direction;
import com.mowitnow.kata.lawnmower.domain.Position;
import com.mowitnow.kata.lawnmower.domain.lawn.Lawn;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@UtilityClass
public class LawnMowerInputUtil {

    private static final Pattern POSITION_PATTERN = Pattern.compile("^(\\d)\\s(\\d)\\s(\\w)$", Pattern.CASE_INSENSITIVE);

    @SneakyThrows
    public LawnMowerInput load(@NonNull Path filePath) {
        List<String> lines = Files.readAllLines(filePath);
        return load(lines);
    }

    public LawnMowerInput load(@NonNull String content) {
        return load(content.lines().toList());
    }

    public LawnMowerInput load(@NonNull List<String> lines) {
        checkPreconditions(lines);
        Matcher matcher = POSITION_PATTERN.matcher(lines.get(0));
        //if (matcher.matches()){
        int maxHeight = Integer.parseInt(matcher.group(1));
        int maxWidth = Integer.parseInt(matcher.group(2));
        Lawn lawn = new Lawn(maxHeight, maxWidth);

        int i = 2;
        List<LawnMowerInput.SingleMowerActions> mowerActionInputCtx = new ArrayList<>();
        while (i < lines.size()) {
            String[] split = lines.get(i).split("\\s");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);

            Direction direction = Direction.valueOf(split[2].toUpperCase().toCharArray()[0]);
            Position mowerPosition = new Position(x, y, direction);

            List<Command> commands = lines.get(i + 1).chars()
                    .mapToObj(chr -> Command.valueOf(String.valueOf(chr).toUpperCase().toCharArray()[0]))
                    .collect(Collectors.toList());
            mowerActionInputCtx.add(new LawnMowerInput.SingleMowerActions(mowerPosition, commands));

            i += 2;
        }
        return new LawnMowerInput(lawn, mowerActionInputCtx);
    }


    private static void checkPreconditions(List<String> lines) {
        if (lines.isEmpty()) {
            throw new IllegalArgumentException("Input file must not be empty!..");
        }
    }
}
