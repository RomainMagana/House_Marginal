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
		//Chemins relatifs
		SEPARATOR = "//",
		CHEMIN = "media" + SEPARATOR,
		CHEMINFONDS = CHEMIN + "fonds" + SEPARATOR,
		FONDCHOIX = CHEMINFONDS + "fondchoix.jpg",
		CHEMINPERSOS = CHEMIN + "personnages" + SEPARATOR,
		PERSO = CHEMINPERSOS + "perso",
		EXTIMAGE = ".gif",
		
		//Les différents états
		MARCHE = "marche", 
		BLESSE = "touche",
		MORT = "mort",
		
		//Caractère de séparation (volontairement rare)
		SEPARE = "~",
		
		//Fond de l'arene
		FONDARENE = CHEMINFONDS + "fondarene.jpg";
	
	public static final Integer
		PORT = 6666,
		
		// pour la direction
		GAUCHE = 0, 
		DROITE = 1,
		
		// nombre de personnages
		NBPERSOS = 3,
		
		// taille de l'image du personnage
		H_PERSO = 44,
		L_PERSO = 39,
	
	    // le message contiendra le pseudo et numéro du personnage
		PSEUDO = 0,
	
		//Positionnements absolute dans la fenetre ARENE
		H_ARENE = 600,
		L_ARENE = 800,
		H_CHAT = 200,
		H_SAISIE = 25,
		
		// elle va servir pour les écarts entre différents objets
		MARGE = 5; 
}
