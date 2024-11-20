package player;

import board.Cell;
import board.Coordinates;
import board.CellType;

public abstract class Player {
    private String name;
    private CellType state;

    public Player() {

    }
    public abstract Coordinates getCoordinates(Coordinates coordinates);
    public abstract void play(Coordinates coordinates, Cell[][] board, int size);

    public String getRepresentation() {
        return this.state.getType();
    }
    public void setState(CellType state) {
        this.state = state;
    }
    public CellType getState() {
        return this.state;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
