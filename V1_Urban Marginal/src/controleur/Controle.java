package controleur;

import vue.EntreeJeu;

import java.net.ServerSocket;

import javax.swing.JFrame;

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
		}else {
			if((new ClientSocket((String)info, 6666,this) ).isConnexionOk()) {
				
			}
		}
	}
	
	
	/* ***************************************************************************************
	 * 								EVENEMENT PROVENANT DE CONTROLE							 *
	 *****************************************************************************************/
	
}