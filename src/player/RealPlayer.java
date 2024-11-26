package player;

/**
 * RealPlayer
 * This class represent a player played by the user
 */

import board.Cell;
import board.Coordinates;
import board.State;
import game.GameType;
import game.UserInteraction;
import game.View;

import java.util.Objects;

public class RealPlayer extends Player {
    View view;
    UserInteraction userInteraction;

    public RealPlayer(State state, String name) {
        this.setName(name);
        this.setState(state);
        this.view = new View();
        this.userInteraction = new UserInteraction();
    }

    public Coordinates getMove(Coordinates coordinates, int horizontalSize, int verticalSize, GameType gameType) {
        int column;
        int line;
        if (gameType == GameType.CONNECT_FOUR) {
            view.displayPlayerNameTurn(this.getName());

            view.displayMenuChoiceColumn(horizontalSize);
            column = getPlayerChoice() - 1;

            coordinates.setColumn(column);
        } else {
            view.displayPlayerNameTurn(this.getName());

            view.displayMenuChoiceLine(verticalSize);
            line = getPlayerChoice() - 1;

            view.displayMenuChoiceColumn(horizontalSize);
            column = getPlayerChoice() - 1;
            coordinates.setLine(line);
            coordinates.setColumn(column);
        }
        return coordinates;
    }

    private int getPlayerChoice() {
        int choice = 0;
        while (choice == 0) {
            try {
                choice = userInteraction.scannerInt();
            } catch (Exception e) {
                view.displayInvalidChoice();
            }
        }
        return choice;
    }



}
