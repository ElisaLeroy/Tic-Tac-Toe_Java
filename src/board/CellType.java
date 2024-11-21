package board;

/**
 * CellType
 * This enum stock states that can be used by the board or the player
 */
public enum CellType {

    EMPTY("   "),
    X(" X "),
    O(" O ");

    private String type;

    CellType(String type) {
        this.type = type;
    }
    public String getType() {
        return this.type;
    }

}
