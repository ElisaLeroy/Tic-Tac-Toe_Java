package game;

import board.Cell;
import board.Coordinates;
import board.State;
import player.ArtificialPlayer;
import player.Player;
import player.RealPlayer;

import java.security.SecureRandom;
import java.util.Objects;

public abstract class GameModel {
    private GameType gameType;
    private int verticalBoardSize;
    private int horizontalBoardSize;
    private int alignCellsCondition;
    private boolean win = false;

    private Cell[][] board;
    Coordinates coordinates;
    private Player player1;
    private Player player2;
    private Player currentPlayer;


    public GameModel() {
    }

    protected void fillBoard() {
        for (int i = 0; i < horizontalBoardSize; i++) {
            for (int j = 0; j < verticalBoardSize; j++) {
                board[i][j] = new Cell();
            }
        }
    }
    protected void instanceFirstPlayer(int type) {
        switch (type) {
            case 1:
                this.player1 = new RealPlayer(State.X, "Player 1"); ////////////
                break;
            case 2:
                this.player1 = new ArtificialPlayer(State.X, "Player 1"); ///////////////
                break;
        }
    }
    protected void instanceSecondPlayer(int type) {
        switch (type) {
            case 1:
                this.player2 = new RealPlayer(State.O, "Player 2"); ////////////
                break;
            case 2:
                this.player2 = new ArtificialPlayer(State.O, "Player 2"); ///////////////
                break;
        }
    }
    protected boolean checkFullBoard() {
        int voidCell = 0;
        for (int i = 0; i < horizontalBoardSize; i++) {
            for (int j = 0; j < verticalBoardSize; j++) {
                if (board[i][j].getRepresentation().equals("   ")) {
                    voidCell += 1;
                }
            }
        }
        if (voidCell == 0) {
            return true;
        } else {
            return false;
        }
    }  ////
    protected void setCoordinates(int line, int column) {
        this.coordinates = new Coordinates(line, column);
        ;
    }
    protected PlayerType getCurrentPlayerType() {
        return currentPlayer.getType();
    }
    protected int getRandomColumnIndex() {
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt(verticalBoardSize);
    }
    protected int getRandomLineIndex() {
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt(horizontalBoardSize);
    }
    protected void updateBoard() {
        this.board[coordinates.getLine()][coordinates.getColumn()].setState(currentPlayer.getState());
    }
    protected boolean isCellValid() {
        if (Objects.equals(this.board[coordinates.getLine()][coordinates.getColumn()].getState(), State.EMPTY)) {
            return true;
        } else {
            return false;
        }
    }
    protected boolean isWinning() {
        for (int i = 0; i < horizontalBoardSize; i++) {
            for (int j = 0; j < verticalBoardSize; j++) {
                if (board[i][j].getState() != State.EMPTY) {
                    if (checkLine(i, j)
                    || checkColumn(i, j)
                    || checkFirstDiagonal(i, j)
                    || checkSecondDiagonal(i, j)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    protected void changeCurrentPlayer(){
        switch (currentPlayer.getName()) {
            case "Player 1":
                this.currentPlayer = player2;
                break;
            case "Player 2":
                this.currentPlayer = player1;
                break;
        }
    }
    private boolean checkLine(int i, int j) {
        int counter = 0;
        for (int k = 1; k < this.alignCellsCondition; k++) {
            if (j + k >= 0
                    && j + k < horizontalBoardSize
                    && (board[i][j].getState() == board[i][j + k].getState())) {
                counter += 1;
            }
        }
        if (counter == this.alignCellsCondition - 1) {
            return true;
        }
        return false;
    }
    private boolean checkColumn(int i, int j) {
        int counter = 0;
        for (int k = 1; k < alignCellsCondition; k++) {
            if (i + k >= 0
                    && i + k < verticalBoardSize
                    && (board[i][j].getState() == board[i + k][j].getState())) {
                counter += 1;
            }
        }
        if (counter == alignCellsCondition - 1) {
            return true;
        }
        return false;
    }
    private boolean checkFirstDiagonal(int i, int j) {
        int counter = 0;
        for (int k = 1; k < alignCellsCondition; k++) {
            if (j - k >= 0
                    && j - k < verticalBoardSize
                    && i + k >= 0
                    && i + k < horizontalBoardSize
                    && (board[i][j].getState() == board[i + k][j - k].getState())) {
                counter += 1;
            }
        }
        if (counter == alignCellsCondition - 1) {
            return true;
        }
        return false;
    }
    private boolean checkSecondDiagonal(int i, int j) {
        int counter = 0;
        for (int k = 1; k < alignCellsCondition; k++) {
            if (j - k >= 0
                    && j - k < verticalBoardSize
                    && i - k >= 0
                    && i - k < horizontalBoardSize
                    && (board[i][j].getState() == board[i - k][j - k].getState())) {
                counter += 1;
            }
        }
        if (counter == alignCellsCondition - 1) {
            return true;
        }
        return false;
    }



    public int getCoordinateLine() {
        return coordinates.getLine();
    }
    public int getCoordinateColumn() {
        return coordinates.getColumn();
    }
    public void setVerticalBoardSize(int verticalBoardSize) {
        this.verticalBoardSize = verticalBoardSize;
    }
    public int getVerticalBoardSize() {
        return verticalBoardSize;
    }
    public void setHorizontalBoardSize(int horizontalBoardSize) {
        this.horizontalBoardSize = horizontalBoardSize;
    }
    public int getHorizontalBoardSize() {
        return horizontalBoardSize;
    }
    public void setAlignCellsCondition(int alignCellsCondition) {
        this.alignCellsCondition = alignCellsCondition;
    }
    public int getAlignCellsCondition() {
        return alignCellsCondition;
    }
    public GameType getGameType() {
        return gameType;
    }
    public boolean getWin() {
        return win;
    }
    public void setWin(boolean win) {
        this.win = win;
    }
    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }
    public void setBoard(Cell[][] board) {
        this.board = board;
    }
    public Cell[][] getBoard() {
        return board;
    }
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    public String getCurrentPlayerName() {
        return currentPlayer.getName();
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }
    public Player getPlayer1(){
        return this.player1;
    }
}
