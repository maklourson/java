package fr.eni.clinique.ihm.screen;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

import fr.eni.clinique.bo.Client;
import fr.eni.clinique.dal.dao.impl.ClientDAOJdbcImpl;
import fr.eni.clinique.dal.exception.DaoException;
import fr.eni.clinique.ihm.controller.ConnexionController;
import fr.eni.clinique.ihm.model.ConnexionModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Panel;

public class EcranRecherche extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3895627998525870940L;
	private JPanel contentPane;
	private JTextField txtNomDuClient;
	private  ConnexionModel connexionModel;
	private ConnexionController connexionController ;
	private List<Client> lesClients = new ArrayList<>();
	private JTable tableau;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EcranRecherche frame = new EcranRecherche();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EcranRecherche() {
		setTitle("R\u00E9sultat de la recherche");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 467, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(22, 11, 388, 72);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtNomDuClient = new JTextField();
		txtNomDuClient.setBounds(10, 25, 276, 20);
		panel.add(txtNomDuClient);
		txtNomDuClient.setToolTipText("Nom du client \u00E0 rechercher");
		txtNomDuClient.setColumns(10);
		JButton btnRecherche = new JButton("");
		btnRecherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtNomDuClient.getText().equals("")){
				   JOptionPane.showMessageDialog(EcranRecherche.this, "Veuillez saisir un nom");
				}
				else{
					
					try {
						
						 connexionModel = new ConnexionModel();
						 connexionController = new ConnexionController(connexionModel);
						 
						 ClientDAOJdbcImpl clientJDBC = new ClientDAOJdbcImpl();
						 List<Client> lesClients = clientJDBC.selectByNom(txtNomDuClient.getText());
						
						tableau = new JTable(connexionModel.loadClientRecherche(lesClients));
						contentPane.add(new JScrollPane(tableau));
						EcranRecherche.this.repaint();
					} catch (DaoException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnRecherche.setBounds(310, 11, 47, 47);
		panel.add(btnRecherche);
		btnRecherche.setIcon(new ImageIcon(EcranRecherche.class.getResource("/images/Rechercher.png")));
		
		
	
	}
}
