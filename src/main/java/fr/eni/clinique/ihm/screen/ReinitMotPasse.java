package fr.eni.clinique.ihm.screen;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.eni.clinique.bll.exception.BLLException;
import fr.eni.clinique.bll.factory.ManagerFactory;
import fr.eni.clinique.bll.manager.LoginMger;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.ihm.controller.ConnexionController;
import fr.eni.clinique.ihm.model.ConnexionModel;

public class ReinitMotPasse extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LoginMger loginManager = ManagerFactory.loginMger();
	private JPanel mainPanel;
	private JLabel personnelLabel;
	private JLabel motPasseLabel;
	private JLabel motPasseConfirmeLabel;
	private JComboBox<String> personnelCombo;
	private JPasswordField motPasse;
	private JPasswordField motPasseConfirme;
	
	private JButton validerBoutton;
	
	private Font defaultLabelFont = new Font("Arial", Font.BOLD, 14);
	
	private ConnexionModel connexionModel;
	private ConnexionController connexionController ;
	
	public ReinitMotPasse(ConnexionModel connexionModel,ConnexionController connexionController){
		this.connexionController = connexionController;
		this.connexionModel = connexionModel;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(600, 500);
		setResizable(false);
		setTitle("Réinitialiser mot de passe");
		try {
			setUp();
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private void setUp() throws BLLException{
		//maini panel
		mainPanel = new JPanel();
		mainPanel.setOpaque(true);
		setLocationRelativeTo(null);
		setContentPane(mainPanel);
		
		mainPanel.setLayout(new GridBagLayout());
		
		//creation de composants
		 List<Personnel> personnels = loginManager.toutLePersonnel();
		personnelLabel = createLabel("Personnels");
		for (Personnel personnel : personnels) {
			personnelCombo = createComboBox(personnels, "Selectionner un personnel");			
		}
		motPasseLabel = createLabel("Nouveau mot de passe");
		motPasse = createPassField(null, "Nouveau mot de passe");
		motPasseConfirmeLabel =createLabel("Confirmer mot passe");
		motPasseConfirme = createPassField(null, "Confirmer mot passe");
		
		//Creation de la grille et placement des composant sur la grille 
		GridBagConstraints gridBagConstraints = createGridBagConstraints();
		
		addComponentOnGrid(mainPanel, personnelLabel, gridBagConstraints, 1, 1, 0.15);
		addComponentOnGrid(mainPanel, personnelCombo, gridBagConstraints, 2, 1, 0.15);
		addComponentOnGrid(mainPanel, motPasseLabel, gridBagConstraints, 1, 2, 0.15);
		addComponentOnGrid(mainPanel, motPasse, gridBagConstraints, 2, 2, 0.15);
		addComponentOnGrid(mainPanel, motPasseConfirmeLabel, gridBagConstraints, 1, 3, 0.15);
		addComponentOnGrid(mainPanel, motPasseConfirme, gridBagConstraints, 2, 3, 0.15);
		addComponentOnGrid(mainPanel, boutonAjouter(), gridBagConstraints, 5, 4, 1);
		
	}
	private Personnel retourSaisi() {
		// TODO Auto-generated method stub
		return null;
	}
	private void showFailureMessage(String message) {
		JOptionPane.showMessageDialog(ReinitMotPasse.this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
	}

	private void showSuccessMessage(String message) {
		JOptionPane.showMessageDialog(ReinitMotPasse.this, message);
	}
	
	private JPanel boutonAjouter() {
		JPanel panel = new JPanel();
		panel.setOpaque(true);
		panel.setLayout(new GridBagLayout());

		GridBagConstraints gridBagConstraints = new GridBagConstraints();

		validerBoutton = new JButton();
		validerBoutton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Save24.gif")));
		validerBoutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (String.valueOf(motPasse.getPassword()).equals(String.valueOf(motPasseConfirme.getPassword()))) {
						
						connexionController.AjoutPersonnel(retourSaisi());
						showSuccessMessage("Personnel ajouter !");						
						motPasse.setText("");
						motPasseConfirme.setText("");						
						
					} else {
						showFailureMessage("Attention veuillez confirmer le mot de passe !");
						motPasseConfirme.requestFocus();
						motPasseConfirme.setText("");
					}

				} catch (BLLException ex) {
					showFailureMessage(ex.getMessage());
				}

			}

			
		});

		addComponentOnGrid(panel, validerBoutton, gridBagConstraints, 5, 5, 1);
		return panel;

	}
	
	
	private JComboBox<String> createComboBox(List<Personnel> personnels, String tooltip) {

        JComboBox<String> combo = new JComboBox<>();
        combo.setToolTipText(tooltip);

        for (Personnel personnel : personnels) {
            combo.addItem(personnel.getNom());
        }
        return combo;
    }
	
	private JPasswordField createPassField(String text, String tooltip) {
		JPasswordField motPasseTxt = new JPasswordField(text);
		motPasseTxt.setToolTipText(tooltip);
		return motPasseTxt;

	}

	private void addComponentOnGrid(JPanel panel, JComponent component, GridBagConstraints gridBagConstraints,
			int gridX, int gridY, double weightX) {

		gridBagConstraints.gridx = gridX; // Place on X Axis
		gridBagConstraints.gridy = gridY; // Place on Y Axis
		gridBagConstraints.weightx = weightX; // Weight on X Axis

		panel.add(component, gridBagConstraints);
	}

	private GridBagConstraints createGridBagConstraints() {

		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL; // Redimensionne
																	// horizontalement
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);

		return gridBagConstraints;
	}
	
	private JLabel createLabel(String text) {

		JLabel label = new JLabel(text);
		label.setFont(defaultLabelFont);

		return label;
	}

}
