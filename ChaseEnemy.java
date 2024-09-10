package hws.hw8;

/**
 * Chase enemy class.
 *
 * @author John Gilbert Paul IV
 * @version 04/7/2024
 */
public class ChaseEnemy extends Enemy {
    public static final int RELEASE_TIME = 0;
    private Player player;


    /**
     * Create a chase enemy.
     *
     * @param levelData data of current level
     * @param spawnLocation spawn location of chase enemy
     * @param player player to follow
     */
    public ChaseEnemy(Level levelData, Point spawnLocation, Player player) {
        super(levelData, spawnLocation, RELEASE_TIME);
        this.player = player;
    }

    @Override
    public void update() {
        Point p1 = player.getCurrentPosition();
        Point point2 = this.getCurrentPosition();
        Point diff = point2.difference(p1);
        double diffX = diff.getX();
        double diffY = diff.getY();
        if (this.isCenteredOnGrid()) {
            if (this.getCurrentDirection().isUpDown()) {
                Direction d = this.getCurrentDirection();
                if (diffX > 0) {
                    d = Direction.LEFT;
                } else if (diffX < 0) {
                    d = Direction.RIGHT;
                }
                this.setDesiredDirection(d);
            } else {
                Direction d = this.getCurrentDirection();
                if (diffY > 0) {
                    d = Direction.DOWN;
                } else if (diffY < 0) {
                    d = Direction.UP;
                }
                this.setDesiredDirection(d);
            }
        }
        super.update();
    }

    @Override
    public void draw() {
        String imagePath = "hws/hw8/img/semicolons";
        if (this.isEdible()) {
            imagePath += "_scared";
        }
        imagePath += ".png";
        DuckManGame.drawImage(this.getCurrentPosition(), imagePath);
    }
}
