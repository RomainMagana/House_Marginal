package modele;

import controleur.Controle;
import outils.connexion.Connection;

public abstract class Jeu {
	//Properties
	protected Controle controle;
	
	//Methode
	// La r�c�ption d'une connection pour communiquer avec un ordinateur distant
	public abstract void setConnection(Connection connection);
	
	// La r�c�ption d'une information d'un ordinateur distant
	public abstract void reception(Connection connection,Object info);
	
	// L'envoi d'une information pour communiquer avec l'odinateur distant
	public void envoi(Connection connection,Object info) {
		connection.envoi(info);
	}
	
	//La r�c�ption d'une deconnection 
	public abstract void deconnection(Connection connection);
}
