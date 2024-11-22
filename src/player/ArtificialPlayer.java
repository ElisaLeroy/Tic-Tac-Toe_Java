package player;

/**
 * ArtificialPlayer
 * This class represent a player played by the computer
 */

import board.Cell;
import board.Coordinates;
import board.State;
import game.GameType;
import game.View;
import java.security.SecureRandom;
import java.util.Objects;

public class ArtificialPlayer extends Player {
    View menuDisplay;

    public ArtificialPlayer(State state, String name ){
        this.setName(name);
        this.setState(state);
        this.menuDisplay = new View();
    }

    public void play(Coordinates coordinates, Cell[][] board, int horizontalSize, int verticalSize, GameType gameType){
        int line = coordinates.getLine();
        int column = coordinates.getColumn();

        try {
            if (!Objects.equals(board[line][column].getRepresentation(), "   ")) { //si la case est déjà occupée
                menuDisplay.displayWrongCell(); //affiche message erreur
                play(getMove(coordinates, horizontalSize, verticalSize, gameType), board, horizontalSize, verticalSize,gameType); //on relance le la partie
            } else { //si la case est vide
                Cell cell = board[line][column]; //ici, on vise la cellule seléctionnée par l'utilisateur
                cell.setState(this.getState()); //on ne modifie pas la cellule mais ce qu'elle contient (representation)

                menuDisplay.displayBoard(horizontalSize, verticalSize, board); //on affiche la grille
            }
        } catch (Exception e) {
            System.out.println("Invalid choice, please try again");
            play(getMove(coordinates, horizontalSize, verticalSize,gameType), board, horizontalSize, verticalSize, gameType);
        }
    }

    public Coordinates getMove(Coordinates coordinates, int horizontalSize, int verticalSize, GameType gameType){
        int randomColumn;
        int randomLine;

        menuDisplay.displayPlayerNameTurn(this.getName());
        final SecureRandom secureRandom = new SecureRandom();
        if (gameType == GameType.CONNECT_FOUR) {
            randomColumn = secureRandom.nextInt(verticalSize);
            coordinates.setColumn(randomColumn);

        }else{
            randomLine = secureRandom.nextInt(horizontalSize);
            randomColumn = secureRandom.nextInt(verticalSize);
            coordinates.setLine(randomLine);
            coordinates.setColumn(randomColumn);
        }


        return coordinates;
    }




}
