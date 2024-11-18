import java.lang.reflect.Array;
import java.util.Objects;
import java.util.Scanner;
import java.util.List;


/**
 *
 */
public class TicTacToe {
    private final int size;
    private Cell[][] board;
    private Player player;
    private Menu menu;
    private boolean win = true;

    public TicTacToe() {
        menu = new Menu();
        player = new Player();
        this.size = 3;
        this.board = new Cell[size][size];
    }

    public void play() {
        fillBoard();
        display();
        Coordinates coordinates = new Coordinates();
        while (win) {
            setOwner(player, getMoveFromPlayer(coordinates));
        }
    }

    private void fillBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    private void display() {
        for (int i = 0; i < size; i++) {
            System.out.print("\n-------------\n|");
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j].getRepresentation() + "|");
            }
        }
    }

    private Coordinates getMoveFromPlayer(Coordinates coordinates) {

        int line = menu.menuChoiceLine() - 1;
        int column = menu.menuChoiceColumn() - 1;
        coordinates.setLine(line);
        coordinates.setColumn(column);
        return coordinates;

    }

    private void setOwner(Player player, Coordinates coordinates) {
        int line = coordinates.getLine();
        int column = coordinates.getColumn();

        try {
            if (!Objects.equals(board[line][column].getRepresentation(), "   ")) {
                menu.displayWrongCell();
            } else {
                Cell cell = board[line][column]; //ici, on vise la cellule seléctionnée par l'utilisateur
                cell.setRepresentation(player.getRepresentation()); //on ne modifie pas la cellule mais ce qu'elle contient (representation)

                display();
            }
        } catch (Exception e) {
            System.out.println("Invalid choice, please try again");
            getMoveFromPlayer(coordinates);
        }
    }



}


