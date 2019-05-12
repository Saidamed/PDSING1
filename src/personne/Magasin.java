package personne;

public class Magasin {
    private String nom;
    private String categorie;


    public Magasin(String nom, String categorie) {
        this.nom = nom;
        this.categorie = categorie;

    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    public String toString(){
        return "      "+this.nom;
    }
}
