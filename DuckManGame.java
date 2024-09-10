package hws.hw8;

import java.util.ArrayList;

/**
 * JMU CS themed Pac-Man clone.
 *
 * @author CS159 Faculty and John Gilbert Paul IV
 * @version 03/25/2024
 */
public class DuckManGame implements Playable {

    public static final int GRID_SIZE = 32;
    public static final Point PLAYER_SPAWN = new Point(8.5, 5.5);
    public static final Point ENEMY_SPAWN = new Point(8.5, 11.5);

    private boolean running;
    private Level levelMap;

    private ArrayList<Drawable> drawables;
    private ArrayList<Updatable> updatables;
    private ArrayList<Dot> dots;
    private ArrayList<Enemy> enemies;

    private NumericDisplay score;
    private NumericDisplay lives;

    private Player player;

    /**
     * Default constructor.
     */
    public DuckManGame() {
        levelMap = new Level(GRID_SIZE);
        drawables = new ArrayList<Drawable>();
        dots = new ArrayList<Dot>();
        updatables = new ArrayList<Updatable>();
        enemies = new ArrayList<Enemy>();
    }

    /**
     * Draw an image on the screen.
     *
     * @param position where to draw the image
     * @param imagePath path to the image file
     */
    public static void drawImage(Point position, String imagePath) {
        GameDriver.picture(position, GRID_SIZE, imagePath);
    }

    /**
     * Add a dot to the game.
     *
     * @param dot the dot to add
     */
    public void addDot(Dot dot) {
        this.dots.add(dot);
        this.drawables.add(dot);
    }

    /**
     * add enemy to level.
     *
     * @param enemy enemy to add
     */
    public void addEnemy(Enemy enemy) {
        this.drawables.add(enemy);
        this.updatables.add(enemy);
        this.enemies.add(enemy);
    }

    /**
     * Update the game state when the player collides with dots and enemies.
     */
    public void handlePlayerCollisions() {
        ArrayList<Dot> toRemove = new ArrayList<Dot>();
        for (Dot d: this.dots) {
            if (this.player.collidesWith(d)) {
                if (d instanceof MagicEgg) {
                    this.score.setValue(this.score.getValue() + MagicEgg.
                        POINTS);
                    for (Enemy e: this.enemies) {
                        e.makeEdible();
                    }
                } else if (d instanceof Dot) {
                    this.score.setValue(this.score.getValue() + Dot.POINTS);
                }
                toRemove.add(d);
            }

        }
        for (Dot d: toRemove) {
            this.dots.remove(d);
            this.drawables.remove(d);
        }
        if (this.dots.isEmpty()) {
            running = false;
        }


        for (Enemy e: this.enemies) {
            if (this.player.collidesWith(e)) {
                if (e.isEdible()) {
                    this.score.setValue(this.score.getValue() + Enemy.
                        POINTS);
                    e.reset();
                } else {
                    this.lives.setValue(this.lives.getValue() - 1);
                    this.player.reset();
                    if (this.lives.getValue() == 0) {
                        running = false;
                    }
                }
            }
        }

    }

    /**
     * Iterate through the level map and create a new dot/egg object if the
     * map has a dot/egg at that position.
     */
    public void spawnNewDots() {
        for (int x = 0; x < levelMap.getWidth(); x++) {
            for (int y = 0; y < levelMap.getHeight(); y++) {
                if (levelMap.isEgg(x, y)) {
                    Point p = new Point(x + .5, y + .5);
                    MagicEgg d = new MagicEgg(p);
                    addDot(d);
                }
                if (levelMap.isDot(x, y)) {
                    Point p = new Point(x + .5, y + .5);
                    Dot d = new Dot(p);
                    addDot(d);
                }

            }
        }
    }

    /**
     * Create and add a player to the game.
     */
    public void spawnNewPlayer() {
        player = new Player(levelMap, PLAYER_SPAWN);
        this.drawables.add(this.player);
        this.updatables.add(this.player);
    }

    /**
     * Create and add enemies to the game.
     */
    public void spawnNewEnemies() {
        WallEnemy wE = new WallEnemy(levelMap, ENEMY_SPAWN);
        WanderEnemy waE = new WanderEnemy(levelMap, ENEMY_SPAWN);
        ChaseEnemy cE = new ChaseEnemy(levelMap, ENEMY_SPAWN, this.player);
        addEnemy(cE);
        addEnemy(waE);
        addEnemy(wE);

    }

    @Override
    public void startGame() {
        Point upperLeft = new Point(10, GameDriver.SCREEN_HEIGHT - 10);
        score = new NumericDisplay(upperLeft, "Points", 0);
        drawables.add(score);

        Point upperRight = new Point(GameDriver.SCREEN_WIDTH - 80,
                GameDriver.SCREEN_HEIGHT - 10);
        lives = new NumericDisplay(upperRight, "Lives", 3);
        drawables.add(lives);

        spawnNewDots();
        spawnNewPlayer();
        spawnNewEnemies();

        running = true;
    }

    @Override
    public void updateAll() {
        if (!running) {
            return;
        }

        handlePlayerCollisions();

        for (Updatable u: updatables) {
            u.update();
        }
    }

    @Override
    public void drawAll() {
        levelMap.draw();
        for (Drawable drawable : drawables) {
            drawable.draw();
        }
    }
}
