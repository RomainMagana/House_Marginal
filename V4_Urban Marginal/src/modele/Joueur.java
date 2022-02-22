package modele;

import controleur.Global;

public class Joueur extends Objet implements Global {
	//Properties
	private String pseudo;
	private int numPerso;
	private Label message;

	//Constructor
	public Joueur() {
		
	}

	private initPerso(String pseudo,int numPerso) {
		this.pseudo = pseudo;
		this.numPerso = numPerso;
	}

	/**
	 * @return the message
	 */
	public Label getMessage() {
		return message;
	}
}
