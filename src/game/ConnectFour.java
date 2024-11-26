package game;

import board.Cell;
import board.Coordinates;
import board.State;
import player.ArtificialPlayer;
import player.Player;
import player.RealPlayer;


import java.util.InputMismatchException;
import java.util.Objects;

public class ConnectFour extends GameController {

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
            player1.getMove(coordinates, boardLineNumber,boardColumnNumber , getGameType());
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
                player.getMove(coordinates,boardColumnNumber, boardLineNumber, getGameType());
                movePlayer(coordinates, gameType, player); //on relance le la partie


            } else { //si la case est vide
                //ici,
                board[line][column].setState(player.getState()); //on vise la cellule seléctionnée par l'utilisateur et on lui attribut le state du player
                view.displayBoard(boardColumnNumber, boardLineNumber, board); //on affiche la grille
            }
        } catch (Exception e) {
            System.out.println("Invalid choice, please try again");
            movePlayer(coordinates, gameType, player);
        }
    }

    public void movePlayer(Coordinates coordinates, GameType gameType, Player player) {
        int column = coordinates.getColumn();
        int positionInColumn = 0;

        try {

            //ici, i représente la ligne de la colonne (on test donc toutes les lignes jusquèà trouver la dernière valide)
            //la case i existe forcément car elle début à 0 et ne dépasse pas la taille de la colonne == donc pas besoin de tester ça
//            for(int i=0; i <boardLineNumber; i++){
//                if(board[column][i].getState() == State.EMPTY){ //si la case courante est vide on stock son index
//                    positionInColumn = i;
//                }else{
//                    break;
//                }
//            }
//            if (positionInColumn >= 0
//                        && positionInColumn < boardLineNumber
//                        && board[column][positionInColumn ].getState() == State.EMPTY) {
//
//                board[positionInColumn][column].setState(player.getState());
//                view.displayBoard(boardColumnNumber,boardLineNumber, board);
//            }else{
//                System.out.println("non.");
//            }



            //TODO: Check si la colonne choisie à une place, si oui on part du bas d ela colonne et on met le pion dans la première case vide qu'on trouve

        } catch (Exception e) {
            System.out.println("non 2");
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
        for (int i = 0; i <boardLineNumber ; i++) {
            for (int j = 0; j < boardColumnNumber; j++) {
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
