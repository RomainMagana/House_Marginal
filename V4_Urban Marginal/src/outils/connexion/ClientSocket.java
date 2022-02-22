package outils.connexion;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

/**
 * @author Romain Magana
 * @version V1 
 * ClientSocket
 * date : 01/10/21
 */

public class ClientSocket {
	//Properties
	boolean connexionOk;
	
	//Constructor
	public ClientSocket(String ip, int port, Object leRecepteur) {
		connexionOk = false;
		
		try {
			Socket socket = new Socket(ip,port);
			System.out.println("Connexion serveur réussi");
			connexionOk = true;
			new Connection(socket, leRecepteur);
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "Serveur non disponible");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "IP incorrecte");
		}
	}

	/**
	 * @return the connexionOk
	 */
	public boolean isConnexionOk() {
		return connexionOk;
	}

	
}