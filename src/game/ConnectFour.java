package game;

import board.Cell;

public class ConnectFour extends GameModel{

    public ConnectFour() {
        this.setGameType(GameType.CONNECT_FOUR);
        this.setAlignCellsCondition(4);
        this.setHorizontalBoardSize(6);
        this.setVerticalBoardSize(7);
        this.setBoard(new Cell[this.getHorizontalBoardSize()][this.getVerticalBoardSize()]);
    }
}
