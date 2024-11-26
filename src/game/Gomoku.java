package game;

import board.Cell;


public class Gomoku extends GameController {


    public Gomoku() {
        this.setGameType(GameType.TIC_TAC_TOE);
        this.setAlignCellsCondition(5);
        this.setHorizontalBoardSize(15);
        this.setVerticalBoardSize(15);
        this.setBoard(new Cell[this.getHorizontalBoardSize()][this.getVerticalBoardSize()]);
    }




}
