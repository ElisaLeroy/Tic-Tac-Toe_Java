public class Player extends Cell{

    private String representation;  //peut prendre les valeurs O ou X
    private String name;

    public Player() {

    }

    @Override
    public String getRepresentation() {
        return representation;
    }
    public void setRepresentation(String representation) {
        this.representation = representation;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
