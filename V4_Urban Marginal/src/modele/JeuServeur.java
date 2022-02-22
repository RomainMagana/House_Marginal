package modele;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import controleur.Controle;
import outils.connexion.Connection;
import controleur.Global;

/**
 * @author Romain Magana
 * @version V1 
 * JeuServeur
 * date : 14/10/21
 */

public class JeuServeur extends Jeu implements Global {
	//Properties
	private ArrayList<Mur> lesMurs = new ArrayList<Mur>();
	private Hashtable<Connection, Joueur> lesJoueurs = new Hashtable<Connection, Joueur>();
	
	//Constructor 
	public JeuServeur(Controle controle) {
		super.controle = controle;
		Label.setNbLabel(0);
	}
	
	@Override
	public void setConnection(Connection connection) {
		lesJoueurs.put(connection, new Joueur());
		controle.evenementModele(this, "envoi panel murs", connection);
	}
	
	@Override
	public void reception(Connection connection, Object info) {
		//Split permet de découper un string en tableau à partir d'un char de séparation
		String[] infos = ((String)info).split(SEPARE);
		
		switch (Integer.parseInt(infos[0])) {
		case PSEUDO :
			break;

		default:
			break;
		}
		
	}
	
	@Override
	public void deconnection(Connection connection) {
		// TODO Auto-generated method stub
	}
	
	public void constructionMurs() {
		for (int i = 0; i < NBMURS; i++) {
			lesMurs.add(new Mur());
			controle.evenementModele(this, "ajout mur", lesMurs.get(i).getLabel().getjLabel());
			
		}
	}
}
