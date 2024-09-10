package hws.hw8;

/**
 * Enemy class.
 *
 * @author John Gilbert Paul IV
 * @version 04/7/2024
 */
public abstract class Enemy extends Actor {
    public static final double ENEMY_SPEED = 0.06;
    public static final int EDIBLE_DURATION = 300;
    public static final int POINTS = 200;

    protected int timeUntilReleased;
    protected int timeUntilNormal;

    /**
     * Default enemy class.
     *
     * @param levelData data from level
     * @param spawnLocation spawn location of enemy
     * @param timeUntilReleased time until enemy can move
     */
    public Enemy(Level levelData, Point spawnLocation, int timeUntilReleased) {
        super(levelData, spawnLocation, Direction.UP, ENEMY_SPEED);
        this.timeUntilNormal = 0;
        this.timeUntilReleased = timeUntilReleased;
    }

    /**
     * Make enemy edible.
     */
    public void makeEdible() {

        if (this.timeUntilReleased <= 0) {
            this.timeUntilNormal = EDIBLE_DURATION;
            this.setDesiredDirection(this.getCurrentDirection().getOpposite());
        }


    }

    public boolean isEdible() {
        return timeUntilNormal > 0;
    }

    /**
     * reset enemy.
     */
    public void reset() {
        super.reset();
        this.timeUntilNormal = 0;
    }

    /**
     * update enemy.
     */
    public void update() {
        if (timeUntilReleased > 0) {
            timeUntilReleased -= 1;
        } else {
            if (this.isEdible()) {
                timeUntilNormal -= 1;
            }
            if (this.isStopped()) {
                this.setDesiredDirection(this.getCurrentDirection().
                    getRandomTurn());
            }
            super.update();
        }

    }
}
