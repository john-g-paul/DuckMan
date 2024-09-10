package hws.hw8;

/**
 * Player class.
 *
 * @author John Gilbert Paul IV
 * @version 04/7/2024
 */
public class Player extends Actor {
    public static final double PLAYER_SPEED = .07;

    /**
     * Create Player.
     *
     * @param level level that player is on
     * @param spawnLocation spawn location of player
     */
    public Player(Level level, Point spawnLocation) {
        super(level, spawnLocation, Direction.LEFT, PLAYER_SPEED);
    }

    @Override
    public void update() {
        Direction d = this.getCurrentDirection();
        if (GameDriver.upPressed()) {
            d = Direction.UP;
        }
        if (GameDriver.downPressed()) {
            d = Direction.DOWN;
        }
        if (GameDriver.leftPressed()) {
            d = Direction.LEFT;
        }
        if (GameDriver.rightPressed()) {
            d = Direction.RIGHT;
        }
        this.setDesiredDirection(d);
        super.update();
    }

    @Override
    public void draw() {
        String imagePath = "hws/hw8/img/duck_";
        if (this.getCurrentDirection() == Direction.LEFT) {
            imagePath += "left";
        }
        if (this.getCurrentDirection() == Direction.RIGHT) {
            imagePath += "right";
        }
        if (this.getCurrentDirection() == Direction.UP) {
            imagePath += "up";
        }
        if (this.getCurrentDirection() == Direction.DOWN) {
            imagePath += "down";
        }
        imagePath += ".png";
        DuckManGame.drawImage(this.getCurrentPosition(), imagePath);
    }

    /**
     * check to see if player collides with *dot*.
     *
     * @param dot dot to see if player collides
     * @return whether the player collided with the dot
     */
    public boolean collidesWith(Dot dot) {
        double x1 = dot.getPosition().getX();
        double x2 = this.getCurrentPosition().getX();
        double y1 = dot.getPosition().getY();
        double y2 = this.getCurrentPosition().getY();
        double sqrdx = Math.pow((x2 - x1), 2);
        double sqrdy = Math.pow((y2 - y1), 2);
        double addi = sqrdx + sqrdy;
        double root = Math.sqrt(addi);
        if (root < 0.5) {
            return true;
        }
        return false;
    }

}
