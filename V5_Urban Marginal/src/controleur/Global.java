package controleur;

/**
 * Regroupement des constantes de l'application
 * @author emds
 *
 */
public interface Global {
	
	public static final Integer PORT = 6666 ;
	
	// fichiers
	public static final String
		SEPARATOR = "//",
		CHEMIN = "media" + SEPARATOR,
		CHEMINFONDS = CHEMIN + "fonds" + SEPARATOR,
		CHEMINPERSOS = CHEMIN + "personnages" + SEPARATOR,
		CHEMINMURS = CHEMIN + "murs" + SEPARATOR,
		CHEMINBOULES = CHEMIN + "boules" + SEPARATOR,
		CHEMINSONS = CHEMIN + "sons/",
		EXTIMAGE = ".gif" ;

	// images
	public static final String
		FONDCHOIX = CHEMINFONDS + "fondchoix.jpg",	
		FONDARENE = CHEMINFONDS+"fondarene.gif",
		PERSO = CHEMINPERSOS + "perso",
		MURD = CHEMINMURS + "mur1.gif",
		MURG = CHEMINMURS + "mur0.gif",
		TABLE = CHEMINMURS + "table.gif",
		BOULE = CHEMINBOULES + "boule.gif";
	
	// sons
	public static final String
		SONPRECEDENT = CHEMINSONS + "precedent.wav", // sur le clic du bouton précédent
		SONSUIVANT = CHEMINSONS + "suivant.wav", // sur le clic du bouton suivant
		SONGO = CHEMINSONS + "go.wav", // sur le clic du bouton go
		SONWELCOME = CHEMINSONS + "welcome.wav", // à l'entrée de la frame ChoixJoueur
		SONAMBIANCE = CHEMINSONS + "ambiance.wav" ; // son d'ambiance dans tout le jeu
	
	public static final Integer
		FIGHT = 0,
		HURT = 1,
		DEATH = 2 ;
	public static final String[] 
		SON = {"fight.wav", "hurt.wav", "death.wav"};
	
	// personnages
	public static final int
		GAUCHE = 0,
		DROITE = 1,
		HAUT = 2,
		BAS = 3,
		TIRE = 4,
		NBETATSMARCHE = 4,
		NBETATSBLESSE = 2,
		NBETATSMORT = 2,
		LEPAS = 10, // nombre de pixels de déplacement d'un pas
		NBPERSOS = 4,
		H_MESSAGE = 8,
		H_PERSO = 27,
		L_PERSO = 20; // 17 normalement mais pour le design
	public static final String
		MARCHE = "marche",
		BLESSE = "touche",
		MORT = "mort" ;
	
	// messages serveurs
	public static final String SEPARE = "~" ;
	public static final int
		PSEUDO = 0,
		CHAT = 1,
		ACTION = 2 ;
	
	// tailles dans frames
	public static final int
		H_ARENE = 624,
		L_ARENE = 800,
		H_CHAT = H_ARENE,
		L_CHAT = 500,
		H_SAISIE = 25,
		MARGE = 5 ; // écarts entre les objets
	
	// table
		public static final int
			H_TABLE = 80, // hauteur d'une table
			L_TABLE = 110, // largeur d'une table
			POSXTABLE = 405, // position X table
			POSYTABLE = 247; // position Y table
	
	// murs
	public static final int
		NBMURS = 4, // nombre de murs
		H_MUR = 80, // hauteur du mur
		L_MUR = 40; // largeur du mur
	public static int[]
		POSXMURS = {360, 516, 500, POSXTABLE}, // position X mur
		POSYMURS = {220,260,400, POSYTABLE}; // position Y mur
	
	// boule
	public static final int
		H_BOULE = 17, // hauteur de la boule
		L_BOULE = 17 ; // largeur de la boule
}