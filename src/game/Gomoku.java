package game;

/**
 * Represents the Gomoku game.
 * In Gomoku, players aim to align five marks (either X or O) in a row on a 15x15 board.
 */
import board.Cell;


public class Gomoku extends GameModel {


    public Gomoku() {
        this.setGameType(GameType.TIC_TAC_TOE);
        this.setAlignCellsCondition(5);
        this.setHorizontalBoardSize(15);
        this.setVerticalBoardSize(15);
        this.setBoard(new Cell[this.getHorizontalBoardSize()][this.getVerticalBoardSize()]);
    }




}
