package modele;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.Global;

/**
 * Gestion des murs
 * @author emds
 *
 */
public class Mur extends Objet implements Global {

	/**
	 * Constructeur
	 */
	public Mur(int valeurX, int valeurY) {
		
 		//calcul position aléatoire du mur
//		posX = (int) Math.round(Math.random() * (L_ARENE - L_MUR)) ;
//		posY = (int) Math.round(Math.random() * (H_ARENE - H_MUR)) ;
		posX = (int) valeurX;
		posY = (int) valeurY;
		// création du label pour ce mur (pas d'importance pour le rang dans le panel, d'où -1)
		label = new Label(-1, new JLabel());
		// caractéristiques du mur (centrage, position, image)
		label.getjLabel().setHorizontalAlignment(SwingConstants.CENTER);
		label.getjLabel().setVerticalAlignment(SwingConstants.CENTER);
		// test si c'est une table
		if(posX == POSXTABLE && posY == POSYTABLE) {
			label.getjLabel().setBounds(posX, posY, L_TABLE, H_TABLE);
			label.getjLabel().setIcon(new ImageIcon(TABLE));
		}else {
			label.getjLabel().setBounds(posX, posY, L_MUR, H_MUR);
			// Le canapé se voit orrienté en fonction de sa position
			if(posX < L_ARENE/2) {
				label.getjLabel().setIcon(new ImageIcon(MURG));
			}else {
				label.getjLabel().setIcon(new ImageIcon(MURD));
			}
		}
	}
}