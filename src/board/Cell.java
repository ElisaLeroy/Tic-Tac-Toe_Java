package board;
/**
 *
 * Represents a single cell on the game board.
 * Each cell has a state, which determines its current content.
 */

public class Cell  {
    private State state;

    /**
     * Cell constructor
     * Constructs a new Cell with an initial state of State.EMPTY.
     */
    public Cell(){
        this.state = State.EMPTY;
    }


    /**
     * Returns the string representation of the cell's state.
     *
     * @return the cell's state as a string.
     */
    public String getRepresentation(){
        return this.state.getType();
    }

    /**
     * Gets the current state of the cell.
     *
     * @return the cell's state.
     */
    public State getState(){
        return this.state;
    }

    /**
     * Sets the state of the cell.
     *
     * @param state the new state of the cell.
     */
    public void setState(State state){
        this.state = state;
    }


}
