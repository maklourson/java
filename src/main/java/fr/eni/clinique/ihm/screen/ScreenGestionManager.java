package fr.eni.clinique.ihm.screen;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.ihm.controller.ConnexionController;
import fr.eni.clinique.ihm.model.ConnexionModel;
public class ScreenGestionManager extends JFrame {

 	/**
	 * 
	 */
	private static final long serialVersionUID = -5384422349271584894L;
	private List<Personnel> lesPersonnels = new ArrayList<>();
	private final String[] entetes = { "Nom", "Rôle", "MotPasse"};
    private final Object[][] donnees = {};
	    
	private JPanel mainPanel;
	private JLabel ajoutLbl; 
	private JLabel supprLbl; 
	private JLabel reinitLbl; 
	
    private JButton validateButton;
    private JButton deleteButton;
    private JButton newButton;
    
    public ScreenGestionManager (ConnexionController controller, ConnexionModel model) {

    setDefaultCloseOperation(EXIT_ON_CLOSE); 
    setSize(800, 500 ); 
    setResizable(false); 
    setTitle("Gestion du personnel");
    try {
        setUp(); 
        controller.init();

    } catch (Exception e) {
        showFailureMessage(e.getMessage());
}
}

private void showFailureMessage(String message) {
    JOptionPane.showMessageDialog(ScreenGestionManager.this, message, "Erreur d'affichage", JOptionPane.ERROR_MESSAGE);
}

private void setUp() {

    mainPanel = new JPanel();
    mainPanel.setOpaque(true);
    setVisible(true);
    setLocationRelativeTo(null);

    setContentPane(mainPanel);
    
    mainPanel.setLayout(new GridBagLayout());
    mainPanel.setLocation(100, 100);
    ajoutLbl = new JLabel("Ajouter");
    supprLbl = new JLabel("Supprimer");
    reinitLbl = new JLabel("Réinitialiser");

    JTable monTableau = new JTable(donnees, entetes);
    
    monTableau.setVisible(true);
    GridBagConstraints gridBagConstraints = createGridBagConstraints();
    addComponentOnGrid(mainPanel, createButtonBar(), gridBagConstraints, 1, 1, 1);
    addComponentOnGrid(mainPanel, monTableau, gridBagConstraints, 2, 1, 1);    
}

private void addComponentOnGrid(JPanel panel,JComponent component,GridBagConstraints gridBagConstraints,int gridX, int gridY, double weightX) {
    gridBagConstraints.gridx = gridX; 
    gridBagConstraints.gridy = gridY;
    gridBagConstraints.weightx = weightX; 

    panel.add(component, gridBagConstraints);
}

private GridBagConstraints createGridBagConstraints() {

    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL; 
    gridBagConstraints.insets = new Insets(5,5,5,5);
    return gridBagConstraints; 
}

private JPanel createButtonBar() {
    JPanel panel = new JPanel();
    panel.setOpaque(true);
    panel.setLayout(new GridBagLayout());
   
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    validateButton = new JButton();
    validateButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/init.PNG")));
    validateButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    });

    deleteButton = new JButton();
    deleteButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/Delete24.gif")));
    deleteButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           
        }
    });

    newButton = new JButton();
    newButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/New24.gif")));
    newButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    });

    addComponentOnGrid(panel, newButton, gridBagConstraints, 0, 0, 1);
    addComponentOnGrid(panel, ajoutLbl, gridBagConstraints, 0, 1, 1);

    addComponentOnGrid(panel, deleteButton, gridBagConstraints,1, 0, 1);
    addComponentOnGrid(panel, supprLbl, gridBagConstraints, 1, 1, 1);
    
    addComponentOnGrid(panel, validateButton, gridBagConstraints, 2, 0, 1);
    addComponentOnGrid(panel, reinitLbl, gridBagConstraints, 2, 1, 1);
    
    return panel;
}

public void addAllPersonnel(List<Personnel> lePersonnel) {
    this.lesPersonnels.addAll(lePersonnel);
}

public void addPersonnel(Personnel lePersonnel) {
    this.lesPersonnels.add(lePersonnel);
}
public ScreenGestionManager(List<Personnel> personnels) {
    super();
    this.lesPersonnels = personnels;
}

}
