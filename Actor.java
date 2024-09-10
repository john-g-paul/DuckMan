package hws.hw8;

/**
 * An entity that can appear on the game screen and interact with other game
 * elements such as players, enemies, or environmental objects.
 *
 * @author CS159 Faculty
 * @version 03/25/2024
 */
public abstract class Actor implements Drawable, Updatable {

    protected Level levelData;
    protected Point currentPosition; // Represents world position (grid-based)
    protected Direction desiredDirection;

    private Point startPosition;
    private Direction startDirection;
    private Direction currentDirection;
    private double movementSpeed;
    private boolean stopped;

    /**
     * Construct an Actor within a Level.
     *
     * @param levelData the Level data
     * @param startPosition the initial position
     * @param startDirection the initial direction
     * @param movementSpeed the initial speed
     */
    public Actor(Level levelData, Point startPosition, Direction startDirection,
            double movementSpeed) {
        this.levelData = levelData;
        this.startPosition = new Point(startPosition);
        this.startDirection = startDirection;
        this.movementSpeed = movementSpeed;
        reset();
    }

    public boolean isStopped() {
        return stopped;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public Direction getDesiredDirection() {
        return desiredDirection;
    }

    public void setDesiredDirection(Direction direction) {
        this.desiredDirection = direction;
    }

    public Point getCurrentPosition() {
        return currentPosition;
    }

    /**
     * Determine whether this Actor is at the same position as another Actor.
     *
     * @param other the other Actor
     * @return true if collision, false otherwise
     */
    public boolean collidesWith(Actor other) {
        // Use square collision
        Point difference = currentPosition.difference(other.currentPosition);
        boolean xOverlap = Math.abs(difference.getX()) < .5;
        boolean yOverlap = Math.abs(difference.getY()) < .5;
        return xOverlap && yOverlap;
    }

    @Override
    public void update() {
        if (!stopped) {
            // Move forward
            currentPosition.translate(
                currentDirection.getDx() * movementSpeed,
                currentDirection.getDy() * movementSpeed);
        }

        if (isTouchingWall(currentDirection)) {
            snapToGrid();
            stopped = true;
        }

        if (desiredDirection != currentDirection) {
            if (desiredDirection == currentDirection.getOpposite()) {
                currentDirection = desiredDirection;
                this.stopped = false;
            } else if (canMove(desiredDirection)) {
                snapToGrid();
                currentDirection = desiredDirection;
                this.stopped = false;
            }
        }
    }

    /**
     * Resets the Actor to the starting position and direction.
     */
    protected void reset() {
        this.currentPosition = new Point(startPosition);
        this.currentDirection = startDirection;
        this.desiredDirection = startDirection;
        stopped = false;
    }

    /**
     * Determine whether the Actor is touching a wall.
     *
     * @param direction the direction to the wall from the Actor
     * @return true if touching the Wall, false otherwise
     */
    protected boolean isTouchingWall(Direction direction) {
        Point testPoint = new Point(
                currentPosition.getX() + direction.getDx() * 0.501,
                currentPosition.getY() + direction.getDy() * 0.501);
        return levelData.isWall(testPoint.getX(), testPoint.getY());
    }

    /**
     * Determine whether the Actor is in the center of a tile.
     *
     * @return true if centered, false otherwise
     */
    protected boolean isCenteredOnGrid() {
        Point center = new Point(
                (int) currentPosition.getX() + 0.5,
                (int) currentPosition.getY() + 0.5);
        return currentPosition.distance(center) < movementSpeed / 2;
    }

    /**
     * Determine whether the Actor can move in a direction.
     *
     * @param direction the desired direction to move
     * @return true if can move, false otherwise
     */
    protected boolean canMove(Direction direction) {
        if (isCenteredOnGrid()) {
            // Check if the tile in the desired direction is not a wall
            return !levelData.isWall(
                    currentPosition.getX() + direction.getDx(),
                    currentPosition.getY() + direction.getDy());
        }
        return false;
    }

    /**
     * Move the Actor to the center of the current position.
     */
    private void snapToGrid() {
        int gridX = (int) currentPosition.getX();
        int gridY = (int) currentPosition.getY();
        currentPosition.setValues(gridX + 0.5, gridY + 0.5);
    }
}
