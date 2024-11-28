package player;

/**
 * RealPlayer
 * This class represent a player played by the user
 */

import board.Cell;
import board.Coordinates;
import board.State;
import game.GameType;
import game.PlayerType;
import game.UserInteraction;
import game.View;

import java.util.Objects;

public class RealPlayer extends Player {
    View view;
    UserInteraction userInteraction;

    public RealPlayer(State state, String name) {
        this.setName(name);
        this.setState(state);
        this.setType(PlayerType.REAL);
        this.view = new View();
        this.userInteraction = new UserInteraction();
    }
}
