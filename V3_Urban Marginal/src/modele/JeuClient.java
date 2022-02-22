package modele;

/**
 * @author Romain Magana
 * @version V1 
 * JeuClient
 * date : 14/10/21
 */

import controleur.Controle;
import outils.connexion.Connection;

public class JeuClient extends Jeu{
	
	//Properties
	private Connection connection;
	
	//Constructor 
	public JeuClient(Controle controle) {
		super.controle = controle;
	}
		
	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
		
	}

	@Override
	public void reception(Connection connection, Object info) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deconnection(Connection connection) {
		// TODO Auto-generated method stub
		
	}
	
	public void envoi(Object info) {
		super.envoi(connection,info);
	}
	
}
