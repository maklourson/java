package fr.eni.clinique.ihm.screen;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import fr.eni.clinique.dal.dao.AnimalDAO;
import fr.eni.clinique.dal.dao.ClientDAO;
import fr.eni.clinique.dal.exception.DaoException;
import fr.eni.clinique.dal.factory.DaoFactory;
import fr.eni.clinique.ihm.controller.ConnexionController;
import fr.eni.clinique.ihm.model.ConnexionModel;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EcranPrincipalClient extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5989549587705837199L;
	private JPanel contentPane;
	private JTextField txtCodeClient;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtAdress1;
	private JTextField txtAdress2;
	private JTextField txtCodePostal;
	private JTextField txtVille;
	private  ConnexionModel connexionModel;
	private ConnexionController connexionController ;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EcranPrincipalClient frame = new EcranPrincipalClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public EcranPrincipalClient() {
		
		setTitle("Clients");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 734, 588);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		 connexionModel = new ConnexionModel();
		 connexionController = new ConnexionController(connexionModel);
		 
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(10, 11, 698, 73);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblRechercher = new JLabel("Rechercher");
		lblRechercher.setBounds(10, 50, 74, 14);
		panel.add(lblRechercher);
		
		JLabel lblAjouter = new JLabel("Ajouter");
		lblAjouter.setBounds(254, 50, 46, 14);
		panel.add(lblAjouter);
		
		JLabel lblSupprimer = new JLabel("Supprimer");
		lblSupprimer.setBounds(326, 50, 63, 14);
		panel.add(lblSupprimer);
		
		JButton btnAjoutCli = new JButton("+");
		btnAjoutCli.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAjoutCli.setBounds(251, 11, 46, 35);
		panel.add(btnAjoutCli);
		btnAjoutCli.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			AjoutClient ajoutClient = new AjoutClient(connexionController, connexionModel);
			ajoutClient.setVisible(true);
				
			}
		});
			
		JButton btnSuppriCli = new JButton("-");
		btnSuppriCli.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSuppriCli.setBounds(326, 11, 46, 35);
		panel.add(btnSuppriCli);
		btnSuppriCli.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int choix = JOptionPane.showConfirmDialog(EcranPrincipalClient.this,"Êtes-vous sûr de vouloir supprimer ce client ?", "Suppression Client",JOptionPane.OK_CANCEL_OPTION);
				if (choix ==1){
					ClientDAO client = new DaoFactory().clientDAO();
					client.deleteById(Integer.parseInt(txtCodeClient.getText()));
				}
			}
		});
		
		JLabel lblValider = new JLabel("Valider");
		lblValider.setBounds(572, 51, 46, 14);
		panel.add(lblValider);
		
		JLabel lblAnnuler = new JLabel("Annuler");
		lblAnnuler.setBounds(642, 50, 46, 14);
		panel.add(lblAnnuler);
		
		JButton btnValider = new JButton("");
		btnValider.setIcon(new ImageIcon(EcranPrincipalClient.class.getResource("/images/Save24.gif")));
		btnValider.setBounds(563, 12, 46, 35);
		panel.add(btnValider);
		btnValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(EcranPrincipalClient.this, "Modification bien enregistrée");
				
			}
		});
		
		JButton btnAnnuler = new JButton("");
		btnAnnuler.setIcon(new ImageIcon(EcranPrincipalClient.class.getResource("/images/aim.png")));
		btnAnnuler.setBounds(642, 11, 46, 35);
		panel.add(btnAnnuler);
		btnAnnuler.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();
				
			}
		});
		
		JButton btnRechercher = new JButton("");
		btnRechercher.setIcon(new ImageIcon(EcranPrincipalClient.class.getResource("/images/Rechercher.png")));
		btnRechercher.setBounds(10, 11, 46, 35);
		panel.add(btnRechercher);
		btnRechercher.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new EcranRecherche().setVisible(true);
				
			}
		});
		
		JLabel lblCode = new JLabel("Code");
		lblCode.setBounds(10, 95, 46, 14);
		contentPane.add(lblCode);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(10, 130, 46, 14);
		contentPane.add(lblNom);
		
		JLabel lblPrnom = new JLabel("Pr\u00E9nom");
		lblPrnom.setBounds(10, 155, 46, 14);
		contentPane.add(lblPrnom);
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setBounds(10, 180, 46, 14);
		contentPane.add(lblAdresse);
		
		JLabel lblCodePostal = new JLabel("Code Postal");
		lblCodePostal.setBounds(10, 244, 110, 14);
		contentPane.add(lblCodePostal);
		
		JLabel lblVille = new JLabel("Ville");
		lblVille.setBounds(10, 272, 46, 14);
		contentPane.add(lblVille);
		
		txtCodeClient = new JTextField();
		txtCodeClient.setBounds(86, 95, 150, 20);
		contentPane.add(txtCodeClient);
		txtCodeClient.setColumns(10);
		
		txtNom = new JTextField();
		txtNom.setBounds(86, 127, 150, 20);
		contentPane.add(txtNom);
		txtNom.setColumns(10);
		
		txtPrenom = new JTextField();
		txtPrenom.setBounds(86, 152, 150, 20);
		contentPane.add(txtPrenom);
		txtPrenom.setColumns(10);
		
		txtAdress1 = new JTextField();
		txtAdress1.setBounds(86, 177, 150, 20);
		contentPane.add(txtAdress1);
		txtAdress1.setColumns(10);
		
		txtAdress2 = new JTextField();
		txtAdress2.setBounds(86, 208, 150, 20);
		contentPane.add(txtAdress2);
		txtAdress2.setColumns(10);
		
		txtCodePostal = new JTextField();
		txtCodePostal.setBounds(86, 241, 150, 20);
		contentPane.add(txtCodePostal);
		txtCodePostal.setColumns(10);
		
		txtVille = new JTextField();
		txtVille.setBounds(86, 269, 150, 20);
		contentPane.add(txtVille);
		txtVille.setColumns(10);
		
		JPanel panel_listeAnimaux = new JPanel();
		panel_listeAnimaux.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_listeAnimaux.setBounds(246, 95, 462, 443);
		contentPane.add(panel_listeAnimaux);
		panel_listeAnimaux.setLayout(null);
		
		JButton btnAjoutAnimal = new JButton("+");
		btnAjoutAnimal.setBounds(273, 381, 46, 35);
		panel_listeAnimaux.add(btnAjoutAnimal);
		btnAjoutAnimal.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAjoutAnimal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new EcranAnimal(connexionController, connexionModel).setVisible(true);
				
			}
		});
		JLabel lblAjouter_1 = new JLabel("Ajouter");
		lblAjouter_1.setBounds(273, 418, 83, 14);
		panel_listeAnimaux.add(lblAjouter_1);
		
		JButton btnSuppriAnimal = new JButton("-");
		btnSuppriAnimal.setBounds(341, 381, 46, 35);
		panel_listeAnimaux.add(btnSuppriAnimal);
		btnSuppriAnimal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int choix = JOptionPane.showConfirmDialog(EcranPrincipalClient.this,"Êtes-vous sûr de vouloir supprimer cet animal ?", "Suppression Animal",JOptionPane.OK_CANCEL_OPTION);
				if (choix ==1){
					AnimalDAO animal = new DaoFactory().animalDAO();
					try {
						animal.deleteById(Integer.parseInt(txtCodeClient.getText()));
					} catch (NumberFormatException e1) {
					
						e1.printStackTrace();
					} catch (DaoException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnSuppriAnimal.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblSupprimer_1 = new JLabel("Supprimer");
		lblSupprimer_1.setBounds(341, 418, 83, 14);
		panel_listeAnimaux.add(lblSupprimer_1);
		
		JButton btnEditAnimal = new JButton("");
		btnEditAnimal.setBounds(406, 381, 46, 35);
		panel_listeAnimaux.add(btnEditAnimal);
		btnEditAnimal.setIcon(new ImageIcon(EcranPrincipalClient.class.getResource("/images/editer.png")));
		btnEditAnimal.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEditAnimal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new EcranAnimal(connexionController, connexionModel).setVisible(true);
				
			}
		});
		
		JLabel lblEditer = new JLabel("Editer");
		lblEditer.setBounds(406, 418, 78, 14);
		panel_listeAnimaux.add(lblEditer);
		
		JButton btnCliPrec = new JButton("");
		btnCliPrec.setIcon(new ImageIcon(EcranPrincipalClient.class.getResource("/images/Back24.gif")));
		btnCliPrec.setBounds(86, 316, 46, 23);
		contentPane.add(btnCliPrec);
		
		JButton btnCliSuiv = new JButton("");
		btnCliSuiv.setIcon(new ImageIcon(EcranPrincipalClient.class.getResource("/images/Forward24.gif")));
		btnCliSuiv.setBounds(190, 316, 46, 23);
		contentPane.add(btnCliSuiv);
	}
}
