package modele;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.Global;
import outils.connexion.Connection;

/**
 * Gestion des joueurs
 * @author emds
 *
 */
public class Joueur extends Objet implements Global {

	// constantes
	private static final int MAXVIE = 10 ; // vie de d�part pour tous les joueurs
	private static final int GAIN = 1 ; // gain de points de vie lors d'une attaque
	private static final int PERTE = 2 ; // perte de points de vie lors d'une attaque
	
	// propri�t�s
	private String pseudo ;
	private int numPerso ;
	private Label message ;
	private JeuServeur jeuServeur ;
	private int vie ; // vie restante du joueur
	private int orientation ; // tourn� vers la gauche (0) ou vers la droite (1)
	private int etape ; // num�ro d'�tape dans l'animation
	private Boule boule ; // la boule du joueur
	
	/**
	 * Constructeur
	 */
	public Joueur(JeuServeur jeuServeur) {
		this.jeuServeur = jeuServeur ;
		vie = MAXVIE ;
		etape = 1 ;
		orientation = DROITE ;
	}
	
	/**
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * @return the boule
	 */
	public Boule getBoule() {
		return boule;
	}

	/**
	 * @return the orientation
	 */
	public int getOrientation() {
		return orientation;
	}

	/**
	 * Affiche le personnage et son message
	 * @param etat
	 * @param etape
	 */
	public void affiche(String etat, int etape) {
		label.getjLabel().setBounds(posX, posY, L_PERSO, H_PERSO);
		label.getjLabel().setIcon(new ImageIcon(PERSO+numPerso+etat+etape+"d"+orientation+EXTIMAGE));
		message.getjLabel().setBounds(posX-10, posY+H_PERSO, L_PERSO+10, H_MESSAGE);
		message.getjLabel().setText(pseudo+" : "+vie);
		// envoi du personnage � tous les autres joueurs
		jeuServeur.envoi(label);
		jeuServeur.envoi(message);
	}
	
	/**
	 * Initialisation d'un joueur (pseudo et num�ro)
	 * @param pseudo
	 * @param numPerso
	 */
	public void initPerso(String pseudo, int numPerso, Hashtable<Connection, Joueur> lesJoueurs, ArrayList<Mur> lesMurs) {
		this.pseudo = pseudo ;
		this.numPerso = numPerso ;
		// cr�ation de l'affichage du personnage
		label = new Label(Label.getNbLabel(), new JLabel()) ;
		Label.setNbLabel(Label.getNbLabel()+1);
		label.getjLabel().setHorizontalAlignment(SwingConstants.CENTER);
		label.getjLabel().setVerticalAlignment(SwingConstants.CENTER);
		jeuServeur.nouveauLabelJeu(label);
		// cr�ation de l'affichage du message sous le personnage
		message = new Label(Label.getNbLabel(), new JLabel()) ;
		Label.setNbLabel(Label.getNbLabel()+1);
		message.getjLabel().setHorizontalAlignment(SwingConstants.CENTER);
		message.getjLabel().setFont(new Font("Dialog", Font.PLAIN, 8));
		jeuServeur.nouveauLabelJeu(message);
		// calcul de la premi�re position al�atoire
		premierePosition(lesJoueurs, lesMurs) ;
		// affichage du personnage
		affiche(MARCHE, etape) ;
		// cr�ation de la boule
		boule = new Boule(jeuServeur) ;
		jeuServeur.envoi(boule.getLabel());
	}

	/**
	 * G�re le d�placement du personnage
	 * @param action
	 * @param position
	 * @param orientation
	 * @param lepas
	 * @param max
	 * @param lesJoueurs
	 * @param lesMurs
	 * @return
	 */
	private int deplace(int action, // action sollicit�e : aller vers la gauche, la droite
						int position, // position de d�part
						int orientation, // orientation de d�part
						int lepas, // valeur du d�placement (positif ou n�gatif)
						int max, // valeur � ne pas d�passer
						Hashtable<Connection, Joueur> lesJoueurs, // les autres joueurs (pour �viter les collisions)
						ArrayList<Mur> lesMurs) { // les murs (pour �viter les collisions)
		this.orientation = orientation ;
		int ancpos = position ;
		position += lepas ;
		position = Math.max(position, 80) ;
		position = Math.min(position,  max) ;
		if (action==GAUCHE || action==DROITE) {
			posX = position ;
		}else{
			posY = position ;
		}
		// controle s'il y a collision
		if (toucheJoueur(lesJoueurs) || toucheMur(lesMurs)) {
			position = ancpos ;
		}
		// passe � l'�tape suivante de l'animation de la marche
		etape = (etape % NBETATSMARCHE) + 1 ;
		return position ;
	}
	
