package outils.connexion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import outils.connexion.ServeurSocket;

/**
 * @author Romain Magana
 * @version V1 
 * Connection
 * date : 01/10/21
 */

public class Connection extends Thread {
	//Properties
	private Object leRecepteur;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	//Constructor
	public Connection(Socket socket, Object leRecepteur) {
		this.leRecepteur = leRecepteur;
				
		//Creation de canal de sortie pour envoyer les infos
		try {
			this.out = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
		System.out.println("Erreur de creation du canal out : "+e);
		System.exit(0);
		}
		
		try {
			this.in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
		System.out.println("Erreur de creation du canal in : "+e);
		System.exit(0);
		}
		
		//Démarrage du thread d'écoute d'un message
		this.start();
		
		//Transtipage pour convenir à la méthode setconnection
		((controleur.Controle)this.leRecepteur).setConnection(this) ;
	}
	
	public void envoi(Object unObjet) {
		try {
			out.writeObject(unObjet);
			out.flush();
		} catch (IOException e) {
			System.out.println("erreur sur l'objet out : "+e);
		}
	}
	
	public void run() {
		Boolean inOk = true;
		Object reception;
		
		while(inOk) {
			try {
				reception = in.readObject();
				((controleur.Controle)this.leRecepteur).receptionInfo(this,reception);
			} catch (ClassNotFoundException e) {
				System.out.println("erreur de class reception : "+e);
				System.exit(0);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "L'ordinateur distant s'est déconnecté");
				inOk = false;
				try {
					in.close();
				} catch (IOException e1) {
					System.out.println("La fertmeture du canal s'est mal passée : "+e);
				}
			}
			
		}
	}
}