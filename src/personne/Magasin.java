package personne;

public class Magasin {
    private String nom;
    private String categorie;
   // private int numEmpl;
    //private int etageEmpl;


    public Magasin(String nom, String categorie) {
        this.nom = nom;
        this.categorie = categorie;
       // this.numEmpl = numEmpl;
        //this.etageEmpl = etageEmpl;
    }

   /* public int getNumEmpl() {
        return numEmpl;
    }

    public void setNumEmpl(int numEmpl) {
        this.numEmpl = numEmpl;
    }

    public int getEtageEmpl() {
        return etageEmpl;
    }

    public void setEtageEmpl(int etageEmpl) {
        this.etageEmpl = etageEmpl;
    }
*/
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
