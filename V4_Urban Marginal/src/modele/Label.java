package modele;

import java.io.Serializable;
import javax.swing.JLabel;

/**
 * @author Romain Magana
 * @version V1 
 * Label
 * date : 19/10/21
 */

//Transfer via le réseau, pour sérialiser on utilise Serializable
public class Label implements Serializable {
	
	//Properties
	private static Integer nbLabel;
	private Integer numLabel;
	private JLabel jLabel;
	
	//Constructor
	public Label(Integer numLabel, JLabel jLabel) {
		this.jLabel = jLabel;
		this.numLabel = numLabel;
	}
	
	/**
	 * @return the numLabel
	 */
	public Integer getNumLabel() {
		return numLabel;
	}

	/**
	 * @return the jLabel
	 */
	public JLabel getjLabel() {
		return jLabel;
	}
	
	/**
	 * @return the nbLabel
	 */
	public static Integer getNbLabel() {
		return nbLabel;
	}

	/**
	 * @param nbLabel the nbLabel to set
	 */
	public static void setNbLabel(Integer nbLabel) {
		Label.nbLabel = nbLabel;
	}
	
}
