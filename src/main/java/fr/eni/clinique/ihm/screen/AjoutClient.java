package fr.eni.clinique.ihm.screen;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import fr.eni.clinique.bll.exception.BLLException;
import fr.eni.clinique.bo.Client;

import fr.eni.clinique.ihm.controller.ConnexionController;
import fr.eni.clinique.ihm.model.ConnexionModel;

public class AjoutClient extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5584980528885898448L;
	private JPanel contentPane;
	private JTextField txtNomCli;
	private JTextField txtPrenomCli;
	private JTextField txtAdress1;
	private JTextField txtAdress2;
	private JTextField txtCodePost;
	private JTextField txtVille;
	private ConnexionModel connexionModel;
	private ConnexionController connexionController ;
	private Integer codeClient = 0;
	private JTextField txtTel;
	private JTextField txtRemq;
	private JTextField txtAssur;
	private JButton btnAnnuler;
	private JButton btnValider;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnexionModel connexionModel = new ConnexionModel();
					ConnexionController connexionController = new ConnexionController(connexionModel);
					new AjoutClient(connexionController, connexionModel).setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AjoutClient(ConnexionController connexionController, ConnexionModel connexionModel) {
		this.connexionController=connexionController;
		this.connexionModel=connexionModel;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 327);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setVisible(true);
		setLocationRelativeTo(null);
		setUp();
		setContentPane(contentPane);
		setTitle("Enregistrement d'un nouveau client");
	}	
		private void setUp() {
	    	contentPane.setLayout(null);
			
			JLabel lblNom = new JLabel("Nom");
			lblNom.setBounds(10, 69, 57, 14);
			contentPane.add(lblNom);
			
			JLabel lblPrnom = new JLabel("Pr\u00E9nom");
			lblPrnom.setBounds(10, 100, 64, 14);
			contentPane.add(lblPrnom);
			
			JLabel lblAdresse = new JLabel("Adresse");
			lblAdresse.setBounds(10, 131, 64, 14);
			contentPane.add(lblAdresse);
			
			JLabel lblCodePostal = new JLabel("Code Postal");
			lblCodePostal.setBounds(10, 193, 70, 14);
			contentPane.add(lblCodePostal);
			
			JLabel lblVille = new JLabel("Ville");
			lblVille.setBounds(10, 224, 64, 14);
			contentPane.add(lblVille);
			
			txtNomCli = new JTextField();
			txtNomCli.setBounds(90, 66, 110, 20);
			contentPane.add(txtNomCli);
			txtNomCli.setColumns(10);
			
			txtPrenomCli = new JTextField();
			txtPrenomCli.setBounds(90, 97, 110, 20);
			contentPane.add(txtPrenomCli);
			txtPrenomCli.setColumns(10);
			
			txtAdress1 = new JTextField();
			txtAdress1.setBounds(90, 128, 110, 20);
			contentPane.add(txtAdress1);
			txtAdress1.setColumns(10);
			
			txtAdress2 = new JTextField();
			txtAdress2.setBounds(90, 159, 110, 20);
			contentPane.add(txtAdress2);
			txtAdress2.setColumns(10);
			
			txtCodePost = new JTextField();
			txtCodePost.setBounds(90, 190, 110, 20);
			contentPane.add(txtCodePost);
			txtCodePost.setColumns(10);
			
			txtVille = new JTextField();
			txtVille.setBounds(90, 221, 110, 20);
			contentPane.add(txtVille);
			txtVille.setColumns(10);
			
			JLabel lblTl = new JLabel("T\u00E9l");
			lblTl.setBounds(236, 94, 50, 14);
			contentPane.add(lblTl);
			
			JLabel lblRemarque = new JLabel("Remarque");
			lblRemarque.setBounds(236, 144, 57, 14);
			contentPane.add(lblRemarque);
			
			txtTel = new JTextField();
			txtTel.setBounds(309, 91, 110, 20);
			contentPane.add(txtTel);
			txtTel.setColumns(10);
			
			txtRemq = new JTextField();
			txtRemq.setBounds(309, 119, 110, 65);
			contentPane.add(txtRemq);
			txtRemq.setColumns(10);
			
			JLabel lblAssurance = new JLabel("Assurance");
			lblAssurance.setBounds(236, 69, 72, 14);
			contentPane.add(lblAssurance);
			
			txtAssur = new JTextField();
			txtAssur.setBounds(309, 66, 110, 20);
			contentPane.add(txtAssur);
			txtAssur.setColumns(10);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			panel_1.setBounds(50, 10, 372, 28);
			contentPane.add(panel_1);
			
			JLabel lblAjouterUnNouveau = new JLabel("AJOUTER UN NOUVEAU CLIENT");
			panel_1.add(lblAjouterUnNouveau);
			
			JButton btnValider = new JButton("");
			btnValider.setIcon(new ImageIcon(AjoutClient.class.getResource("/images/Save24.gif")));
			btnValider.setBounds(283, 252, 57, 33);
			contentPane.add(btnValider);
			btnValider.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Client c = retourSaisi();
					if(txtNomCli.getText().equals("") || txtPrenomCli.getText().equals("") || txtAdress1.getText().equals("") || txtAdress2.getText().equals("") || txtCodePost.getText().equals("") || txtVille.getText().equals("") || txtTel.getText().equals("") || txtRemq.getText().equals("") || txtAssur.getText().equals("")){
						JOptionPane.showMessageDialog(AjoutClient.this, "Tous les champs sont obligatoires !");
						
					}else{
					try {
						connexionController.AjoutClient(c);
						JOptionPane.showMessageDialog(AjoutClient.this, "Client : " + c.getNomClient()+ " " + c.getPrenomClient()+ " bien enregistré");
						dispose();
					} 
					catch (BLLException e1) {
						e1.printStackTrace();
					}
				}}
			});
			JButton btnAnnuler = new JButton("");
			btnAnnuler.setIcon(new ImageIcon(AjoutClient.class.getResource("/images/aim.png")));
			btnAnnuler.setBounds(367, 252, 57, 33);
			contentPane.add(btnAnnuler);
			btnAnnuler.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					
				}
			});
		
		}
		
		private Client retourSaisi() {
			
			Client client = new Client();
			
			client.setCodeClient(codeClient);
			
			client.setNomClient(txtNomCli.getText().trim());
			
			client.setPrenomClient(txtNomCli.getText().trim());
			
			client.setAdresse1(txtAdress1.getText().trim());
			
			client.setAdresse2(txtAdress2.getText().trim());
			
			client.setCodePostal(txtCodePost.getText().trim());
			
			client.setVille(txtVille.getText().trim());
			
			client.setNumTel(txtTel.getText().trim());
			
			client.setRemarque(txtRemq.getText().trim());
			
			client.setAssurance(txtAssur.getText());	
			
			client.setArchive(false);
			
			return client;
		}
		
		
	}


