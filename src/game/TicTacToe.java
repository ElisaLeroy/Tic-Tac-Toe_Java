package game;

import board.Cell;
import board.Coordinates;
import board.CellType;
import player.ArtificialPlayer;
import player.Player;
import player.RealPlayer;

import java.util.ArrayList;
import java.util.Arrays;


public class TicTacToe {
    private final int size;
    private Cell[][] board;
    private MenuDisplay menuDisplay;
    Player player1;
    Player player2;
    private boolean win = false;

    public TicTacToe() {
        menuDisplay = new MenuDisplay();
        this.size = 3;
        this.board = new Cell[size][size];
    }

    /**
     * This method run the game by calling other methods
     */
    public void playGame() {
        fillBoard();
        menuDisplay.displayTittle();
        choosePlayer();
        Coordinates coordinates = new Coordinates();
        menuDisplay.displayPlayersRepresentations(player1, player2);

        while (checkFullBoard() != 0 || !win) {
            player1.play((player1).getCoordinates(coordinates), board, size);
            verifyWinConditions(player1, coordinates);

            player2.play((player2).getCoordinates(coordinates), board, size);
            verifyWinConditions(player2, coordinates);

            if(win) break;
        }
    }



    private void verifyWinConditions(Player player, Coordinates coordinates){
        checkFullBoard();
        isWinning(coordinates);

        if(this.win) {
            menuDisplay.displayWinnerGame(player);
            System.exit(0);
        }
    }

    private void fillBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    private void choosePlayer() {
     int choicePlayer1 = menuDisplay.choosePlayer("Player 1");
     int choicePlayer2 = menuDisplay.choosePlayer("Player 2");
     switch (choicePlayer1) {
         case 1:
             this.player1 = new RealPlayer(CellType.X, "Player 1");
             break;
         case 2:
             this.player1 = new ArtificialPlayer(CellType.O, "Player 1");
             break;
         default:
             menuDisplay.displayInvalidChoice();
             choosePlayer();
             break;
     }

     switch (choicePlayer2) {
         case 1:
             this.player2 = new RealPlayer(CellType.X, "Player 2");
             break;
         case 2:
             this.player2 = new ArtificialPlayer(CellType.O, "Player 2");
             break;
         default:
             menuDisplay.displayInvalidChoice();
             choosePlayer();
             break;
     }
    }

    private int checkFullBoard() {
        int voidCell = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].getRepresentation().equals("   ")) {
                    voidCell += 1;
                }
            }
        }
        if (voidCell == 0) {
            menuDisplay.displayEndGame();
            System.exit(0);
        }
        return voidCell;
    }

    private  ArrayList<String> stockFirstDiagonal(){
        ArrayList<String> stockCells = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            stockCells.add(board[i][i].getRepresentation());
        }
        return stockCells;
    }

    private  ArrayList<String> stockSecondDiagonal(){
        ArrayList<String> stockCells = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            stockCells.add(board[i][size - 1 - i].getRepresentation());
        }
        return stockCells;
    }

    private ArrayList<String> stockLineCells(int coordinate) {
        ArrayList<String> stockCells = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            stockCells.add(board[coordinate][i].getRepresentation());
        }
        return stockCells;
    }

    private ArrayList<String> stockColumnCells(int coordinate) {
        ArrayList<String> stockCells = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            stockCells.add(board[i][coordinate].getRepresentation());
        }
        return stockCells;
    }

    private boolean checkEmptyCells(ArrayList<String> array) {
        if (array.contains("   ")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkElementsInArray(ArrayList<String> cells) {
        boolean winCells = false;
        ArrayList<String> listX = new ArrayList<>(Arrays.asList(" X ", " X ", " X "));
        ArrayList<String> listO = new ArrayList<>(Arrays.asList(" O ", " O ", " O "));

        //on compare la liste courante (ligne ou colonne) à l'une des liste modèle
        if (cells.equals(listX) || cells.equals(listO)) {
            winCells = true;
        }
        return winCells;
    }

    private void isWinning(Coordinates coordinates) {
        int coordinateLine = coordinates.getLine();
        int coordinateColumn = coordinates.getColumn();
        ArrayList<String> arrayLine = new ArrayList<>(stockLineCells(coordinateLine));
        ArrayList<String> arrayColumn = new ArrayList<>(stockColumnCells(coordinateColumn));
        ArrayList<String> arrayFirstDiagonal = new ArrayList<>(stockFirstDiagonal());
        ArrayList<String> arraySecondDiagonal = new ArrayList<>(stockSecondDiagonal());


        Boolean line = checkElementsInArray(arrayLine);
        Boolean column = checkElementsInArray(arrayColumn);
        Boolean firstDiagonal = checkElementsInArray(arrayFirstDiagonal);
        Boolean secondDiagonal = checkElementsInArray(arraySecondDiagonal);

        if ((!checkEmptyCells(arrayLine) || !checkEmptyCells(arrayColumn) || !checkEmptyCells(arraySecondDiagonal) || !checkEmptyCells(arrayFirstDiagonal)) && (line || column || firstDiagonal || secondDiagonal))   {
            this.win = true;
        }
    }

}




