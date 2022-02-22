package modele;

/**
 * @author Romain Magana
 * @version V1 
 * Objet
 * date : 19/10/21
 */

public abstract class Objet {
	
	//Properties
	protected Integer posY;
	protected Label label;
	protected Integer posX;
	
	/**
	 * @return the posX
	 */
	public Integer getPosX() {
		return posX;
	}
	
	/**
	 * @return the posY
	 */
	public Integer getPosY() {
		return posY;
	}
	
	/**
	 * @return the label
	 */
	public Label getLabel() {
		return label;
	}	
}
