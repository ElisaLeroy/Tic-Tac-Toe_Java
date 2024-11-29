package game;

/**
 * Controller class responsible for managing the flow of a game session.
 * It handles game initialization, player actions, and game progression.
 */

public class GameController {
    private GameType gameType;
    private GameModel game;

    private View view;
    private UserInteraction interaction;

    /**
     * Initializes the GameController with the specified game type.
     * Sets up the view and user interaction components.
     *
     * @param gameType the type of game to be played (Tic Tac Toe or Gomoku).
     */
    public GameController(GameType gameType) {
        this.gameType = gameType;
        this.view = new View();
        this.interaction = new UserInteraction();
    }

    /**
     * Starts the game session by initializing the game model,
     * displaying the title, and prompting players for moves.
     * It continues until there is a winner or the board is full.
     */
    public void playGame() {
        instanceGameModel(gameType);
        game.fillBoard();
        view.displayTitle(gameType);
        choosePlayer();
        while (!game.checkFullBoard() || !game.isWinning()) {
            view.displayPlayerTurn(game.getCurrentPlayerName());
            movePlayer();
            view.displayBoard(game.getVerticalBoardSize(), game.getHorizontalBoardSize(), game.getBoard());
            if (game.checkFullBoard()) {
                view.displayNoWinner();
                System.exit(0);
            } else if (game.isWinning()) {
                view.displayWinnerGame(game.getCurrentPlayerName());
                System.exit(0);
            } else {
                game.changeCurrentPlayer();
            }
        }
    }

    private void movePlayer() {
        getNewPositionCoordinates();
        if (game.isCellEmpty()) {
            game.updateBoard();
        } else {
            view.displayInvalidCell();
            movePlayer();
        }
    }

    private void getNewPositionCoordinates() {
        int line;
        int column;
        if (game.getCurrentPlayerType() == PlayerType.REAL) {
            view.displayMenuChoiceLine(game.getVerticalBoardSize());
            line = getSecureScanner() - 1;
            view.displayMenuChoiceColumn(game.getHorizontalBoardSize());
            column = getSecureScanner() - 1;

        } else {
            line = game.getRandomLineIndex();
            column = game.getRandomColumnIndex();
        }
        game.setCoordinates(line, column);
    }

    private int getSecureScanner() {
        int choice = 0;
        while (choice == 0) {
            try {
                choice = interaction.scannerInt();
            } catch (Exception e) {
                view.displayInvalidCell();
            }
        }
        return choice;
    }

    private void choosePlayer() {
        view.displayMenuPlayerChoice("player 1");
        int choice1 = getSecureScanner();
        game.instanceFirstPlayer(choice1);

        view.displayMenuPlayerChoice("player 2");
        int choice2 = getSecureScanner();
        game.instanceSecondPlayer(choice2);

        game.setCurrentPlayer(game.getPlayer1());

    }

    private void instanceGameModel(GameType gameType) {
        switch (gameType) {
            case TIC_TAC_TOE:
                this.game = new TicTacToe();
                break;
            case GOMOKU:
                this.game = new Gomoku();
                break;
        }
    }


}
