package game;

public class ApplicationController {
    private View view;
    private UserInteraction interaction;
    private GameController game;

    public ApplicationController() {
        this.view = new View();
        this.interaction = new UserInteraction();
    }


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
            default:
                view.displayInvalidChoice();
                Main.main(null);
                break;
        }
    }




}
