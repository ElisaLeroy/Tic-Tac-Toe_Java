package board;

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
