package board;

/**
 * State
 * This enum stock states that can be used by the board or the player
 * 3 states can be allocated to a Cell, his default state is EMPTY.
 * X and O states can also be used by players
 */
public enum State {

    EMPTY("   "),
    X(" X "),
    O(" O ");

    private String type;

    State(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

}
