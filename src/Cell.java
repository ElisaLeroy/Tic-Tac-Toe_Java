
/**
 * Classe qui modélise une case du plateau de jeu
 */
public class Cell  {
    private String representation;

    public Cell(){
        this.representation = "   ";
    }


    /**
     * @return String qui représente une cellule
     */

    public String getRepresentation(){
        return representation;
    }
    public void setRepresentation(String representation){
        this.representation = representation;
    }



}
