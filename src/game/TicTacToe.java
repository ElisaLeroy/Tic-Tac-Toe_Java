package game;

/**
 * Represents the TicTacToe game.
 * In TicTacToe, players aim to align three marks (either X or O) in a row on a 3x3 board.
 */


import board.Cell;


public class TicTacToe extends GameModel {

    public TicTacToe() {
        this.setGameType(GameType.TIC_TAC_TOE);
        this.setAlignCellsCondition(3);
        this.setHorizontalBoardSize(3);
        this.setVerticalBoardSize(3);
        this.setBoard(new Cell[this.getHorizontalBoardSize()][this.getVerticalBoardSize()]);
    }





}




