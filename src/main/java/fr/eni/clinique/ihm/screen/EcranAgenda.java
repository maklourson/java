package fr.eni.clinique.ihm.screen;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import fr.eni.clinique.bll.exception.BLLException;
import fr.eni.clinique.bll.factory.ManagerFactory;
import fr.eni.clinique.bll.manager.LoginMger;
import fr.eni.clinique.bo.Personnel;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class EcranAgenda extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8314030710747042747L;
	/**
	 * 
	 */

	private JPanel contentPane;
	private JComboBox<String> cbx_veterinaire;
	private LoginMger loginManager = ManagerFactory.loginMger();
	private JComboBox<String> createComboBox(List<Personnel> personnels, String tooltip) {
        JComboBox<String> combo = new JComboBox<>();
        combo.setToolTipText(tooltip);
        for (Personnel personnel : personnels) {
        	if(personnel.getRole().equals("Vet")){
            combo.addItem(personnel.getNom());
        	}
        }
        return combo;
    }


	/**
	 * Create the frame.
	 */
	public EcranAgenda() {
		setTitle("Agenda");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDe = new JLabel("De");
		lblDe.setBounds(10, 11, 46, 14);
		contentPane.add(lblDe);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(10, 25, 414, 82);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblVtrinaire = new JLabel("V\u00E9t\u00E9rinaire");
		lblVtrinaire.setBounds(10, 27, 70, 14);
		panel.add(lblVtrinaire);
		
		
		List<Personnel> personnels;
		try {
			personnels = loginManager.toutLePersonnel();
			for (Personnel personnel : personnels) {
			
					cbx_veterinaire = createComboBox(personnels, "Selectionner un veterinaire");
				
			}
		} catch (BLLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		cbx_veterinaire.setBounds(74, 24, 136, 20);
		panel.add(cbx_veterinaire);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(236, 27, 46, 14);
		panel.add(lblDate);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBounds(10, 114, 414, 136);
		contentPane.add(panel_1);
		
		JButton Bt_dossierMedical = new JButton("");
		Bt_dossierMedical.setBounds(365, 261, 46, 23);
		contentPane.add(Bt_dossierMedical);
		Bt_dossierMedical.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new EcranDossierMedical().setVisible(true);
			}
		});
		
		JLabel lblDossierMdical = new JLabel("Dossier m\u00E9dical");
		lblDossierMdical.setBounds(346, 286, 124, 14);
		contentPane.add(lblDossierMdical);
	}

}
