package Enums;

import java.util.Objects;

public enum Direction {
    NORTH("move north"), SOUTH("move south"), EAST("move east"), WEST("move west");
    private String input;
    Direction(String input) {
        this.input = input;
    }

    public boolean checkDirection(String playerInput) {
        return Objects.equals(playerInput, this.input);
    }
}