package hws.hw8;

/**
 * An enumeration of the four possible directions that actors can move.
 *
 * @author CS159 Faculty
 * @version 03/25/2024
 */
public enum Direction {

    UP(0, 1), DOWN(0, -1), LEFT(-1, 0), RIGHT(1, 0);

    private final double dx;
    private final double dy;

    /**
     * Explicit value constructor.
     *
     * @param dx delta in the x direction
     * @param dy delta in the y direction
     */
    Direction(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    /**
     * Get the opposite of this direction.
     *
     * @return the opposite direction
     */
    public Direction getOpposite() {
        switch (this) {
            case UP:
                return DOWN;
            case RIGHT:
                return LEFT;
            case DOWN:
                return UP;
            case LEFT:
            default:
                return RIGHT;
        }
    }

    /**
     * Get the direction rotated to the left.
     *
     * @return the direction to the left
     */
    public Direction getRotatedLeft() {
        switch (this) {
            case UP:
                return LEFT;
            case RIGHT:
                return UP;
            case DOWN:
                return RIGHT;
            case LEFT:
            default:
                return DOWN;
        }
    }

    /**
     * Get the direction rotated to the right.
     *
     * @return the direction to the right
     */
    public Direction getRotatedRight() {
        switch (this) {
            case UP:
                return RIGHT;
            case RIGHT:
                return DOWN;
            case DOWN:
                return LEFT;
            case LEFT:
            default:
                return UP;
        }
    }

    /**
     * Turn either left or right (with 50% probability).
     *
     * @return the new direction
     */
    public Direction getRandomTurn() {
        if (Math.random() < 0.5) {
            return getRotatedLeft();
        } else {
            return getRotatedRight();
        }
    }

    /**
     * Get a random direction (with equal probability).
     *
     * @return the new direction
     */
    public static Direction getRandomDirection() {
        int random = (int) (Math.random() * 4);
        switch (random) {
            case 0:
                return UP;
            case 1:
                return RIGHT;
            case 2:
                return DOWN;
            case 3:
            default:
                return LEFT;
        }
    }

    /**
     * Determine if this direction is UP or DOWN.
     *
     * @return true if UP or DOWN, false otherwise
     */
    public boolean isUpDown() {
        return this == UP || this == DOWN;
    }
}
