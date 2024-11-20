package board;

/**
 * Classe qui modélise une case du plateau de jeu
 */
public class Cell  {
    private CellType state;


    public Cell(){
        this.state = CellType.EMPTY;

    }


    /**
     * @return String qui représente une cellule
     */

    public String getRepresentation(){
        return this.state.getType();
    }
    public void setState(CellType state){
        this.state = state;
    }



}
