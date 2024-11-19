import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


public class TicTacToe {
    private final int size;
    private Cell[][] board;
    private Player player1;
    private Player player2;
    private Menu menu;
    private boolean win = false;



    public TicTacToe() {
        menu = new Menu();
        player1 = new Player();
        player2 = new Player();
        this.size = 3;
        this.board = new Cell[size][size];
    }

    /**
     * This method run the game by calling other methods
     */
    public void play() {
        fillBoard();
        menu.displayTittle();
        display();
        Coordinates coordinates = new Coordinates();
        //choix joueur 1
        // choix joueur 2


        //setPlayersRepresentation();
        setPlayersNames();
        menu.displayPlayersRepresentations(player1, player2);
        while (checkFullBoard() != 0 || !win) {


            //pour jouer player.setOwner(fonction qui sera stocker sans sa classe et overrider pour l'ordinateur)
            //puis vérifier la victoire

            setOwner(player1, getMoveFromPlayer(coordinates, player1));
            verifyWinConditions(player1, coordinates);

            setOwner(player2, getMoveFromPlayer(coordinates, player2));
            verifyWinConditions(player2, coordinates);

        }
    }

    private void verifyWinConditions(Player player, Coordinates coordinates){
        checkFullBoard();
        isWinning(coordinates, player);

        if(this.win == true) {
            menu.displayWinnerGame(player);
            System.exit(0);
        }
    }

    private void setPlayersRepresentation() {
        this.player1.setRepresentation(" X ");
        this.player2.setRepresentation(" O ");
    }

    private void setPlayersNames() {
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
            menu.displayEndGame();
            System.exit(0);
        }
        return voidCell;
    }

    private void isWinning(Coordinates coordinates, Player player) {
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

        if (!checkEmptyCells(arrayLine) || !checkEmptyCells(arrayColumn) || !checkEmptyCells(arraySecondDiagonal) || !checkEmptyCells(arrayFirstDiagonal))   {
            if (line || column || firstDiagonal || secondDiagonal) {
                this.win = true;
            }
        }
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


}




