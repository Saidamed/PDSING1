package personne;

public class Personne {
	private String nom;
	private String prenom;
	private int id;

	public Personne(String nom,String prenom,int id) {
		this.nom = nom;
		this.prenom = prenom;
		this.id = id;
	}

	public Personne() {
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom()
	{
		return this.prenom;
	}

	public int getId() {
		return id;
	}
	public String toString(){
		return "ID:"+this.id+" Nom :"+this.nom+";Prenom :"+this.prenom;
	}
}
