package controleur;

/**
 * @author Romain Magana
 * @version V1 
 * Global
 * date : 16/10/21
 */

//On initialise toutes les constantes dans ce fichier
public interface Global {
	
	public static final String 
		// Chemins relatifs PERSONNAGE
		SEPARATOR = "//",
		CHEMIN = "media" + SEPARATOR,
		CHEMINFONDS = CHEMIN + "fonds" + SEPARATOR,
		FONDCHOIX = CHEMINFONDS + "fondchoix.jpg",
		CHEMINPERSOS = CHEMIN + "personnages" + SEPARATOR,
		PERSO = CHEMINPERSOS + "perso",
		EXTIMAGE = ".gif",
		
		// Les différents états
		MARCHE = "marche", 
		BLESSE = "touche",
		MORT = "mort",
		
		// Caractère de séparation (volontairement rare)
		SEPARE = "~",
		
		// Chemis relatifs ARENE
		FONDARENE = CHEMINFONDS + "fondarene.jpg",
		CHEMINMURS = CHEMIN + "murs" + SEPARATOR,
		MUR = CHEMINMURS + "mur.gif";
	
	public static final int
		PORT = 6666,
		
		// Pour la direction
		GAUCHE = 0, 
		DROITE = 1,
		
		// Nombre de personnages
		NBPERSOS = 3,
		
		// Taille de l'image du personnage
		H_PERSO = 44,
		L_PERSO = 39,
		
	    // Le message contiendra le pseudo et numéro du personnage
		PSEUDO = 0,
	
		//Positionnements absolute dans la fenetre ARENE
		H_ARENE = 600,
		L_ARENE = 800,
		H_CHAT = 200,
		H_SAISIE = 25,
		
		// Elle va servir pour les écarts entre différents objets
		MARGE = 5,
	
		//Nombre/Hauteur/Largeur du mur
		NBMURS = 20,
		H_MUR = 35,
		L_MUR = 34;
}
