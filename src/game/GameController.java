package game;

public class GameController {
    private GameType gameType;
    private GameModel game;

    private View view;
    private UserInteraction interaction;


    public GameController(GameType gameType) {
        this.gameType = gameType;
        this.view = new View();
        this.interaction = new UserInteraction();
    }

    public void playGame(){
        instanceGameModel(gameType);
        game.fillBoard();
        choosePlayer();
        while (!game.checkFullBoard()|| !game.isWinning()){
            view.displayPlayerTurn(game.getCurrentPlayerName());
            movePlayer();
            view.displayBoard(game.getVerticalBoardSize(), game.getHorizontalBoardSize(), game.getBoard());
            view.displayBoard(game.getCoordinateLine(), game.getCoordinateColumn(), game.getBoard());
            if(game.checkFullBoard()){
                view.displayNoWinner();
            }
            else if(game.isWinning()){
                view.displayWinnerGame(game.getCurrentPlayerName());
            }
            else{
                game.changeCurrentPlayer();
            }}
    }
    private void movePlayer(){
        getNewPositionCoordinates();
        if(game.isCellValid()){
            game.updateBoard();
        }
        else{
            view.displayInvalidChoice();
            movePlayer();
        }
    }
    private void getNewPositionCoordinates(){
        int line;
        int column;
        if(game.getCurrentPlayerType() == PlayerType.REAL) {
            view.displayMenuChoiceLine(game.getVerticalBoardSize());
            line = getSecureScanner()-1;
            view.displayMenuChoiceColumn(game.getHorizontalBoardSize());
            column = getSecureScanner()-1;

        }else{
            line = game.getRandomLineIndex();
            column = game.getRandomColumnIndex();
        }
        game.setCoordinates(line,column);
    }
    private int getSecureScanner(){
        int choice = 0;
        while (choice == 0) {
            try {
                choice = interaction.scannerInt();
            } catch (Exception e) {
                view.displayInvalidChoice();
            }
        }
        return choice;
    }
    private void choosePlayer(){
        view.displayMenuPlayerChoice("player 1");
        int choice1 = getSecureScanner();
        game.instanceFirstPlayer(choice1);

        view.displayMenuPlayerChoice("player 2");
        int choice2 = getSecureScanner();
        game.instanceSecondPlayer(choice2);



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
