package com.mowitnow.kata.lawnmower.app.input;

import com.mowitnow.kata.lawnmower.app.input.exceptions.InvalidInputContentFileException;
import com.mowitnow.kata.lawnmower.domain.Command;
import com.mowitnow.kata.lawnmower.domain.Direction;
import com.mowitnow.kata.lawnmower.domain.Position;
import com.mowitnow.kata.lawnmower.domain.lawn.Lawn;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LawnMowerInputLoader {

    private static final Pattern COORDINATE_PATTERN = Pattern.compile("^(\\d)\\s(\\d)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern POSITION_PATTERN = Pattern.compile("^(\\d)\\s(\\d)\\s(\\w)$", Pattern.CASE_INSENSITIVE);

    @SneakyThrows
    public LawnMowerGroup load(@NonNull Path filePath) {
        List<String> lines = Files.readAllLines(filePath);
        return load(lines);
    }

    public LawnMowerGroup load(@NonNull String rawContent) {
        return load(rawContent.lines().toList());
    }

    public LawnMowerGroup load(@NonNull List<String> lines) {
        checkPreconditions(lines);
        Lawn lawn = extractLawn(lines.get(0));
        int i = 1;
        List<LawnMowerGroup.LawnMower> LawnMowerInputCtx = new ArrayList<>();
        while (i < lines.size()) {
            Position from = extractPosition(lines, i);
            List<Command> commands = extractAllCommands(lines, i);
            LawnMowerInputCtx.add(new LawnMowerGroup.LawnMower(UUID.randomUUID(),from, commands));
            i += 2;
        }
        return new LawnMowerGroup(lawn, LawnMowerInputCtx);
    }

    private static List<Command> extractAllCommands(List<String> lines, int i) {
        return lines.get(i + 1).chars().mapToObj(chr -> (char) chr)
                .map(Command::valueOf)
                .toList();
    }

    private static Position extractPosition(List<String> lines, int i) {
        Matcher matcher = POSITION_PATTERN.matcher(lines.get(i));
        Position answer;
        if (matcher.matches()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            Direction direction = Direction.valueOf(matcher.group(3).toUpperCase().toCharArray()[0]);
            answer = new Position(x, y, direction);
        } else {
            throw new InvalidInputContentFileException("Unable to extract position from input content!");
        }
        return answer;
    }

    private static Lawn extractLawn(String line) {
        Matcher matcher = COORDINATE_PATTERN.matcher(line);
        Lawn answer;
        if (matcher.matches()) {
            int maxHeight = Integer.parseInt(matcher.group(1));
            int maxWidth = Integer.parseInt(matcher.group(2));
            answer = new Lawn(maxHeight, maxWidth);
        } else {
            throw new InvalidInputContentFileException("Unable to extract Lawn caracteristics from input content !");
        }
        return answer;
    }


    private static void checkPreconditions(List<String> lines) {
        if (lines.isEmpty()) {
            throw new IllegalArgumentException("Input file must not be empty!..");
        }
    }
}
