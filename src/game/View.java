package game;
/**
 * View
 * This class contains all the display part of the game (in console)
 */

import board.Cell;
import player.Player;

public class View {

    /**
     * displayMenuChoosePlayer
     * @param player
     */
    public void displayMenuPlayerChoice(String player) {
        System.out.println("Please choose "+player +
                """
                        
                        [1] Real player 
                        [2] Computer
                        """);
    }

    /**
     * displayMenuChoiceLine
     */
    public void displayMenuChoiceLine(){

        System.out.println("""
                Choose the line
                [1] First line
                [2] Second line
                [3] Third line
                """);
    }

    /**
     * menuChoiceColumn
     */
    public void displayMenuChoiceColumn(){
        System.out.println("""
                
                Choose the column
                [1] First column
                [2] Second column
                [3] Third column
                """);
    }

    /**
     * displayTittle
     */
    public void displayTicTacToeTitle(){
        System.out.println(
                                """
                                         _____ _     _____         _____         \s
                                        |_   _(_) __|_   _|_ _  __|_   _|__   ___\s
                                          | | | |/ __|| |/ _` |/ __|| |/ _ \\ / _ \\
                                          | | | | (__ | | (_| | (__ | | (_) |  __/
                                          |_| |_|\\___||_|\\__,_|\\___||_|\\___/ \\___|
                                        
                                """
        );
    }

    public void displayGomokuTitle(){
        System.out.println(
                """
                          ____                       _         \s
                         / ___| ___  _ __ ___   ___ | | ___   _\s
                        | |  _ / _ \\| '_ ` _ \\ / _ \\| |/ / | | |
                        | |_| | (_) | | | | | | (_) |   <| |_| |
                         \\____|\\___/|_| |_| |_|\\___/|_|\\_\\\\__,_|
                        
                """
        );
    }

    /**
     * displayInvalidChoice
     */
    public void displayInvalidChoice(){
        System.out.println("This choice is invalid, please try again");
    }

    /**
     * displayPlayersRepresentations
     */
    public void displayPlayersRepresentations(){
        System.out.println("""
                
                
                Player 1 -> X
                Player 2 -> O
                """);
    }

    /**
     * displayBoard
     * @param size
     * @param board
     */
    public void displayBoard(int size, Cell[][] board) {
        for (int i = 0; i < size; i++) {
            System.out.print("\n-------------\n|");
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j].getRepresentation() + "|");
            }
        }
    }

    /**
     * displayPlayerNameTurn
     * @param playerName
     */
    public void displayPlayerNameTurn(String playerName){
        System.out.println("\n\n*-------------*\n "+ playerName + " turn\n"+"*-------------*\n");
    }

    /**
     * displayWrongCell
     */
    public void displayWrongCell(){
        System.out.println("This cell is not empty, please try again");
    }

    /**
     * displayEndGame
     */
    public void displayEndGame() {
        System.out.println("""
           *-------------*" +
          "   End game " +
          "There's no winner..." +
          "*-------------*""");
    }

    /**
     * displayWinnerGame
     * @param player
     */
    public void displayWinnerGame(Player player){
        System.out.println(
                """  
                                                             _                     _\s
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

