package player;

/**
 * RealPlayer
 * This class represent a player played by the user
 */

import board.Cell;
import board.Coordinates;
import board.State;
import game.UserInteraction;
import game.View;
import java.util.Objects;

public class RealPlayer extends Player {
    View view;
    UserInteraction userInteraction;

    public RealPlayer(State state, String name) {
        this.setName(name);
        this.setState(state);
        this.view = new View();
        this.userInteraction = new UserInteraction();
    }



    public void play(Coordinates coordinates, Cell[][] board, int size ) {
        int line = coordinates.getLine();
        int column = coordinates.getColumn();

        try {
            if (!Objects.equals(board[line][column].getRepresentation(), "   ")) { //si la case est déjà occupée
                view.displayWrongCell(); //affiche message erreur
                play(getCoordinates(coordinates, size), board, size); //on relance le la partie
            } else { //si la case est vide
                Cell cell = board[line][column]; //ici, on vise la cellule seléctionnée par l'utilisateur
                cell.setState(this.getState()); //on attribut
                view.displayBoard(size, board); //on affiche la grille
            }
        } catch (Exception e) {
            System.out.println("Invalid choice, please try again");
            play(getCoordinates(coordinates, size), board, size);
        }
    }

    public Coordinates getCoordinates(Coordinates coordinates, int size) {
        view.displayPlayerNameTurn(this.getName());

        view.displayMenuChoiceLine();
        int line = getPlayerChoice() - 1;

        view.displayMenuChoiceColumn();
        int column = getPlayerChoice() - 1;


        coordinates.setLine(line);
        coordinates.setColumn(column);
        return coordinates;
    }

    private int getPlayerChoice() {
        int choice = 0;
        while (choice == 0) {
            try {
                choice = userInteraction.scannerInt();
            } catch (Exception e) {
                view.displayInvalidChoice();
            }
        }
        return choice;
    }




}
