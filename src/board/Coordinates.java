package board;

public class Coordinates {

    public final static int DEFAULT = -1;

    private int line;
    private int column;

    public Coordinates() {
        this(DEFAULT);
    }

    public Coordinates(int column) {
        this(DEFAULT, column);
    }

    public Coordinates(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public int getLine(){
        return line;
    }
    public int getColumn(){
        return column;
    }
    public void setLine(int line){
        this.line = line;
    }
    public void setColumn(int column){
        this.column = column;
    }
}
