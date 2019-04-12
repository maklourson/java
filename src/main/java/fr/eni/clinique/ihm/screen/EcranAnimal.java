package fr.eni.clinique.ihm.screen;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import fr.eni.clinique.bll.exception.BLLException;
import fr.eni.clinique.bll.factory.ManagerFactory;
import fr.eni.clinique.bll.manager.LoginMger;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Race;
import fr.eni.clinique.ihm.controller.ConnexionController;
import fr.eni.clinique.ihm.model.ConnexionModel;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;

public class EcranAnimal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8248264425509804877L;
	private LoginMger loginManager = ManagerFactory.loginMger();
	private JPanel contentPane;
	private JTextField txt_codeAnimal;
	private JTextField txt_nomAnimal;
	private JTextField txt_Couleur;
	private JComboBox<String> comboBoxClient;
	JComboBox<String> cbx_Sexe;
	JComboBox<String> cbx_espèce;
	JComboBox<String> cbx_Race;
	JComboBox<String> cbx_tatouage;
	private ConnexionModel connexionModel;
	private ConnexionController connexionController ;
	private Integer codeAnimal=0;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnexionModel connexionModel = new ConnexionModel();
					ConnexionController connexionController = new ConnexionController(connexionModel);
					
					new EcranAnimal(connexionController, connexionModel).setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EcranAnimal(ConnexionController connexionController, ConnexionModel connexionModel) {
		
		this.connexionController=connexionController;
		this.connexionModel=connexionModel;
		
		setTitle("Animaux");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 390, 312);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(10, 11, 365, 56);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton valider = new JButton("");
		valider.setBounds(225, 11, 57, 33);
		valider.setIcon(new ImageIcon(EcranAnimal.class.getResource("/images/Save24.gif")));
		panel.add(valider);
		valider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Animal a = retourSaisi();
				try {
					connexionController.AjoutAnimal(a);
				} 
				catch (BLLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		
		JLabel lblValider = new JLabel("Valider");
		lblValider.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblValider.setBounds(235, 41, 46, 14);
		panel.add(lblValider);
		
		
		JButton annuler = new JButton("");
		annuler.setIcon(new ImageIcon(EcranAnimal.class.getResource("/images/aim.png")));
		annuler.setBounds(302, 11, 57, 33);
		panel.add(annuler);
		annuler.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JLabel lblAnnuler = new JLabel("Annuler");
		lblAnnuler.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAnnuler.setBounds(312, 41, 46, 14);
		panel.add(lblAnnuler);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBounds(10, 87, 354, 45);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		comboBoxClient = new JComboBox<String>();
		comboBoxClient.setBounds(10, 11, 334, 20);
		panel_1.add(comboBoxClient);
		
		try {
			List<Client> clients = loginManager.tousLesClients();
			for (Client client : clients) {
				comboBoxClient.addItem(client.getNomClient()+" - "+client.getPrenomClient());		
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
		 
		JLabel lblCode = new JLabel("Code");
		lblCode.setBounds(10, 143, 46, 14);
		contentPane.add(lblCode);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(10, 168, 46, 14);
		contentPane.add(lblNom);
		
		JLabel lblCouleur = new JLabel("Couleur");
		lblCouleur.setBounds(10, 193, 46, 14);
		contentPane.add(lblCouleur);
		
		JLabel lblEspce = new JLabel("Esp\u00E8ce");
		lblEspce.setBounds(10, 218, 46, 14);
		contentPane.add(lblEspce);
		
		JLabel lblTatouage = new JLabel("Tatouage");
		lblTatouage.setBounds(10, 243, 46, 14);
		contentPane.add(lblTatouage);
		
		JLabel lblRace = new JLabel("Race");
		lblRace.setBounds(160, 218, 32, 14);
		contentPane.add(lblRace);
		
		cbx_espèce = new JComboBox<String>();
		cbx_espèce.setBounds(65, 215, 86, 20);
		contentPane.add(cbx_espèce);
		try {
			List<Race> races = loginManager.toutesLesRaces();
			for (Race race : races) {
				cbx_espèce.addItem(race.getEspece());		
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		cbx_Race = new JComboBox<String>();
		cbx_Race.setBounds(188, 215, 88, 20);
		contentPane.add(cbx_Race);
		try {
			List<Race> races = loginManager.toutesLesRaces();
			for (Race race : races) {
				cbx_Race.addItem(race.getRace());		
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
		 
		cbx_Sexe = new JComboBox<String>();
		cbx_Sexe.setBounds(274, 165, 74, 20);
		cbx_Sexe.addItem("Femelle");
		cbx_Sexe.addItem("Mâle");
		contentPane.add(cbx_Sexe);
		
		txt_codeAnimal = new JTextField();
		txt_codeAnimal.setEditable(false);
		txt_codeAnimal.setBounds(65, 143, 86, 20);
		contentPane.add(txt_codeAnimal);
		txt_codeAnimal.setColumns(10);
		
		txt_nomAnimal = new JTextField();
		txt_nomAnimal.setBounds(65, 165, 199, 20);
		contentPane.add(txt_nomAnimal);
		txt_nomAnimal.setColumns(10);
		
		txt_Couleur = new JTextField();
		txt_Couleur.setBounds(65, 190, 199, 20);
		contentPane.add(txt_Couleur);
		txt_Couleur.setColumns(10);
		
		cbx_tatouage = new JComboBox<String>();
		cbx_tatouage.setBounds(66, 240, 86, 20);
		contentPane.add(cbx_tatouage);
		cbx_tatouage.addItem("Oui");
		cbx_tatouage.addItem("Non");
		
		JLabel lblClient = new JLabel("Client :");
		lblClient.setBounds(10, 67, 46, 21);
		contentPane.add(lblClient);
		lblClient.setFont(new Font("Tahoma", Font.BOLD, 11));
	}
	
	private Animal retourSaisi() {
		
		Animal animal = new Animal();
		
		animal.setCodeAnimal(codeAnimal);
		animal.setNomAnimal(txt_nomAnimal.getText().trim());
		
		if(cbx_Sexe.getSelectedIndex()==0){
			animal.setSexe("F");
		}
		else if(cbx_Sexe.getSelectedIndex()==1){
			animal.setSexe("M");
		}
		animal.setCouleur(txt_Couleur.getText().trim());
		
		animal.setRace((String) cbx_Race.getSelectedItem());
		
		animal.setEspece((String) cbx_espèce.getSelectedItem());
		
		animal.setCodeClient(comboBoxClient.getSelectedIndex());
	
		animal.setTatouage((String) cbx_tatouage.getSelectedItem());
		
		animal.setAntecedents("");
		
		animal.setArchive(false);
		
		return animal;
	}
}
