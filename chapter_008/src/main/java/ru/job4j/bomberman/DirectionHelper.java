package ru.job4j.bomberman;

public class DirectionHelper {
    public static Direction clockwiseNext(Direction direction) {
        Direction result;
        if (direction.equals(Direction.WEST)) {
            result = Direction.SOUTH;
        } else if (direction.equals(Direction.SOUTH)) {
            result = Direction.EAST;
        } else if (direction.equals(Direction.EAST)) {
            result = Direction.NORTH;
        } else {
            result = Direction.WEST;
        }
        return result;
    }
}