package game;

import board.Cell;
import board.State;
import player.Player;

import java.util.Objects;

public class GameModel {
    private int horizontalBoardSize;
    private int verticalBoardSize;
    private Cell[][] board;
    int alignCellsCondition;

    public GameModel(int horizontalBoardSize, int verticalBoardSize, int alignCellsCondition, Cell[][] board) {
        this.horizontalBoardSize = horizontalBoardSize;
        this.verticalBoardSize = verticalBoardSize;
        this.alignCellsCondition = alignCellsCondition;
        this.board = board;
    }


    protected boolean isCellValid(int indexLine, int indexColumn, Cell[][] board2) {
        if (Objects.equals(board2[indexLine][indexColumn].getState(), State.EMPTY)) { //si la case est déjà occupée
            return true;
        }
        else{
            return false;
        }
    }

    protected void applyPlayerMove(int indexLine, int indexColumn, State state, Cell[][] board2){
        board2[indexLine][indexColumn].setState(state);
    }

    protected boolean isWinning() {
        for (int i = 0; i < horizontalBoardSize; i++) {
            for (int j = 0; j < verticalBoardSize; j++) {
                if (board[i][j].getState() != State.EMPTY) {

                    if (checkLine(i, j)) {
                        return true;
                    }

                    if (checkColumn(i, j)) {
                        return true;
                    }

                    if (checkFirstDiagonal(i, j)) {
                        return true;
                    }
                    if (checkSecondDiagonal(i, j)) {
                        return true;
                    }


                }
            }
        }

        return false;
    }

    private boolean checkLine(int i, int j) {
        int counter = 0;
        for (int k = 1; k < alignCellsCondition; k++) {
            if (j + k >= 0
                    && j + k < horizontalBoardSize
                    && (board[i][j].getState() == board[i][j + k].getState())) {
                counter += 1;
            }
        }
        if (counter == alignCellsCondition - 1) {
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


}
