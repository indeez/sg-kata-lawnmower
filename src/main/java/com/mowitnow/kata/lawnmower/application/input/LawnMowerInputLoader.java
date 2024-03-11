package com.mowitnow.kata.lawnmower.application.input;

import com.mowitnow.kata.lawnmower.application.input.exceptions.InvalidInputFileException;
import com.mowitnow.kata.lawnmower.domain.Command;
import com.mowitnow.kata.lawnmower.domain.Direction;
import com.mowitnow.kata.lawnmower.domain.Position;
import com.mowitnow.kata.lawnmower.domain.lawn.Lawn;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class LawnMowerInputLoader {

    private static final Pattern COORDINATE_PATTERN = Pattern.compile("^(\\d)\\s(\\d)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern POSITION_PATTERN = Pattern.compile("^(\\d)\\s(\\d)\\s(\\w)$", Pattern.CASE_INSENSITIVE);

    @SneakyThrows
    public LawnMowerInput load(@NonNull Path filePath) {
        List<String> lines = Files.readAllLines(filePath);
        return load(lines);
    }

    public LawnMowerInput load(@NonNull String rawContent) {
        return load(rawContent.lines().toList());
    }

    public LawnMowerInput load(@NonNull List<String> lines) {
        checkPreconditions(lines);
        Lawn lawn = extractLawn(lines.get(0));
        int i = 1;
        List<LawnMowerInput.SingleMowerActions> mowerActionInputCtx = new ArrayList<>();
        while (i < lines.size()) {
            Position from = extractPosition(lines, i);
            List<Command> commands = extractAllCommands(lines, i);
            mowerActionInputCtx.add(new LawnMowerInput.SingleMowerActions(from, commands));
            i += 2;
        }
        return new LawnMowerInput(lawn, mowerActionInputCtx);
    }

    private static List<Command> extractAllCommands(List<String> lines, int i) {
        return lines.get(i + 1).chars().mapToObj(chr -> (char) chr)
                .map(Command::valueOf)
                .toList();
    }

    private static Position extractPosition(List<String> lines, int i) {
        Matcher matcher = POSITION_PATTERN.matcher(lines.get(i));
        Position from;
        if (matcher.matches()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            Direction direction = Direction.valueOf(matcher.group(3).toUpperCase().toCharArray()[0]);
            from = new Position(x, y, direction);
        } else {
            throw new InvalidInputFileException();
        }
        return from;
    }

    private static Lawn extractLawn(String line) {
        Matcher matcher = COORDINATE_PATTERN.matcher(line);
        Lawn lawn;
        if (matcher.matches()) {
            int maxHeight = Integer.parseInt(matcher.group(1));
            int maxWidth = Integer.parseInt(matcher.group(2));
            lawn = new Lawn(maxHeight, maxWidth);
        } else {
            throw new InvalidInputFileException();
        }
        return lawn;
    }


    private static void checkPreconditions(List<String> lines) {
        if (lines.isEmpty()) {
            throw new IllegalArgumentException("Input file must not be empty!..");
        }
    }
}
