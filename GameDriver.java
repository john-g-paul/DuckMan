package hws.hw8;

import edu.princeton.cs.introcs.StdDraw;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

/**
 * Driver for game applications that sets up the screen and provides the main
 * game loop.
 *
 * @author CS159 Faculty
 * @version 03/25/2024
 */
public class GameDriver {

    public static final Color SCREEN_COLOR = new Color(0, 0, 0);
    public static final int TARGET_FPS = 30;
    public static final int SCREEN_WIDTH = 544;
    public static final int SCREEN_HEIGHT = 640;

    /**
     * Create a game object and a display screen, and execute the main update
     * and draw loop.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        // Initialize the game screen
        StdDraw.setTitle("Duck-Man");
        StdDraw.setCanvasSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        StdDraw.setXscale(0, SCREEN_WIDTH);
        StdDraw.setYscale(0, SCREEN_HEIGHT);
        StdDraw.enableDoubleBuffering();
        StdDraw.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));

        // Create a game object
        Playable game = new DuckManGame();
        game.startGame();

        // Main game loop
        long lastFrameTime = System.currentTimeMillis();
        while (true) {
            StdDraw.clear(SCREEN_COLOR);
            game.updateAll();
            game.drawAll();
            StdDraw.show();

            // Pause the game to hit the target FPS
            long currentFrameTime = System.currentTimeMillis();
            long elapsedTime = currentFrameTime - lastFrameTime;
            lastFrameTime = currentFrameTime;
            StdDraw.pause((int) Math.max(0, 1000 / TARGET_FPS - elapsedTime));
        }
    }

    /**
     * Wrapper for StdDraw.picture() that uses grid instead of pixel coords.
     *
     * @param center the center position of the image, in grid coordinates
     * @param gridSize the size of a grid cell in pixels
     * @param filename the path to the image/picture, e.g., "ball.gif"
     */
    public static void picture(Point center, int gridSize, String filename) {
        StdDraw.picture(center.getX() * gridSize, center.getY() * gridSize,
            filename, gridSize, gridSize);
    }

    /**
     * Wrapper for StdDraw.isKeyPressed().
     *
     * @return true if the up key is being pressed
     */
    public static boolean upPressed() {
        return StdDraw.isKeyPressed(KeyEvent.VK_UP);
    }

    /**
     * Wrapper for StdDraw.isKeyPressed().
     *
     * @return true if the down key is being pressed
     */
    public static boolean downPressed() {
        return StdDraw.isKeyPressed(KeyEvent.VK_DOWN);
    }

    /**
     * Wrapper for StdDraw.isKeyPressed().
     *
     * @return true if the left key is being pressed
     */
    public static boolean leftPressed() {
        return StdDraw.isKeyPressed(KeyEvent.VK_LEFT);
    }

    /**
     * Wrapper for StdDraw.isKeyPressed().
     *
     * @return true if the right key is being pressed
     */
    public static boolean rightPressed() {
        return StdDraw.isKeyPressed(KeyEvent.VK_RIGHT);
    }
}
