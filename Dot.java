package hws.hw8;

/**
 * Dot class.
 *
 * @author John Gilbert Paul IV
 * @version 04/7/2024
 */
public class Dot implements Drawable {
    public static final int POINTS = 10;
    protected Point position;

    /**
     * Create a dot at position.
     *
     * @param position position of point
     */
    public Dot(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return this.position;
    }

    @Override
    public void draw() {
        String imagePath = "hws/hw8/img/dot.png";
        DuckManGame.drawImage(this.position, imagePath);
    }
}
