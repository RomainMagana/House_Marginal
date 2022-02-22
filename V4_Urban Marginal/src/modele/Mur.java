package modele;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.Global;

/**
 * @author Romain Magana
 * @version V1 
 * Objet
 * date : 19/10/21
 */


public class Mur extends Objet implements Global{
	
	//Constructeur
	public Mur() {
		super.posX = (int) Math.round(Math.random()*(L_ARENE - L_MUR));
		super.posY = (int) Math.round(Math.random()*(H_ARENE - H_MUR));
		
		super.label = new Label(-1, new JLabel());
		
		//Utilisation du getter JLabel pour centrer horizontalement le contenu d'un JLabel
		super.label.getjLabel().setHorizontalAlignment(SwingConstants.CENTER);
		super.label.getjLabel().setVerticalAlignment(SwingConstants.CENTER);
		super.label.getjLabel().setBounds(posX, posY, L_MUR, H_MUR);
		super.label.getjLabel().setIcon(new ImageIcon(MUR));
	}
}
