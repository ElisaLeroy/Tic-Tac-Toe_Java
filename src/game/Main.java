package game;

public class Main {
    public static void main(String[] args) {

        GameController game = new GameController(GameType.GOMOKU);
        game.playGame();


    }
}