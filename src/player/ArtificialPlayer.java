package player;

import board.Cell;
import board.Coordinates;
import board.CellType;
import game.MenuDisplay;

import java.security.SecureRandom;
import java.util.Objects;

public class ArtificialPlayer extends Player {
    MenuDisplay menuDisplay;

    public ArtificialPlayer(CellType state, String name ){
        this.setName(name);
        this.setState(state);
        this.menuDisplay = new MenuDisplay();
    }



    public void play(Coordinates coordinates, Cell[][] board, int size){
        int line = coordinates.getLine();
        int column = coordinates.getColumn();

        try {
            if (!Objects.equals(board[line][column].getRepresentation(), "   ")) { //si la case est déjà occupée
                menuDisplay.displayWrongCell(); //affiche message erreur
                play(getCoordinates(coordinates), board, size); //on relance le la partie
            } else { //si la case est vide
                Cell cell = board[line][column]; //ici, on vise la cellule seléctionnée par l'utilisateur
                cell.setState(this.getState()); //on ne modifie pas la cellule mais ce qu'elle contient (representation)

                menuDisplay.displayBoard(size, board); //on affiche la grille
            }
        } catch (Exception e) {
            System.out.println("Invalid choice, please try again");
            play(getCoordinates(coordinates), board, size);
        }
    };

    public Coordinates getCoordinates(Coordinates coordinates){
        menuDisplay.displayPlayerNameTurn(this.getName());
        final SecureRandom secureRandom = new SecureRandom();
        int randomLine = secureRandom.nextInt(3);
        int randomColumn = secureRandom.nextInt(3);
        System.out.println(randomLine + " : line - "+ randomColumn + " : column");

        coordinates.setLine(randomLine);
        coordinates.setColumn(randomColumn);

        return coordinates;
    }




}
