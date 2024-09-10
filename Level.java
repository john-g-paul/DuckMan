package hws.hw8;

import edu.princeton.cs.introcs.StdDraw;

/**
 * A level of the game that represents the wall and dot locations.
 *
 * @author CS159 Faculty
 * @version 03/25/2024
 */
public class Level implements Drawable {

    private static final int[][] LEVEL_MAP = {
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1},
        {1, 3, 1, 1, 2, 1, 1, 2, 1, 2, 1, 1, 2, 1, 1, 3, 1},
        {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
        {1, 2, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 2, 1},
        {1, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 1},
        {1, 1, 1, 1, 2, 1, 1, 0, 1, 0, 1, 1, 2, 1, 1, 1, 1},
        {1, 0, 0, 1, 2, 1, 0, 0, 0, 0, 0, 1, 2, 1, 0, 0, 1},
        {1, 0, 0, 1, 2, 0, 0, 1, 1, 1, 0, 0, 2, 1, 0, 0, 1},
        {1, 0, 0, 1, 2, 1, 0, 1, 1, 1, 0, 1, 2, 1, 0, 0, 1},
        {1, 0, 0, 1, 2, 1, 0, 0, 0, 0, 0, 1, 2, 1, 0, 0, 1},
        {1, 1, 1, 1, 2, 1, 0, 1, 1, 1, 0, 1, 2, 1, 1, 1, 1},
        {1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1},
        {1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1},
        {1, 3, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 3, 1},
        {1, 1, 2, 1, 2, 1, 2, 1, 1, 1, 2, 1, 2, 1, 2, 1, 1},
        {1, 2, 2, 2, 2, 1, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 1},
        {1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1},
        {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };

    private int gridPixelSize;

    /**
     * Explicit value constructor.
     *
     * @param gridPixelSize how many pixels per grid tile
     */
    public Level(int gridPixelSize) {
        this.gridPixelSize = gridPixelSize;
    }

    /**
     * Get the grid tile at the (x, y) location.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the tile code
     */
    private int getTile(double x, double y) {
        return getTile((int) x, (int) y);
    }

    /**
     * Get the grid tile at the (x, y) location.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the tile code
     */
    private int getTile(int x, int y) {
        if (x < 0 || x >= LEVEL_MAP[0].length
            || y < 0 || y >= LEVEL_MAP.length) {
            return 1;  // wall if out of bounds
        }
        return LEVEL_MAP[LEVEL_MAP.length - y - 1][x];
    }

    /**
     * Check if the given (x, y) is a wall.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return true if a wall, false otherwise
     */
    public boolean isWall(double x, double y) {
        return getTile(x, y) == 1;
    }

    /**
     * Check if the given (x, y) is a dot.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return true if a dot, false otherwise
     */
    public boolean isDot(double x, double y) {
        return getTile(x, y) == 2;
    }

    /**
     * Check if the given (x, y) is an egg.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return true if an egg, false otherwise
     */
    public boolean isEgg(double x, double y) {
        return getTile(x, y) == 3;
    }

    public int getHeight() {
        return LEVEL_MAP.length;
    }

    public int getWidth() {
        return LEVEL_MAP[0].length;
    }

    @Override
    public void draw() {
        double width = LEVEL_MAP[0].length * gridPixelSize;
        double height = LEVEL_MAP.length * gridPixelSize;
        StdDraw.picture(width / 2, height / 2, "hws/hw8/img/level.png",
                width, height);
    }
}
