package game;

/**
 * View
 *
 * The View class is responsible for displaying various outputs to the console
 * during the game. It handles user prompts, game state visualizations,
 * and messages for different scenarios such as errors, victories, or invalid inputs.
 *
 * @see board.Cell
 * @see GameType
 */


import board.Cell;

public class View {
    public void displayLaunchGame() {
        System.out.println("""
                
                ╔════════════════════════════════════════════╗
                ║                                            ║
                ║ ╦═╗┌─┐┌─┐┌┬┐┬ ┬  ╔╦╗┌─┐  ╔═╗┬  ┌─┐┬ ┬  ┌─┐ ║
                ║ ╠╦╝├┤ ├─┤ ││└┬┘   ║ │ │  ╠═╝│  ├─┤└┬┘   ┌┘ ║
                ║ ╩╚═└─┘┴ ┴─┴┘ ┴    ╩ └─┘  ╩  ┴─┘┴ ┴ ┴    o  ║
                ║                                            ║
                ╚════════════════════════════════════════════╝
                """);
    }
    public void displayTitle(GameType gametype){
        switch (gametype) {
            case TIC_TAC_TOE:
                System.out.println("""
                        
                        ╔═══════════════════════════════╗
                        ║ ╔╦╗┬┌─┐  ╔╦╗┌─┐┌─┐  ╔╦╗┌─┐┌─┐ ║
                        ║  ║ ││     ║ ├─┤│     ║ │ │├┤  ║
                        ║  ╩ ┴└─┘   ╩ ┴ ┴└─┘   ╩ └─┘└─┘ ║
                        ╚═══════════════════════════════╝
                        """);
                break;
            case GOMOKU:
                System.out.println(""" 
                        
                        ╔════════════════════╗
                        ║ ╔═╗┌─┐┌┬┐┌─┐┬┌─┬ ┬ ║
                        ║ ║ ╦│ │││││ │├┴┐│ │ ║
                        ║ ╚═╝└─┘┴ ┴└─┘┴ ┴└─┘ ║
                        ╚════════════════════╝
                        """);
                break;
            case CONNECT_FOUR:
                System.out.println("""
                        
                        ╔═════════════════════════════════════╗
                        ║                                     ║
                        ║ ╔═╗┌─┐┌┐┌┌┐┌┌─┐┌─┐┌┬┐  ╔═╗┌─┐┬ ┬┬─┐ ║
                        ║ ║  │ │││││││├┤ │   │   ╠╣ │ ││ │├┬┘ ║
                        ║ ╚═╝└─┘┘└┘┘└┘└─┘└─┘ ┴   ╚  └─┘└─┘┴└─ ║
                        ║                                     ║
                        ╚═════════════════════════════════════╝
                        """);
                break;

        }
    }
    public void displayInvalidCell(){
        System.out.println("This cell is not empty, please try again");
    }
    public void displayBoard(int numberOfLine, int numberOfColumn, Cell[][] board) {
        for (int i = 0; i < numberOfColumn; i++) {
            System.out.print("\n*"+("---*".repeat(board[0].length))+"\n|");
            for (int j = 0; j < numberOfLine; j++) {
                System.out.print(board[i][j].getRepresentation() + "|");
            }
        }
        System.out.print("\n*"+("---*".repeat(board[0].length)));
    }
    public void displayPlayerTurn(String playerName){
        System.out.println("\n\n*-------------*\n "+ playerName + " turn\n"+"*-------------*\n");
    }
    public void displayNoWinner() {
        System.out.println("""
          
          
                ╔═══════════╗
                ║ ┌─┐┌┐┌┌┬┐ ║
                ║ ├┤ │││ ││ ║
                ║ └─┘┘└┘─┴┘ ║
                ╚═══════════╝
          
          *---------------------*
            There's no winner...
          *---------------------*""");
    }
    public void displayWinnerGame(String playerName){
        System.out.println(
                """
                        
                        ╔══════════════════════╗
                        ║ ╦  ╦┬┌─┐┌┬┐┌─┐┬─┐┬ ┬ ║
                        ║ ╚╗╔╝││   │ │ │├┬┘└┬┘ ║
                        ║  ╚╝ ┴└─┘ ┴ └─┘┴└─ ┴  ║
                        ╚══════════════════════╝
                        """
        );
        System.out.println(playerName + " is the winner");
    }
    public void displayInvalidChoice(){
        System.out.println("""
                /!\\ This choice is invalid, please try again /!\\
                """);
    }


    public void displayMenuChoiceGame(){
        System.out.println("""
                *----------------------*
                    Choose your game
                *----------------------*
                
                [1] Tic Tac Toe
                [2] Gomoku
                """);
    }
    public void displayMenuPlayerChoice(String player) {
        System.out.println("Please choose "+player +
                """
                        
                        [1] Real player 
                        [2] Computer
                        """);
    }
    public void displayMenuChoiceLine(int verticalSize){
        System.out.println("Choose the column between 1 and "+ verticalSize);
    }
    public void displayMenuChoiceColumn( int horizontalSize){
        System.out.println("Choose the column between 1 and "+ horizontalSize);
    }
    public void displayMenuRestart(){
        System.out.println(
                """
                *-----------------------*
                       What next ?
                *-----------------------*
                
                [1] Start a new game
                [2] Exit 
                        """
        );
    }








}

