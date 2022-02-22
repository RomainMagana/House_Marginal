package controleur;

import vue.Arene;
import vue.ChoixJoueur;
import vue.EntreeJeu;

import java.net.ServerSocket;

import javax.swing.JFrame;

import modele.Jeu;
import modele.JeuClient;
import modele.JeuServeur;
import outils.connexion.ClientSocket;
import outils.connexion.ServeurSocket;
/**
 * @author Romain Magana
 * @version V1 
 * Controle
 * date : 30/09/21
 */

public class Controle {
	
	//Properties
	private EntreeJeu frmEntreeJeu;
	private Jeu lejeu;
	private Arene frmArene;
	private ChoixJoueur frmChoixJoueur;
	
	public static void main(String[] args) {
		new Controle(); 
	}
	
	// Constructor
	private Controle() {
		this.frmEntreeJeu = new EntreeJeu(this);
		this.frmEntreeJeu.setVisible(true);
	}

	/* ***************************************************************************************
	 * 								EVENEMENT PROVENANT DE LA VUE							 *
	 *****************************************************************************************/
	
	public void evenementVue(JFrame uneFrame, Object info) {
		if(uneFrame instanceof EntreeJeu) {
			evenementEntreeJeu((String)info);
		}
	}

	private void evenementEntreeJeu(Object info) {
		if((String)info == "serveur") {
			new ServeurSocket(this,6666);
			//Choix Serveur
			this.lejeu = new JeuServeur(this);
			
			//On ferme la frame EntreeJeu
			this.frmEntreeJeu.dispose();
			
			//Lancement de l'arene
			this.frmArene = new Arene();
			this.frmArene.setVisible(true);
			
		}else {
			if((new ClientSocket((String)info, 6666,this) ).isConnexionOk()) {
				//Choix client
				this.lejeu = new JeuClient(this);
				this.frmEntreeJeu.dispose();
				
				//Lancement de l'arene
				this.frmArene = new Arene();
				
				//Nouveau joueur
				this.frmChoixJoueur = new ChoixJoueur();
				this.frmChoixJoueur.setVisible(true);
			}
		}
	}
	
	
	/* ***************************************************************************************
	 * 								EVENEMENT PROVENANT DU MODELE							 *
	 *****************************************************************************************/
	
	
	
}