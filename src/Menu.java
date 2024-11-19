import java.sql.SQLOutput;
import java.util.Scanner;

public class Menu {

    public void displayTittle(){
        System.out.println(
                                """
                                 _____ _        _____             _____          \s
                                /__   (_) ___  /__   \\__ _  ___  /__   \\___   ___\s
                                  / /\\/ |/ __|   / /\\/ _` |/ __|   / /\\/ _ \\ / _ \\
                                 / /  | | (__   / / | (_| | (__   / / | (_) |  __/
                                 \\/   |_|\\___|  \\/   \\__,_|\\___|  \\/   \\___/ \\___|\n
                                """
        );
    }

    public void displayPlayersRepresentations(Player player1, Player player2){
        System.out.println("""
                \n
                Player 1 -> X
                Player 2 -> O
                """);
    }

    public int menuChoiceLine(){
        Scanner choiceLine = new Scanner(System.in);
        System.out.println("""
                Choose the line
                [1] First line
                [2] Second line
                [3] Third line
                """);

        return choiceLine.nextInt();
    }

    public void displayPlayerNameTurn(Player player){
        System.out.println("\n\n*-------------*\n "+player.getName() + " turn\n"+"*-------------*\n");
    }
    public int menuChoiceColumn(){
        Scanner choiceLine = new Scanner(System.in);
        System.out.println("""
                
                Choose the column
                [1] First column
                [2] Second column
                [3] Third column
                """);
        return choiceLine.nextInt();
    }

    public void displayWrongCell(){
        System.out.println("This cell is not empty, please try again");
    }

    public void displayEndGame() {
        System.out.println("\n\n*-------------*" +
                "\n End game \n" +
                "There's no winner..." +
                "*-------------*\n");
    }

    public void displayWinnerGame(Player player){
        System.out.println(
                """  
                        \n\n                                      _                     _\s
                        \\ \\   / (_) ___| |_ ___  _ __ _   _  | |
                         \\ \\ / /| |/ __| __/ _ \\| '__| | | | | |
                          \\ V / | | (__| || (_) | |  | |_| | |_|
                           \\_/  |_|\\___|\\__\\___/|_|   \\__, | (_)
                                                      |___/    \s
                        """
        );
        System.out.println(player.getName() + " is the winner");
    }







}

