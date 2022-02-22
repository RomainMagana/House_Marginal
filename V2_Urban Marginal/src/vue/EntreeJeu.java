package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.Controle;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Romain Magana
 * @version V1 
 * EntreeJeu
 * date : 28/09/21
 */

public class EntreeJeu extends JFrame {
//def properties
	private JPanel contentPane;
	private JTextField txtIp;
	private Controle controle;

	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EntreeJeu frame = new EntreeJeu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	
	private void btnStart_clic() {
		controle.evenementVue(this, "serveur");
	}
	private void btnConnect_clic() {
		controle.evenementVue(this, txtIp.getText());
	}
	private void btnExit_clic() {
		System.exit(0);
	}
	
	/**
	 * Create the frame.
	 * @param controle 
	 */
	public EntreeJeu(Controle controle) {
		setTitle("Urban Marginal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 411, 257);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/**
		* clic sur le bouton Start pour lancer le serveur
		*/
		JButton btnStart = new JButton("Start");
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnStart_clic() ;
			}
		});
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnStart.setBounds(283, 45, 81, 19);
		contentPane.add(btnStart);
		
		/**
		* Clic sur le bouton Connect se connecter au serveur
		*/
		JButton btnConnect = new JButton("Connect");
		btnConnect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnConnect_clic();
			}
		});
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConnect.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnConnect.setBounds(283, 114, 81, 19);
		contentPane.add(btnConnect);
		
		/**
		* clic sur le bouton Exit pour quitter l'application
		*/
		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnExit_clic() ;
			}
		});
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnExit.setBounds(283, 162, 81, 19);
		contentPane.add(btnExit);
		
		txtIp = new JTextField();
		txtIp.setText("127.0.0.1");
		txtIp.setBounds(108, 114, 68, 19);
		contentPane.add(txtIp);
		txtIp.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Start a server");
		lblNewLabel.setBounds(46, 48, 145, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblConnectAnExisting = new JLabel("Connect an existing server : ");
		lblConnectAnExisting.setBounds(46, 97, 186, 13);
		contentPane.add(lblConnectAnExisting);
		
		JLabel lblNewLabel_1_1 = new JLabel("IP server");
		lblNewLabel_1_1.setBounds(46, 117, 60, 13);
		contentPane.add(lblNewLabel_1_1);
		
		this.controle = controle;
		
	}
}
