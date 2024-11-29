package player;

/**
 * ArtificialPlayer
 * This class represent a player.
 * This player is controlled by the system and makes automated moves.
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

    /**
     * Constructs an ArtificialPlayer with a given state and name.
     * The player type is automatically set to ARTIFICIAL.
     *
     * @param state The state of the player (X or O).
     * @param name The name of the player.
     */
    public ArtificialPlayer(State state, String name ){
        this.setName(name);
        this.setState(state);
        this.setType(PlayerType.ARTIFICIAL);
    }








}
