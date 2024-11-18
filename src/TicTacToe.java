/**
 *
 */
public class TicTacToe {
    private final int size;
    private Cell[][] board;

    public TicTacToe(){
        this.size = 3;
        this.board = new Cell[size][size];
    }

    public void DisplayBoard(){
        fillBoard();
        display();
    }

    private void fillBoard(){
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                board[i][j] = new Cell();
            }
        }
    }

    private void display(){
        for (int i = 0; i < size; i++){
            System.out.print("\n-------------\n|");
            for(int j = 0; j < size; j++){
                System.out.print(board[i][j].getRepresentation()+"|");
            }
        }
    }





}
