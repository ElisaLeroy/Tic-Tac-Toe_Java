package player;
/**
 * Abstract class representing a player in the game.
 * The player can either be a human or the system.
 */

import board.Cell;
import board.Coordinates;
import board.State;
import game.GameType;
import game.PlayerType;

public abstract class Player {
    private String name;
    private State state;
    private PlayerType type;

    public Player() {

    }



    public String getRepresentation() {
        return this.state.getType();
    }
    public void setState(State state) {
        this.state = state;
    }
    public State getState() {
        return this.state;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public PlayerType getType() {
        return type;
    }
    public void setType(PlayerType type) {
        this.type = type;
    }
}
