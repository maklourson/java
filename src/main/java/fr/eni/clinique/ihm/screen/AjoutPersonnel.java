package fr.eni.clinique.ihm.screen;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import fr.eni.clinique.bll.exception.BLLException;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.ihm.controller.ConnexionController;
import fr.eni.clinique.ihm.model.ConnexionModel;

public class AjoutPersonnel extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JLabel nomLabel;
	private JLabel motPasseLabel;
	private JLabel motPasseLabel2;
	private JLabel roleLabel;
	private JLabel archiveLabel;

	private JTextField nomTxt;
	private JPasswordField motPasseTxt;
	private JPasswordField motPasseTxt2;
	
	private JTextField archiveTxt;
	private JRadioButton archiveOuiRadio;
	private JRadioButton archiveNonRadio;
	private JComboBox<String> RoleCombo;

	private JButton boutonAjouter;

	private Font defaultLabelFont = new Font("Arial", Font.BOLD, 14);
	private Font defaultFont = new Font("Arial", Font.PLAIN, 14);

	private ConnexionModel connexionModel;
	private ConnexionController connexionController ;
	private Integer codePers= 0;

	public AjoutPersonnel(ConnexionController connexionController, ConnexionModel connexionModel) {
		this.connexionController = connexionController;
		this.connexionModel = connexionModel;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(600, 500);
		setResizable(false);
		setTitle("Ajout personnel");
		setUp();

	}

	private void setUp() {

		mainPanel = new JPanel();
		mainPanel.setOpaque(true);
		setVisible(true);
		setLocationRelativeTo(null);

		setContentPane(mainPanel);

		mainPanel.setLayout(new GridBagLayout());
		
		// Creation des composants
		nomLabel = createLabel("Nom : ");
		nomTxt = createTextField(null, "Entrez le Nom");
		motPasseLabel = createLabel("Mot de passe : ");
		motPasseTxt = createPassField(null, "Entrez le Mot de passe");
		motPasseLabel2 = createLabel("Confirmer Mot de passe : ");
		motPasseTxt2 = createPassField(null, "Confirmer le Mot de passe");
		roleLabel = createLabel("Rôle () :");
		RoleCombo = createComboBox(Arrays.asList("Adm","Sec","Vet"), "Choisisissez un Rôle ");
		archiveLabel = createLabel("Archivé : ");
		archiveTxt = createTextField(null, "Archivé ?");
		
		// Creation de la grille et placement des composants sur la grille
		GridBagConstraints gridBagConstraints = createGridBagConstraints();

		addComponentOnGrid(mainPanel, nomLabel, gridBagConstraints, 1, 1, 0.15);
		addComponentOnGrid(mainPanel, nomTxt, gridBagConstraints, 2, 1, 0.85);
		addComponentOnGrid(mainPanel, motPasseLabel, gridBagConstraints, 1, 2, 0.15);
		addComponentOnGrid(mainPanel, motPasseTxt, gridBagConstraints, 2, 2, 0.85);
		addComponentOnGrid(mainPanel, motPasseLabel2, gridBagConstraints, 1, 3, 0.15);
		addComponentOnGrid(mainPanel, motPasseTxt2, gridBagConstraints, 2, 3, 0.85);
		addComponentOnGrid(mainPanel, roleLabel, gridBagConstraints, 1, 4, 0.15);
		addComponentOnGrid(mainPanel, RoleCombo, gridBagConstraints, 2, 4, 0.85);
		addComponentOnGrid(mainPanel, archiveLabel, gridBagConstraints, 1, 5, 0.15);
		addComponentOnGrid(mainPanel, createArchiveRadio(), gridBagConstraints, 2, 5, 0.85);
		addComponentOnGrid(mainPanel, boutonAjouter(), gridBagConstraints, 3, 5, 0.85);

	}

	private void showFailureMessage(String message) {
		JOptionPane.showMessageDialog(AjoutPersonnel.this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
	}

	private void showSuccessMessage(String message) {
		JOptionPane.showMessageDialog(AjoutPersonnel.this, message);
	}

	private Personnel retourSaisi() {
		Personnel personnel = new Personnel();

	//	personnel.setCodePers(codePers);
		personnel.setNom(nomTxt.getText().trim());
		personnel.setMotPasse(String.valueOf(motPasseTxt2.getPassword()));
		personnel.setRole(RoleCombo.getSelectedItem().toString());
		String arch = archiveOuiRadio.isSelected() ? "1" : "0";
		personnel.setArchive(Boolean.parseBoolean(arch));

		return personnel;
	}

	private JLabel createLabel(String text) {

		JLabel label = new JLabel(text);
		label.setFont(defaultLabelFont);

		return label;
	}

	private JTextField createTextField(String text, String tooltip) {

		JTextField textField = new JTextField(text);
		textField.setFont(defaultFont);
		textField.setToolTipText(tooltip);
		return textField;
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

	private JPanel boutonAjouter() {
		JPanel panel = new JPanel();
		panel.setOpaque(true);
		panel.setLayout(new GridBagLayout());

		GridBagConstraints gridBagConstraints = new GridBagConstraints();

		boutonAjouter = new JButton();
		boutonAjouter.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Save24.gif")));
		boutonAjouter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (String.valueOf(motPasseTxt.getPassword()).equals(String.valueOf(motPasseTxt2.getPassword()))) {
						
						connexionController.AjoutPersonnel(retourSaisi());
						showSuccessMessage("Le personnel :" +retourSaisi().getNom()+ " a été ajouté !");

						nomTxt.setText("");
						motPasseTxt.setText("");
						motPasseTxt2.setText("");						
						archiveTxt.setText("");
					} else {
						showFailureMessage("Attention veuillez confirmer le mot de passe !");
						motPasseTxt2.requestFocus();
						motPasseTxt2.setText("");
					}

				} catch (BLLException ex) {
					showFailureMessage(ex.getMessage());
				}

			}
		});

		addComponentOnGrid(panel, boutonAjouter, gridBagConstraints, 1, 1, 1);
		return panel;

	}
	private JComboBox<String> createComboBox(List<String> options, String tooltip) {

        JComboBox<String> combo = new JComboBox<>();
        combo.setToolTipText(tooltip);

        for (String option : options) {
            combo.addItem(option);
        }
        return combo;
    }
	
	private JRadioButton createRadioButton(String text, String tooltip) {

        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setToolTipText(tooltip);

        return radioButton;
    }
	
	private JPanel createArchiveRadio(){
		JPanel panel = new JPanel();
		panel.setOpaque(true);
		panel.setLayout(new GridBagLayout());
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		archiveOuiRadio = createRadioButton(String.valueOf("Oui"), "Archivé");
		archiveNonRadio = createRadioButton(String.valueOf("Non"), "Actif");
		
		addComponentOnGrid(panel, archiveOuiRadio, gridBagConstraints, 1, 1, 1);
		addComponentOnGrid(panel, archiveNonRadio, gridBagConstraints, 2, 1, 1);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(archiveOuiRadio);
		buttonGroup.add(archiveNonRadio);
		
		return panel;
		
		
	}

}
