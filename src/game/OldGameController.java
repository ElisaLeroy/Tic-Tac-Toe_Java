//package game;
//
//import board.Cell;
//import board.Coordinates;
//import board.State;
//import player.ArtificialPlayer;
//import player.Player;
//import player.RealPlayer;
//
//import java.util.InputMismatchException;
//
//public abstract class OldGameController {
//    private GameType gameType;
//    private int verticalBoardSize;
//    private int horizontalBoardSize;
//    private int alignCellsCondition;
//    private boolean win = false;
//
//    private Cell[][] board;
//    private View view;
//    private UserInteraction userInteraction;
//    private Player player1;
//    private Player player2;
//    private OldGameModel gameModel;
//
//    public OldGameController() {
//        this.win = false;
//        this.view = new View();
//        this.userInteraction = new UserInteraction();
//        this.gameModel = new OldGameModel(horizontalBoardSize, verticalBoardSize, alignCellsCondition, board);
//
//    }
//
//
//    public void playGame() {
//        fillBoard();
//        view.displayTicTacToeTitle();
//        instancePlayer();
//        Coordinates coordinates = new Coordinates();
//        view.displayPlayersRepresentations();
//
//        while (checkFullBoard() != 0 || !win) {
//
//            player1.getMove(coordinates, horizontalBoardSize, verticalBoardSize, getGameType());
//            movePlayer(coordinates, getGameType(), player1);
//            verifyEndConditions(player1);
//
//            player2.getMove(coordinates, horizontalBoardSize, verticalBoardSize, getGameType());
//            movePlayer(coordinates, getGameType(), player2);
//            verifyEndConditions(player2);
//
//            if (win) break;
//        }
//    }
//
//    private void verifyEndConditions(Player player) {
//        if (gameModel.isWinning(board)) {
//            view.displayWinnerGame(player);
//            System.exit(0);   /// pas de system.exit sauf pour les thread
//        }
//        checkFullBoard();
//    }
//
//    public void movePlayer(Coordinates coordinates, GameType gameType, Player player) {
//        int line = coordinates.getLine();
//        int column = coordinates.getColumn();
//
//        try {
//            if (gameModel.isCellValid(line, column, board)) {
////                board[line][column].setState(player.getState());
//                gameModel.applyPlayerMove(line, column, player.getState(), board);
//                view.displayBoard(verticalBoardSize, horizontalBoardSize, board);
//            }
//            else {
//                view.displayWrongCell();
//                player.getMove(coordinates,horizontalBoardSize, verticalBoardSize, getGameType());
//                movePlayer(coordinates, gameType, player);
//            }
//
//
//        } catch (Exception e) {
//            System.out.println("Invalid choice, please try again");
//            movePlayer(coordinates, gameType, player);
//        }
//    }
//
//    private void fillBoard() {
//        for (int i = 0; i < horizontalBoardSize; i++) {
//            for (int j = 0; j < verticalBoardSize; j++) {
//                board[i][j] = new Cell();
//            }
//        }
//    }
//
//    private void instancePlayer() {
//        int choicePlayer1 = choicePlayer("player1");
//        int choicePlayer2 = choicePlayer("player2");
//        switch (choicePlayer1) {
//            case 1:
//                this.player1 = new RealPlayer(State.X, "Player 1");
//                break;
//            case 2:
//                this.player1 = new ArtificialPlayer(State.X, "Player 1");
//                break;
//            default:
//                view.displayInvalidChoice();
//                instancePlayer();
//                break;
//        }
//
//        switch (choicePlayer2) {
//
//            case 1:
//                this.player2 = new RealPlayer(State.O, "Player 2");
//                break;
//            case 2:
//                this.player2 = new ArtificialPlayer(State.O, "Player 2");
//                break;
//            default:
//                view.displayInvalidChoice();
//                instancePlayer();
//                break;
//        }
//    }
//
//    private int choicePlayer(String player) throws InputMismatchException {
//        int choice = 0;
//        while (choice == 0) {
//            try {
//                view.displayMenuPlayerChoice(player);
//                choice = userInteraction.scannerInt();
//            } catch (Exception e) {
//                view.displayInvalidChoice();
//            }
//        }
//        return choice;
//    }
//
//    private int checkFullBoard() {
//        int voidCell = 0;
//        for (int i = 0; i < horizontalBoardSize; i++) {
//            for (int j = 0; j < verticalBoardSize; j++) {
//                if (board[i][j].getRepresentation().equals("   ")) {
//                    voidCell += 1;
//                }
//            }
//        }
//        if (voidCell == 0) {
//            view.displayEndGame();
//            System.exit(0);
//        }
//        return voidCell;
//    }
//
////    private boolean isWinning() {
////
////        for (int i = 0; i < horizontalBoardSize; i++) {
////            for (int j = 0; j < verticalBoardSize; j++) {
////                if (board[i][j].getState() != State.EMPTY) {
////
////                    if (checkLine(i, j)) {
////                        return true;
////                    }
////
////                    if (checkColumn(i, j)) {
////                        return true;
////                    }
////
////                    if (checkFirstDiagonal(i, j)) {
////                        return true;
////                    }
////                    if (checkSecondDiagonal(i, j)) {
////                        return true;
////                    }
////
////
////                }
////            }
////        }
////
////        return false;
////    }
////
////    private boolean checkLine(int i, int j) {
////        int counter = 0;
////        for (int k = 1; k < alignCellsCondition; k++) {
////            if (    j + k >= 0
////                    && j + k < horizontalBoardSize
////                    && (board[i][j].getState() == board[i][j + k].getState())) {
////                counter += 1;
////            }
////        }
////        if (counter == alignCellsCondition -1) {
////            return true;
////        }
////        return false;
////    }
////
////    private boolean checkColumn(int i, int j) {
////        int counter = 0;
////        for (int k = 1; k < alignCellsCondition; k++) {
////            if (    i + k >= 0
////                    && i + k < verticalBoardSize
////                    && (board[i][j].getState() == board[i+k][j].getState())) {
////                counter += 1;
////            }
////        }
////        if (counter == alignCellsCondition -1) {
////            return true;
////        }
////        return false;
////    }
////
////    private boolean checkFirstDiagonal(int i, int j) {
////        int counter = 0;
////        for (int k = 1; k < alignCellsCondition; k++) {
////            if (    j - k >= 0
////                    && j - k < verticalBoardSize
////                    && i + k >=0
////                    && i + k < horizontalBoardSize
////                    && (board[i][j].getState() == board[i+k][j-k].getState())) {
////                counter += 1;
////            }
////        }
////        if (counter == alignCellsCondition -1) {
////            return true;
////        }
////        return false;
////    }
////
////    private boolean checkSecondDiagonal(int i, int j) {
////        int counter = 0;
////        for (int k = 1; k < alignCellsCondition; k++) {
////            if (    j - k >= 0
////                    && j - k < verticalBoardSize
////                    && i - k >=0
////                    && i - k < horizontalBoardSize
////                    && (board[i][j].getState() == board[i-k][j-k].getState())) {
////                counter += 1;
////            }
////        }
////        if (counter == alignCellsCondition -1) {
////            return true;
////        }
////        return false;
////    }
//
//
//    public GameType getGameType() {
//        return gameType;
//    }
//
//    public void setGameType(GameType gameType) {
//        this.gameType = gameType;
//    }
//
//    public void setVerticalBoardSize(int verticalBoardSize) {
//        this.verticalBoardSize = verticalBoardSize;
//    }
//
//    public int getVerticalBoardSize() {
//        return verticalBoardSize;
//    }
//
//    public void setHorizontalBoardSize(int horizontalBoardSize) {
//        this.horizontalBoardSize = horizontalBoardSize;
//    }
//
//    public int getHorizontalBoardSize() {
//        return horizontalBoardSize;
//    }
//
//    public void setAlignCellsCondition(int alignCellsCondition) {
//        this.alignCellsCondition = alignCellsCondition;
//    }
//
//    public int getAlignCellsCondition() {
//        return alignCellsCondition;
//    }
//
//    public void setBoard(Cell[][] board) {
//        this.board = board;
//    }
//}
