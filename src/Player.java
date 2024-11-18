public class Player extends Cell{

    private String representation;  //peut prendre les valeurs O ou X

    public Player() {
        this.representation = " X ";
    }

    @Override
    public String getRepresentation() {
        return representation;
    }
}
