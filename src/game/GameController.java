package game;

import board.BoardGameModel;

/**
 * Controller class responsible for managing the flow of a game session.
 * It handles game initialization, player actions, and game progression.
 */

public class GameController {
    private GameType gameType;
    private GameModel game;
    private BoardGameModel boardGame;

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
        instanceBoardGameModel(game.getVerticalBoardSize(), game.getHorizontalBoardSize());
        boardGame.fillBoard();
        view.displayTitle(gameType);
        choosePlayer();
        while (!boardGame.checkFullBoard() || !boardGame.isWinningBoard(game.getAlignCellsCondition())) {
            view.displayPlayerTurn(game.getCurrentPlayerName());
            movePlayer();
            view.displayBoard(boardGame.getVerticalBoardSize(), boardGame.getHorizontalBoardSize(), boardGame.getBoard());
            if (boardGame.checkFullBoard()) {
                view.displayNoWinner();
                endGameChoices();

                System.exit(0);
            } else if (boardGame.isWinningBoard(game.getAlignCellsCondition())) {
                view.displayWinnerGame(game.getCurrentPlayerName());
                endGameChoices();
            } else {
                game.changeCurrentPlayer();
            }
        }

    }

    private void instanceBoardGameModel(int verticalBoardSize, int horizontalBoardSize) {
        this.boardGame = new BoardGameModel(verticalBoardSize, horizontalBoardSize);
    }

    private void movePlayer() {
        getNewPositionCoordinates();
        if (boardGame.isCellEmpty(game.getCoordinateLine(), game.getCoordinateColumn())) {
            boardGame.updateBoard(game.getCurrentPlayerRepresentation() ,game.getCoordinateLine(), game.getCoordinateColumn());
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
            line = game.getRandomLineIndex(boardGame.getHorizontalBoardSize());
            column = game.getRandomColumnIndex(boardGame.getVerticalBoardSize());
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


    private void endGameChoices(){
        view.displayMenuRestart();
        int choice = getSecureScanner();

        switch (choice){
            case 1:
                Main.main(null);
                break;
            case 2:
                System.exit(0);
                break;
            default:
                view.displayInvalidChoice();
                endGameChoices();
                break;

        }

    }

}
