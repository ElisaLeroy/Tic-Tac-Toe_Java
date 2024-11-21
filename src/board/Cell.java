package board;

/**
 * models a board's cell
 */

public class Cell  {
    private CellType state;


    public Cell(){
        this.state = CellType.EMPTY;

    }


    /**
     * getRepresentation
     * to get the cell's representation
     * @return String
     */
    public String getRepresentation(){
        return this.state.getType();
    }

    /**
     *setState
     * To set the cell's state
     * @param state
     */
    public void setState(CellType state){
        this.state = state;
    }



}
