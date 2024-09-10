package hws.hw8;

/**
 * Wander enemy class.
 *
 * @author John Gilbert Paul IV
 * @version 04/7/2024
 */
public class WanderEnemy extends Enemy {
    public static final int RELEASE_TIME = 180;

    /**
     * Create Wander enemy.
     *
     * @param levelData data from level
     * @param spawnLocation spawn location of wander enemy
     */
    public WanderEnemy(Level levelData, Point spawnLocation) {
        super(levelData, spawnLocation, RELEASE_TIME);
    }

    @Override
    public void update() {
        if (this.isCenteredOnGrid()) {
            this.setDesiredDirection(this.getCurrentDirection().
                getRandomTurn());
        }
        super.update();
    }

    @Override
    public void draw() {
        String imagePath = "hws/hw8/img/brackets";
        if (this.isEdible()) {
            imagePath += "_scared";
        }
        imagePath += ".png";
        DuckManGame.drawImage(this.getCurrentPosition(), imagePath);
    }
}
