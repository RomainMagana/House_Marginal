package modele;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.Global;
import outils.connexion.Connection;

/**
 * Gestion de la boule
 * @author emds
 *
 */
public class Boule extends Objet implements Global {

	// propriétés
	private JeuServeur jeuServeur ;
	
	/**
	 * Constructeur
	 */
	public Boule(JeuServeur jeuServeur) {
		// création de la boule, centrage, taille, et invisible pour le moment
		label = new Label(Label.getNbLabel(), new JLabel());
		Label.setNbLabel(Label.getNbLabel()+1);
		label.getjLabel().setHorizontalAlignment(SwingConstants.CENTER);
		label.getjLabel().setVerticalAlignment(SwingConstants.CENTER);
		label.getjLabel().setBounds(0, 0, L_BOULE, H_BOULE);
		label.getjLabel().setIcon(new ImageIcon(BOULE));
		label.getjLabel().setVisible(false);
		// récupération du jeu serveur
		this.jeuServeur = jeuServeur ;
		// ajout du label dans le jeu
		jeuServeur.nouveauLabelJeu(label);
	}
	
	/**
	 * 
	 * @param attaquant
	 */
	public void tireBoule(Joueur attaquant, ArrayList<Mur> lesMurs, Hashtable<Connection, Joueur> lesJoueurs) {
		// positionnement de la boule
		if(attaquant.getOrientation()==GAUCHE) {
			posX = attaquant.getPosX() - L_BOULE - 1 ;
		}else{
			posX = attaquant.getPosX() + L_PERSO + 1 ;
		}
		posY = attaquant.getPosY() + H_PERSO/2 ;
		// démarrage de l'attaque
		new Attaque(attaquant, jeuServeur, lesMurs, lesJoueurs);
	}
	
}
