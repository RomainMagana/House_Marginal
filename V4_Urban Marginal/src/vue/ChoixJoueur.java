package vue;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.Controle;
import controleur.Global;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * @author Romain Magana
 * @version V1 
 * ChoixJoeur
 * date : 05/10/21
 */

public class ChoixJoueur extends JFrame implements Global {
	
	//Properties
	private JPanel contentPane;
	private JTextField txtPseudo;
	private Integer numPerso;
	private JLabel lblPersonnage;
	private Controle controle;
	
	//Method
	private void souris_normale() {
		contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR ));
	}
	
	private void  souris_doigt() {
		contentPane.setCursor(new Cursor(Cursor.HAND_CURSOR ));
	}
	
	private void lblPrecedent_clic() {
		numPerso = ((numPerso+1)%NBPERSOS)+1;
		affichePerso();
	}
	
	private void lblSuivant_clic() {
		numPerso = (numPerso%NBPERSOS)+1;
		affichePerso();
	}
	
	private void lblGo_clic() {
		if(txtPseudo.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Entrez un pseudo");
			txtPseudo.requestFocus();
		}else {
			controle.evenementVue(this,(PSEUDO + SEPARE + txtPseudo.getText() + SEPARE + numPerso));
		}
	}
	
	private void affichePerso() {
		lblPersonnage.setIcon(new ImageIcon(PERSO + numPerso + MARCHE  +"1d1" + EXTIMAGE));
	}
	
	/**
	 * Create the frame.
	 */
	public ChoixJoueur(Controle controle) {
		setTitle("Choice");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 416, 313);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPrecedent = new JLabel("");
		lblPrecedent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 lblPrecedent_clic();
			}
			public void mouseEntered(MouseEvent e) {
				souris_doigt();
			}
			public void mouseExited(MouseEvent e) {
				 souris_normale();
			}
		});
		
		JLabel lblGo = new JLabel("");
		lblGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblGo_clic();
			}
			public void mouseEntered(MouseEvent e) {
				souris_doigt();
			}
			public void mouseExited(MouseEvent e) {
				 souris_normale();
			}
		});
		
		lblPersonnage = new JLabel("");
		lblPersonnage.setHorizontalAlignment(SwingConstants.CENTER);
		lblPersonnage.setBounds(143, 112, 119, 125);
		contentPane.add(lblPersonnage);
		
		txtPseudo = new JTextField();
		txtPseudo.setBounds(143, 247, 119, 19);
		contentPane.add(txtPseudo);
		txtPseudo.setColumns(10);
		lblGo.setBounds(307, 193, 68, 73);
		contentPane.add(lblGo);
		
		JLabel lblSuivant = new JLabel("");
		lblSuivant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblSuivant_clic();
			}
			public void mouseEntered(MouseEvent e) {
				souris_doigt();
			}
			public void mouseExited(MouseEvent e) {
				 souris_normale();
			}
		});
		lblSuivant.setBounds(287, 144, 47, 51);
		contentPane.add(lblSuivant);
		lblPrecedent.setBounds(61, 144, 40, 51);
		contentPane.add(lblPrecedent);
		
		JLabel lblFond = new JLabel("");
		lblFond.setBounds(0, 0, 400, 275);
		
		lblFond.setIcon(new ImageIcon(FONDCHOIX));
		
		contentPane.add(lblFond);
		//Focus sur le text à l'entrée du programme
		txtPseudo.requestFocus();
		
		numPerso = 1;
		affichePerso();
		this.controle = controle;
	}
}