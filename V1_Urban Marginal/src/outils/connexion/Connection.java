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
				
		//creation de canal de sortie pour envoyer les infos
		try {
			this.out = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
		System.out.println("Erreur de creation du canal out : "+e);
			e.printStackTrace();
		}
		
		try {
			this.in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
		System.out.println("Erreur de creation du canal in : "+e);
			e.printStackTrace();
		}
		
		//D�marrage du thread 
		this.start();
	}
	public void run() {
		Boolean inOk = true;
		Object reception;
		
		while(inOk) {
			try {
				reception = in.readObject();
			} catch (ClassNotFoundException e) {
				System.out.println("erreur de class reception : "+e);
				System.exit(0);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "L'ordinateur distant s'est d�connect�");
				inOk = false;
				try {
					in.close();
				} catch (IOException e1) {
					System.out.println("La fertmeture du canal s'est mal pass�e : "+e);
				}
			}
		}
	}
}
