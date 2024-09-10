package hws.hw8;

import edu.princeton.cs.introcs.StdDraw;

/**
 * A number displayed on the screen.
 *
 * @author CS159 Faculty
 * @version 03/25/2024
 */
public class NumericDisplay implements Drawable {

    private Point position;
    private String text;
    private int value;

    /**
     * Explicit value constructor.
     *
     * @param position the position
     * @param text the text label
     * @param initialValue the initial value
     */
    public NumericDisplay(Point position, String text, int initialValue) {
        this.position = new Point(position);
        this.text = text;
        this.value = initialValue;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void draw() {
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.textLeft(position.getX(), position.getY(), text + ": " + value);
    }
}
