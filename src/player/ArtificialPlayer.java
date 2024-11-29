package player;

/**
 * ArtificialPlayer
 * This class represent a player played by the computer
 */

import board.Cell;
import board.Coordinates;
import board.State;
import game.GameType;
import game.PlayerType;
import game.View;
import java.security.SecureRandom;
import java.util.Objects;

public class ArtificialPlayer extends Player {

    public ArtificialPlayer(State state, String name ){
        this.setName(name);
        this.setState(state);
        this.setType(PlayerType.ARTIFICIAL);
    }








}
