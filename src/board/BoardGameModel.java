package board;

import java.util.Objects;

public class BoardGameModel {
    private int verticalBoardSize;
    private int horizontalBoardSize;

    private Cell[][] board;

    public BoardGameModel(int verticalBoardSize, int horizontalBoardSize) {
        this.verticalBoardSize = verticalBoardSize;
        this.horizontalBoardSize = horizontalBoardSize;
    }



    public void fillBoard() {
        for (int i = 0; i < horizontalBoardSize; i++) {
            for (int j = 0; j < verticalBoardSize; j++) {
                board[i][j] = new Cell();
            }
        }
    }
    public boolean checkFullBoard() {
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

    protected void updateBoard(State currentPlayerState, int line, int column) {
        this.board[line][column].setState(currentPlayerState);
    }
    protected boolean isCellEmpty(int line, int column) {
        if (Objects.equals(this.board[line][column].getState(), State.EMPTY)) {
            return true;
        } else {
            return false;
        }
    }
    private boolean checkLine(int i, int j, int alignCellsCondition) {
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
    private boolean checkColumn(int i, int j, int alignCellsCondition) {
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
    private boolean checkFirstDiagonal(int i, int j, int alignCellsCondition) {
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
    private boolean checkSecondDiagonal(int i, int j, int alignCellsCondition) {
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
    public boolean isWinningBoard(int alignCellsCondition){
        for (int i = 0; i < horizontalBoardSize; i++) {
            for (int j = 0; j < verticalBoardSize; j++) {
                if (board[i][j].getState() != State.EMPTY) {
                    if (checkLine(i, j, alignCellsCondition)
                            || checkColumn(i, j, alignCellsCondition)
                            || checkFirstDiagonal(i, j, alignCellsCondition)
                            || checkSecondDiagonal(i, j, alignCellsCondition)){
                        return true;
                    }
                }
            }
        }
        return false;
    }





    public int getVerticalBoardSize() {
        return verticalBoardSize;
    }
    public int getHorizontalBoardSize() {
        return horizontalBoardSize;
    }
    public Cell[][] getBoard() {
        return board;
    }

    public void setVerticalBoardSize(int verticalBoardSize) {
        verticalBoardSize = verticalBoardSize;
    }
    public void setHorizontalBoardSize(int horizontalBoardSize) {
        horizontalBoardSize = horizontalBoardSize;
    }


    //setBoard

}
