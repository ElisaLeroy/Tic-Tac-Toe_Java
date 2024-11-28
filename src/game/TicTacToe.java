package game;

/**
 * TicTacToe
 * This class contains Tic tac toe game rules and methods that run the game
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




