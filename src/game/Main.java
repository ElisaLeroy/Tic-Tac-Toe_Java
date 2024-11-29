package game;

/**
 * Main
 *
 * Entry point for the game application.
 * Initializes the ApplicationController and starts the game.
 *
 * @see ApplicationController
 */

public class Main {
    public static void main(String[] args) {
        ApplicationController application = new ApplicationController();
        application.chooseGame();

    }
}