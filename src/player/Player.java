package player;
import board.Cell;
import board.Coordinates;
import board.State;
import game.GameType;

public abstract class Player {
    private String name;
    private State state;

    public Player() {

    }


    public abstract Coordinates getMove(Coordinates coordinates, int horizontalSize, int verticalSize, GameType gameType);

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
}
