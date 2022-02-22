package controleur;

import vue.Arene;
import vue.ChoixJoueur;
import vue.EntreeJeu;

import java.net.ServerSocket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.Jeu;
import modele.JeuClient;
import modele.JeuServeur;
import outils.connexion.ClientSocket;
import outils.connexion.Connection;
import outils.connexion.ServeurSocket;
/**
 * @author Romain Magana
 * @version V1 
 * Controle
 * date : 30/09/21
 */

public class Controle implements Global {
	
	//Properties
	private EntreeJeu frmEntreeJeu;
	private Jeu lejeu;
	private Arene frmArene;
	private ChoixJoueur frmChoixJoueur;
	private Connection connection;
	
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
		if(uneFrame instanceof ChoixJoueur) {
			evenementChoixJoueur(info);
		}
	}

	private void evenementEntreeJeu(Object info) {
		if((String)info == "serveur") {
			new ServeurSocket(this,PORT);
			//Choix Serveur
			this.lejeu = new JeuServeur(this);
			
			//On ferme la frame EntreeJeu
			this.frmEntreeJeu.dispose();
			
			//Lancement de l'arene
			this.frmArene = new Arene();
			((JeuServeur)lejeu).constructionMurs();
			this.frmArene.setVisible(true);
			
		}else {
			if((new ClientSocket((String)info, PORT,this) ).isConnexionOk()) {
				//Choix client
				this.lejeu = new JeuClient(this);
				this.lejeu.setConnection(connection);
				this.frmEntreeJeu.dispose();
				
				//Lancement de l'arene
				this.frmArene = new Arene();
				
				//Nouveau joueur
				this.frmChoixJoueur = new ChoixJoueur(this);
				this.frmChoixJoueur.setVisible(true);
			}
		}
	}
	
	private void evenementChoixJoueur(Object info) {
		((JeuClient)lejeu).envoi(info); //Envoi de l'info vers le serveur
		frmChoixJoueur.dispose();
		frmArene.setVisible(true);
	}
	
	
	/* ***************************************************************************************
	 * 								EVENEMENT PROVENANT DU MODELE							 *
	 *****************************************************************************************/
	
	public void setConnection(Connection connection) {
		this.connection = connection;
		if(lejeu instanceof JeuServeur) {
			lejeu.setConnection(connection);
		}
	}
	
	// le contr�leur envoie les informations vers le jeu
	public void receptionInfo(Connection connection, Object info) {
		this.lejeu.reception(connection,info);
	}
	
	//Permet de gerer les �v�nements provenant du mod�le
	public void evenementModele(Object unJeu, String ordre, Object info) {
		if(unJeu instanceof JeuServeur) {
			evenementJeuServeur(ordre, info);
		}
		if(unJeu instanceof JeuClient) {
			evenementJeuClient(ordre, info);
		}
	}
	
	//Gerer les evenements provenant du Client
	private void evenementJeuClient(String ordre, Object info) {
		if(ordre == "ajout panel murs") {
			frmArene.ajoutPanelMurs((JPanel)info);
		}
	}

	//Gerer les evenements provenant du Serveur
	public void evenementJeuServeur(String ordre, Object info) {
		if(ordre == "ajout mur") {
			frmArene.ajoutMur((JLabel)info);
		}
		if (ordre == "envoi panel murs") {
			((JeuServeur)lejeu).envoi(((Connection)info), frmArene.getJpnMurs());
		}
	}
}
