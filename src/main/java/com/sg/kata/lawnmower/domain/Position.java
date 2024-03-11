package com.sg.kata.lawnmower.domain;

import lombok.Value;

@Value
public class Position {
    int x;
    int y;
    Direction direction;
}
