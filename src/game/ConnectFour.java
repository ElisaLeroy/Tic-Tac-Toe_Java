package game;

import board.Cell;
import board.Coordinates;
import board.State;
import player.ArtificialPlayer;
import player.Player;
import player.RealPlayer;


import java.util.InputMismatchException;
import java.util.Objects;

public class ConnectFour extends BoardGame {

    private final int boardLineNumber;
    private final int boardColumnNumber;
    private Cell[][] board;
    private View view;
    private UserInteraction userInteraction;
    private Player player1;
    private Player player2;
    private int winCondition;
    private boolean win = false;

    public ConnectFour() {
        view = new View();
        this.userInteraction = new UserInteraction();
        this.boardLineNumber = 6;
        this.boardColumnNumber = 7;
        this.setGameType(GameType.CONNECT_FOUR);
        this.board = new Cell[boardColumnNumber][boardLineNumber];
        this.winCondition = 4;
    }


    public void playGame() {
        fillBoard();
        //view.displayGomokuTitle();
        instancePlayer();
        Coordinates coordinates = new Coordinates();
        view.displayPlayersRepresentations();

        while (checkFullBoard() != 0 || !win) {
            player1.getMove(coordinates, boardColumnNumber, boardLineNumber, getGameType());
            movePlayer(coordinates, getGameType(), player1);
            verifyEndConditions(player1, coordinates);

            player2.getMove(coordinates, boardColumnNumber, boardLineNumber, getGameType());
            movePlayer(coordinates, getGameType(), player2);
            verifyEndConditions(player2, coordinates);

            if (win) break;
        }
    }


    public void movePlayer2(Coordinates coordinates, GameType gameType, Player player) {
        int line = coordinates.getLine();
        int column = coordinates.getColumn();

        try {
            if (!Objects.equals(board[line][column].getRepresentation(), "   ")) { //si la case est déjà occupée
                view.displayWrongCell();
                player.getMove(coordinates, boardColumnNumber, boardLineNumber, getGameType());
                movePlayer(coordinates, gameType, player); //on relance le la partie


            } else { //si la case est vide
                Cell cell = board[line][column]; //ici, on vise la cellule seléctionnée par l'utilisateur
                cell.setState(player.getState()); //on attribut
                view.displayBoard(boardColumnNumber, boardLineNumber, board); //on affiche la grille
            }
        } catch (Exception e) {
            System.out.println("Invalid choice, please try again");
            movePlayer(coordinates, gameType, player);
        }
    }


    public void movePlayer(Coordinates coordinates, GameType gameType, Player player) {
        int column = coordinates.getColumn();
        int tokenPositionLine = coordinates.getColumn();
        boolean truc = true;

        //ce que je veu :
        //si la position suivante existe && si la position suivante est vide
            // alors tokenPosition += 1;
        //sinon
            //truc = false
        //

        //si tokePosition existe && est disponible
            //on change le state de la cellule par le state du player




        try {
            while (truc) {
                if (tokenPositionLine + 1 > 0
                        && tokenPositionLine + 1 < boardLineNumber
                        && board[column][tokenPositionLine + 1].getState() == State.EMPTY) {

                    tokenPositionLine += 1; //on descend le pion d'une ligne
                }
                else {
                    truc = false;
                }


            }
            if (tokenPositionLine > 0
                    && tokenPositionLine <= boardLineNumber
                    && board[column][tokenPositionLine].getState() == State.EMPTY) {
                board[column][tokenPositionLine].setState(player.getState());
                view.displayBoard(boardLineNumber,boardColumnNumber, board);
            }
            else{
                System.out.println("case indisponible");
            }

            //print le playerRepresentation à board[tokenposition][tokenPositionLine]

             //on affiche la grille

        } catch (Exception e) {
            System.out.println("Invalid choice, please try again");
            movePlayer(coordinates, gameType, player);
        }
    }


    private void verifyEndConditions(Player player, Coordinates coordinates) {

        if (isWinning()) {
            view.displayWinnerGame(player);
            System.exit(0);
        }
        checkFullBoard();
    }

    private void fillBoard() {
        for (int i = 0; i < boardColumnNumber; i++) {
            for (int j = 0; j < boardLineNumber; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    private void instancePlayer() {
        int choicePlayer1 = choicePlayer("player1");
        int choicePlayer2 = choicePlayer("player2");
        switch (choicePlayer1) {
            case 1:
                this.player1 = new RealPlayer(State.X, "Player 1");
                break;
            case 2:
                this.player1 = new ArtificialPlayer(State.X, "Player 1");
                break;
            default:
                view.displayInvalidChoice();
                instancePlayer();
                break;
        }

        switch (choicePlayer2) {

            case 1:
                this.player2 = new RealPlayer(State.O, "Player 2");
                break;
            case 2:
                this.player2 = new ArtificialPlayer(State.O, "Player 2");
                break;
            default:
                view.displayInvalidChoice();
                instancePlayer();
                break;
        }
    }

    private int choicePlayer(String player) throws InputMismatchException {
        int choice = 0;
        while (choice == 0) {
            try {
                view.displayMenuPlayerChoice(player);
                choice = userInteraction.scannerInt();
            } catch (Exception e) {
                view.displayInvalidChoice();
            }
        }
        return choice;
    }

    private int checkFullBoard() {
        int voidCell = 0;
        for (int i = 0; i < boardColumnNumber; i++) {
            for (int j = 0; j < boardLineNumber; j++) {
                if (board[i][j].getRepresentation().equals("   ")) {
                    voidCell += 1;
                }
            }
        }
        if (voidCell == 0) {
            view.displayEndGame();
            System.exit(0);
        }
        return voidCell;
    }

    private boolean isWinning() {

        for (int i = 0; i < boardColumnNumber; i++) {
            for (int j = 0; j < boardLineNumber; j++) {
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
        for (int k = 1; k < winCondition; k++) {
            if (j + k >= 0
                    && j + k < boardColumnNumber
                    && (board[i][j].getState() == board[i][j + k].getState())) {
                counter += 1;
            }
        }
        if (counter == winCondition - 1) {
            return true;
        }
        return false;
    }

    private boolean checkColumn(int i, int j) {
        int counter = 0;
        for (int k = 1; k < winCondition; k++) {
            if (i + k >= 0
                    && i + k < boardLineNumber
                    && (board[i][j].getState() == board[i + k][j].getState())) {
                counter += 1;
            }
        }
        if (counter == winCondition - 1) {
            return true;
        }
        return false;
    }

    private boolean checkFirstDiagonal(int i, int j) {
        int counter = 0;
        for (int k = 1; k < winCondition; k++) {
            if (j - k >= 0
                    && j - k < boardLineNumber
                    && i + k >= 0
                    && i + k < boardColumnNumber
                    && (board[i][j].getState() == board[i + k][j - k].getState())) {
                counter += 1;
            }
        }
        if (counter == winCondition - 1) {
            return true;
        }
        return false;
    }

    private boolean checkSecondDiagonal(int i, int j) {
        int counter = 0;
        for (int k = 1; k < winCondition; k++) {
            if (j - k >= 0
                    && j - k < boardLineNumber
                    && i - k >= 0
                    && i - k < boardColumnNumber
                    && (board[i][j].getState() == board[i - k][j - k].getState())) {
                counter += 1;
            }
        }
        if (counter == winCondition - 1) {
            return true;
        }
        return false;
    }
}
