package modele;

import java.util.ArrayList;
import java.util.Hashtable;

import controleur.Global;
import outils.connexion.Connection;

/**
 * Gestion de l'attaque (boule tir�e, joueur �ventuellement touch�)
 * @author emds
 *
 */
public class Attaque extends Thread implements Global {

	// propri�t�s
	private Joueur attaquant ;
	private JeuServeur jeuServeur ;
	private ArrayList<Mur> lesMurs = new ArrayList<Mur>() ;
	private Hashtable<Connection, Joueur> lesJoueurs = new Hashtable<Connection, Joueur>() ;
	
	/**
	 * Constructeur
	 * @param attaquant
	 * @param jeuServeur
	 */
	public Attaque(Joueur attaquant, JeuServeur jeuServeur, ArrayList<Mur> lesMurs, Hashtable<Connection, Joueur> lesJoueurs) {
		this.attaquant = attaquant;
		this.jeuServeur = jeuServeur;
		this.lesMurs = lesMurs ;
		this.lesJoueurs = lesJoueurs ;
		this.start();
	}

	/**
	 * m�thode dans le thread, pour faire bouger la boule
	 */
	public void run() {
		// l'attaquant est mis � la position 1 de la marche
		attaquant.affiche(MARCHE, 1);
		// r�cup�ration de la boule et orientation de l'attaquant
		Boule laboule = attaquant.getBoule() ;
		int orientation = attaquant.getOrientation() ;
		laboule.getLabel().getjLabel().setVisible(true);
		// gestion de l'�ventuel joueur touch� par la boule
		Joueur victime = null ;
		// boucle sur la trajectoire de la boule
		do {
			if (orientation==GAUCHE) {
				laboule.setPosX(laboule.getPosX()-LEPAS) ;
			}else{
				laboule.setPosX(laboule.getPosX()+LEPAS) ;				
			}
			laboule.getLabel().getjLabel().setBounds(laboule.getPosX(), laboule.getPosY(), L_BOULE, H_BOULE);
			pause(5, 0);
			jeuServeur.envoi(laboule.getLabel());
			victime = toucheJoueur() ;
		}while(laboule.getPosX()>=0 && laboule.getPosX()<=L_ARENE && !toucheMur() && victime==null) ;
		// controle si un joueur est touch� et s'il n'est pas d�j� mort
		if (victime!=null && !victime.estMort()) {
			// son du joueur touch�
			jeuServeur.envoi(HURT);
			// gestion du gain et de la perte de vie
			victime.perteVie();
			attaquant.gainVie();
			// animation de la victime touch�e
			for (int k=1 ; k<=NBETATSBLESSE ; k++) {
				victime.affiche(BLESSE,  k);
				pause(80, 0);
			}
			// controle si la victime est morte
			if (victime.estMort()) {
				// son du joueur touch�
				jeuServeur.envoi(DEATH);
				// animation de la victime qui meurt
				for (int k=1 ; k<=NBETATSMORT ; k++) {
					victime.affiche(MORT,  k);
					pause(80, 0);
				}
			}else{
				// repositionnement normal de la victime
				victime.affiche(MARCHE,  1);				
			}
			// repositionnement normal de l'attaquant
			attaquant.affiche(MARCHE, 1);
		}
		// la boule a fini son parcourt et redevient invisible
		laboule.getLabel().getjLabel().setVisible(false);		
		jeuServeur.envoi(laboule.getLabel());
	}
	
	/**
	 * Gestion d'une pause (qui servira � r�guler le mouvement de la boule)
	 * @param milli
	 * @param nano
	 */
	private void pause(long milli, int nano) {
		try {
			Thread.sleep(milli, nano);
		} catch (InterruptedException e) {
			System.out.println("Probl�me sur la pause");
		}
	}
	
	/**
	 * Controle si la boule touche un mur
	 * @return
	 */
	private boolean toucheMur() {
		for (Mur unMur : lesMurs) {
			if (attaquant.getBoule().toucheObjet(unMur)) {
				return true ;
			}
		}
		return false ;		
	}

	/**
	 * Controle si la boule touche un joueur
	 * @return
	 */
	private Joueur toucheJoueur() {
		for (Joueur unJoueur : lesJoueurs.values()) {
			if (attaquant.getBoule().toucheObjet(unJoueur)) {
				return unJoueur ;
			}
		}
		return null ;
	}
	
	
}