	/**
	 * G�re une action re�ue du controleur (d�placement, tire de boule...)
	 * @param action
	 * @param lesJoueurs
	 * @param lesMurs
	 */
	public void action(int action, Hashtable<Connection, Joueur> lesJoueurs, ArrayList<Mur> lesMurs) {
		// traite l'action
		switch (action) {
			case GAUCHE : posX = deplace(action, posX, GAUCHE,     -LEPAS, L_ARENE - L_PERSO, lesJoueurs, lesMurs);break;
			case DROITE : posX = deplace(action, posX, DROITE,      LEPAS, L_ARENE - L_PERSO -50, lesJoueurs, lesMurs);break;
			case HAUT :   posY = deplace(action, posY, orientation,-LEPAS, H_ARENE - H_PERSO - H_MESSAGE, lesJoueurs, lesMurs);break;
			case BAS :    posY = deplace(action, posY, orientation, LEPAS,H_ARENE - H_PERSO - H_MESSAGE - 100, lesJoueurs, lesMurs);break;
			case TIRE :   
				if (!boule.getLabel().getjLabel().isVisible()) {
					jeuServeur.envoi(FIGHT);
					boule.tireBoule(this, lesMurs, lesJoueurs); 
				}
				break;
	    }
		// affiche le personnage � sa nouvelle position
		affiche(MARCHE, etape) ;
	}
	
	/**
	 * @return the message
	 */
	public Label getMessage() {
		return message;
	}
	
	/**
	 * Contr�le si le joueur chevauche un des autres joueurs
	 * @param lesJoueurs
	 * @return
	 */
	private boolean toucheJoueur(Hashtable<Connection, Joueur> lesJoueurs) {
		for (Joueur unJoueur : lesJoueurs.values()) {
			if (!unJoueur.equals(this)) {
				if (toucheObjet(unJoueur)) {
					return true ;
				}
			}
		}
		return false ;
	}
	
	/**
	 * Contr�le si le joueur chevauche un des murs
	 * @param lesMurs
	 * @return
	 */
	private boolean toucheMur(ArrayList<Mur> lesMurs) {
		for (Mur unMur : lesMurs) {
			if (toucheObjet(unMur)) {
				return true ;
			}
		}
		return false ;
	}
	
	/**
	 * Calcul de la premi�re position al�atoire du joueur (sans chevaucher un autre joueur ou un mur)
	 * @param lesJoueurs
	 * @param lesMurs
	 */
	private void premierePosition(Hashtable<Connection, Joueur> lesJoueurs, ArrayList<Mur> lesMurs) {
		label.getjLabel().setBounds(0, 0, L_PERSO, H_PERSO);
		do {
			// - 100 pour le bord de map
			posX = (int) Math.round(Math.random() * (L_ARENE - 100 - L_PERSO))+80 ;
			posY = (int) Math.round(Math.random() * (H_ARENE - 100 - H_PERSO - H_MESSAGE)) ;
		}while(toucheJoueur(lesJoueurs)||toucheMur(lesMurs)) ;
	}
	
	/**
	 * Gain de points de vie apr�s avoir touch� un joueur
	 */
	public void gainVie() {
		vie += GAIN ;
	}
	
	/**
	 * Perte de points de vie apr�s avoir �t� touch� 
	 */
	public void perteVie() {
		vie = Math.max(vie-PERTE, 0);
	}
	
	/**
	 * vrai si la vie est � 0
	 * @return
	 */
	public boolean estMort() {
		return (vie==0);
	}
	
	/**
	 * Le joueur est mort et disparait
	 */
	public void departJoueur() {
		if (!(label==null)) {
			label.getjLabel().setVisible(false);
			message.getjLabel().setVisible(false);
			boule.getLabel().getjLabel().setVisible(false);		
			jeuServeur.envoi(label);
			jeuServeur.envoi(message);
			jeuServeur.envoi(boule.getLabel());
		}
	}
	
}
