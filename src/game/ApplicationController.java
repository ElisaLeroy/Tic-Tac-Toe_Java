package game;

/**
 * Controller class responsible for managing the flow of the game application.
 * It handles the user's game selection and starts the chosen game.
 */

public class ApplicationController {
    private View view;
    private UserInteraction interaction;
    private GameController game;

    public ApplicationController() {
        this.view = new View();
        this.interaction = new UserInteraction();
    }


    /**
     * Displays the game launch screen and prompts the user to choose a game.
     * After the user makes a choice, the selected game is started.
     */
    public void chooseGame() {
        view.displayLaunchGame();
        view.displayMenuChoiceGame();
        int choice = getSecureScanner();
        instanceGame(choice);
        game.playGame();
    }

    private int getSecureScanner(){
        int choice = 0;
        while (choice == 0) {
            try {
                choice = interaction.scannerInt();
            } catch (Exception e) {
                view.displayInvalidCell();
            }
        }
        return choice;
    }
    private void instanceGame(int choice){
        switch (choice) {
            case 1:
                this.game = new GameController(GameType.TIC_TAC_TOE);
                break;
            case 2 :
                this.game = new GameController(GameType.GOMOKU);
                break;
            case 3:
                this.game = new GameController(GameType.CONNECT_FOUR);
                break;
            default:
                view.displayInvalidChoice();
                Main.main(null);
                break;
        }
    }




}
