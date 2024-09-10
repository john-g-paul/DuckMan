package hws.hw8;

/**
 * Wall enemy class.
 *
 * @author John Gilbert Paul IV
 * @version 04/7/2024
 */
public class WallEnemy extends Enemy {
    public static final int RELEASE_TIME = 360;

    /**
     * Create Wall enemy.
     *
     * @param levelData data from level
     * @param spawnLocation spawn location of wall enemy
     */
    public WallEnemy(Level levelData, Point spawnLocation) {
        super(levelData, spawnLocation, RELEASE_TIME);
    }

    @Override
    public void draw() {
        String imagePath = "hws/hw8/img/parentheses";
        if (this.isEdible()) {
            imagePath += "_scared";
        }
        imagePath += ".png";
        DuckManGame.drawImage(this.getCurrentPosition(), imagePath);
    }
}
