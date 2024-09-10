package hws.hw8;

/**
 * Magic egg class.
 *
 * @author John Gilbert Paul IV
 * @version 04/7/2024
 */
public class MagicEgg extends Dot {
    public static final int POINTS = 50;

    /**
     * Create magic egg.
     *
     * @param position position of magic egg
     */
    public MagicEgg(Point position) {
        super(position);
    }

    @Override
    public void draw() {
        String imagePath = "hws/hw8/img/egg.png";
        DuckManGame.drawImage(super.getPosition(), imagePath);
    }
}
