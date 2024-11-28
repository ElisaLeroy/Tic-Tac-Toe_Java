package game;

public class Main {
    public static void main(String[] args) {

        GameController game = new GameController(GameType.TIC_TAC_TOE);
        game.playGame();


    }
}