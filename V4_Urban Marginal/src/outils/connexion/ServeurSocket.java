package outils.connexion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Romain Magana
 * @version V1 
 * ServeurSocket
 * date : 01/10/21
 */

public class ServeurSocket extends Thread {
	
	//Properties
	private Object leRecepteur;
	private ServerSocket serverSocket;
	
	//Constructor
	public ServeurSocket(Object leRecepteur,int port) {
		//On initialise la prop d�clar�e avec la prop du parametre
		this.leRecepteur = leRecepteur;
		
		// cr�ation de socket serveur des clients
		try {
			this.serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("erreur grave cr�ation socket serveur : "+e);
			System.exit(0);
		}
		
		//d�marrage du thread d'�coute d'attente, attente du client
		this.start();
	}
	
	
	public void run() {
		Socket socket;
		
		while(true) {
			try {
				System.out.println("Le serveur attend");
				socket = serverSocket.accept();
				System.out.println("Le client s'est connect�");
				new Connection(socket, leRecepteur);
			} catch (IOException e) {
				System.out.println("erreur sur l'objet serverSocket : "+e);
				System.exit(0);
			}
		}
	}
}
