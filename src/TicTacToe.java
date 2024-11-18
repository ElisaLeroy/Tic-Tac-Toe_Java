import java.util.Objects;


/**
 *
 */
public class TicTacToe {
    private final int size;
    private Cell[][] board;
    private Player player1;
    private Player player2;
    private Menu menu;
    private boolean win = true; //provisoire

    public TicTacToe() {
        menu = new Menu();
        player1 = new Player();
        player2 = new Player();
        this.size = 3;
        this.board = new Cell[size][size];
    }

    public void play() {
        fillBoard();
        display();
        Coordinates coordinates = new Coordinates();
        setPlayersRepresentation();
        setPlayersNames();
        menu.displayPlayersRepresentations(player1, player2);
        while (win) {
            setOwner(player1, getMoveFromPlayer(coordinates, player1));
            setOwner(player2, getMoveFromPlayer(coordinates, player2));
        }
    }

    private void setPlayersRepresentation() {
        this.player1.setRepresentation(" X ");
        this.player2.setRepresentation(" O ");
    }

    private void setPlayersNames(){
        this.player1.setName("Player 1");
        this.player2.setName("Player 2");
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

    private Coordinates getMoveFromPlayer(Coordinates coordinates, Player player) {
        menu.displayPlayerNameTurn(player);
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
                setOwner(player, getMoveFromPlayer(coordinates, player));
            } else {
                Cell cell = board[line][column]; //ici, on vise la cellule seléctionnée par l'utilisateur
                cell.setRepresentation(player.getRepresentation()); //on ne modifie pas la cellule mais ce qu'elle contient (representation)

                display();
            }
        } catch (Exception e) {
            System.out.println("Invalid choice, please try again");
            setOwner(player, getMoveFromPlayer(coordinates, player));
        }
    }



}


