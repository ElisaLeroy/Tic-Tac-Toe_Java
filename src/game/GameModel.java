package game;
/**
 * Abstract class representing the core logic of a game.
 */

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
    /**
     * Fills the game board with empty cells.
     */
    protected void fillBoard() {
        for (int i = 0; i < horizontalBoardSize; i++) {
            for (int j = 0; j < verticalBoardSize; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    /**
     * Initializes the first player based on the selected type (real or artificial).
     *
     * @param type the type of player (1 for real, 2 for artificial).
     */
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

    /**
     * Initializes the second player based on the selected type (real or artificial).
     *
     * @param type the type of player (1 for real, 2 for artificial).
     */
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

    /**
     * Checks if the board is full.
     *
     * @return true if the board is full, false otherwise.
     */
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
    }

    /**
     * Sets the coordinates for the current player's move.
     *
     * @param line the row coordinate of the move.
     * @param column the column coordinate of the move.
     */
    protected void setCoordinates(int line, int column) {
        this.coordinates = new Coordinates(line, column);
        ;
    }

    /**
     * Generates a random column index within the board's bounds.
     *
     * @return a random column index.
     */
    protected int getRandomColumnIndex() {
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt(verticalBoardSize);
    }

    /**
     * Generates a random line index within the board's bounds.
     *
     * @return a random line index.
     */
    protected int getRandomLineIndex() {
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt(horizontalBoardSize);
    }

    /**
     * Updates the board with the current player's state at the specified coordinates.
     */
    protected void updateBoard() {
        this.board[coordinates.getLine()][coordinates.getColumn()].setState(currentPlayer.getState());
    }

    /**
     * Checks if the selected cell is empty.
     *
     * @return true if the cell is empty, false otherwise.
     */
    protected boolean isCellEmpty() {
        if (Objects.equals(this.board[coordinates.getLine()][coordinates.getColumn()].getState(), State.EMPTY)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if there is a winner by evaluating rows, columns, and diagonals.
     *
     * @return true if a player has won, false otherwise.
     */
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

    /**
     * Checks if there is a winning line starting from the specified cell.
     *
     * @param i the row index of the starting cell.
     * @param j the column index of the starting cell.
     * @return true if a winning line is found, false otherwise.
     */
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

    /**
     * Checks if there is a winning column starting from the specified cell.
     *
     * @param i the row index of the starting cell.
     * @param j the column index of the starting cell.
     * @return true if a winning column is found, false otherwise.
     */
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

    /**
     * Checks if there is a winning diagonal (top-left to bottom-right) starting from the specified cell.
     *
     * @param i the row index of the starting cell.
     * @param j the column index of the starting cell.
     * @return true if a winning diagonal is found, false otherwise.
     */
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

    /**
     * Checks if there is a winning diagonal (top-right to bottom-left) starting from the specified cell.
     *
     * @param i the row index of the starting cell.
     * @param j the column index of the starting cell.
     * @return true if a winning diagonal is found, false otherwise.
     */
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

    /**
     * Switches the current player to the next player.
     */
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

    /**
     * Returns the type of the current player (REAL or ARTIFICIAL).
     *
     * @return the current player's type.
     */
    protected PlayerType getCurrentPlayerType() {
        return currentPlayer.getType();
    }





    // Getter and Setter methods for various properties
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
