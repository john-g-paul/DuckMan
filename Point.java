package hws.hw8;

/**
 * A location in (x,y) coordinate space. In contrast to java.awt.Point, this
 * class uses doubles instead of ints.
 *
 * @author CS159 Faculty
 * @version 03/25/2024
 */
public class Point {

    private double x;
    private double y;

    /**
     * Constructs a point at the origin (0, 0).
     */
    public Point() {
        this(0, 0);
    }

    /**
     * Copy constructor.
     *
     * @param other the point to copy
     */
    public Point(Point other) {
        this(other.x, other.y);
    }

    /**
     * Explicit value constructor.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    /**
     * Set both x and y.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void setValues(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Move this point to location (x + dx, y + dy).
     *
     * @param dx the distance to move along the x axis
     * @param dy the distance to move along the y axis
     */
    public void translate(double dx, double dy) {
        x += dx;
        y += dy;
    }

    /**
     * Gets the difference between this and another point.
     *
     * @param other the other point
     * @return the point (x - other.x, y - other.y)
     */
    public Point difference(Point other) {
        return new Point(x - other.x, y - other.y);
    }

    /**
     * Gets the Euclidean distance from this to another point.
     *
     * @param other the other point
     * @return the Euclidean distance
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(other.x - x, 2) + Math.pow(other.y - y, 2));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            Point other = (Point) obj;
            return distance(other) < 0.000001;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("(%.2f, %.2f)", x, y);
    }
}
