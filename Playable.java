package hws.hw8;

/**
 * A game that can be played.
 *
 * @author CS159 Faculty
 * @version 03/25/2024
 */
public interface Playable {

    /**
     * Start the game.
     */
    void startGame();

    /**
     * Update all objects in the game.
     */
    void updateAll();

    /**
     * Draw all objects in the game.
     */
    void drawAll();

}
