package fr.eni.clinique.ihm.screen;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class EcranDossierMedical extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9124793968888313778L;
	private JPanel contentPane;
	private JTextField txt_CodeAnimal;
	private JTextField txtNom;
	private JTextField txtCouleur;
	private JTextField textSexe;
	private JTextField textRace;
	private JTextField textTatouage;
	private JTextField textNomClient;
	private JTextField txtRemarque;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EcranDossierMedical frame = new EcranDossierMedical();
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
	public EcranDossierMedical() {
		setTitle("Dossier M\u00E9dical");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 430, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(10, 11, 395, 56);
		contentPane.add(panel);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon(EcranDossierMedical.class.getResource("/images/Save24.gif")));
		button.setBounds(278, 11, 34, 33);
		panel.add(button);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(EcranDossierMedical.this, "Modification bien enregistrée");
				
			}
		});
		
		JLabel label = new JLabel("Valider");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(278, 41, 46, 14);
		panel.add(label);
		
		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon(EcranDossierMedical.class.getResource("/images/aim.png")));
		button_1.setBounds(347, 11, 34, 33);
		panel.add(button_1);
		button_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();
				
			}
		});
		
		
		JLabel label_1 = new JLabel("Annuler");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(347, 41, 46, 14);
		panel.add(label_1);
		
		JLabel lblClient = new JLabel("Client");
		lblClient.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblClient.setBounds(10, 78, 46, 14);
		contentPane.add(lblClient);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBounds(10, 92, 152, 40);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		textNomClient = new JTextField();
		textNomClient.setEditable(false);
		textNomClient.setBounds(10, 11, 132, 20);
		panel_1.add(textNomClient);
		textNomClient.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2.setBounds(172, 92, 232, 184);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		txtRemarque = new JTextField();
		txtRemarque.setBounds(10, 11, 212, 162);
		panel_2.add(txtRemarque);
		txtRemarque.setColumns(10);
		
		JLabel lblAntcdentsconsultations = new JLabel("Ant\u00E9c\u00E9dents/Consultations");
		lblAntcdentsconsultations.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAntcdentsconsultations.setBounds(171, 78, 233, 14);
		contentPane.add(lblAntcdentsconsultations);
		
		JLabel lblAnimal = new JLabel("Animal");
		lblAnimal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAnimal.setBounds(10, 138, 46, 14);
		contentPane.add(lblAnimal);
		
		txt_CodeAnimal = new JTextField();
		txt_CodeAnimal.setEditable(false);
		txt_CodeAnimal.setBounds(56, 135, 86, 20);
		contentPane.add(txt_CodeAnimal);
		txt_CodeAnimal.setColumns(10);
		
		txtNom = new JTextField();
		txtNom.setEditable(false);
		txtNom.setBounds(56, 163, 86, 20);
		contentPane.add(txtNom);
		txtNom.setColumns(10);
		
		txtCouleur = new JTextField();
		txtCouleur.setEditable(false);
		txtCouleur.setBounds(56, 194, 58, 20);
		contentPane.add(txtCouleur);
		txtCouleur.setColumns(10);
		
		textSexe = new JTextField();
		textSexe.setEditable(false);
		textSexe.setColumns(10);
		textSexe.setBounds(115, 194, 47, 20);
		contentPane.add(textSexe);
		
		textRace = new JTextField();
		textRace.setEditable(false);
		textRace.setBounds(56, 225, 86, 20);
		contentPane.add(textRace);
		textRace.setColumns(10);
		
		textTatouage = new JTextField();
		textTatouage.setEditable(false);
		textTatouage.setBounds(56, 256, 86, 20);
		contentPane.add(textTatouage);
		textTatouage.setColumns(10);
	}

}
