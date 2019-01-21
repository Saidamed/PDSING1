package vue;

import serveur.Serveur;

public class LancementVue {
	public static void main(String[] args) {
		Serveur s = new Serveur();
		s.open();
		new Vue();
	}
}
