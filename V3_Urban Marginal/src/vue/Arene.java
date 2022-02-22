package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;

/**
 * @author Romain Magana
 * @version V1 
 * Arene
 * date : 05/10/21
 */

public class Arene extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Arene() {
		setTitle("Arena");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 487, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
}
