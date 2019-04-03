package personne;

public class Profile {
    private String name;
    private int ID;
    public Profile(String nom, int id){
        this.name = nom;
        this.ID=id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public String toString(){
        return "ID: "+this.ID+"; Nom: "+this.name;
    }
}
