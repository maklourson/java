package fr.eni.clinique.ihm.screen;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.eni.clinique.bll.exception.BLLException;
import fr.eni.clinique.bll.manager.impl.LoginMgerImpl;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.ihm.controller.ConnexionController;
import fr.eni.clinique.ihm.model.ConnexionModel;

public class ConnexionScreen extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8873958310825089378L;

	 	private JPanel mainPanel;
	    private JLabel nomLbl;
	    private JTextField nomTxt; 
	    private JLabel mdpLbl; 
	    private JPasswordField mdpTxt;	
	    private JButton validerConnexion;	    
	    private Font defaultLabelFont = new Font("Arial", Font.BOLD, 14); 
	    
	    private ConnexionController connnexionController;
	    private ConnexionModel connexionModel; 
	    private LoginMgerImpl managerBll = LoginMgerImpl.getInstance();
	   
	    public ConnexionScreen(String titre,ConnexionController connnexionController, ConnexionModel connexionModel) {
	    	
	    	this.connexionModel = connexionModel;
	    	this.connnexionController = connnexionController;

	        setDefaultCloseOperation(EXIT_ON_CLOSE); // Action de fermeture
	        setSize(300, 200); // Taille de la fenetre
	        setResizable(false); // Fenetre pas redimensionnable
	        setTitle(titre);
	        try {
	            setUp(); 
	            connnexionController.init();

	        } catch (Exception e) {
	            showFailureMessage(e.getMessage());
	        }
	    }
	    
	    private void showFailureMessage(String message) {
	        JOptionPane.showMessageDialog(ConnexionScreen.this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
	    }	    
		
	    
	    private void setUp() {

	        mainPanel = new JPanel();
	        mainPanel.setOpaque(true);
	        setVisible(true);
	        setLocationRelativeTo(null);

	        setContentPane(mainPanel);
	        
	        mainPanel.setLayout(new GridBagLayout());
	        
	        nomLbl = createLabel("Nom");
	        nomTxt = new JTextField();
	        
	        mdpLbl = createLabel("Mot de Passe");
	        mdpTxt = new JPasswordField();
	        
	        JPanel panel = new JPanel();
	        panel.setOpaque(true);
	        panel.setLayout(new GridBagLayout());

	        validerConnexion = new JButton("VALIDER");
	        validerConnexion.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(nomTxt.getText().equals("")|| mdpTxt.getPassword().toString().equals("") ){
	            		showFailureMessage("Veuillez saisir tous les champs");	
	            	}
	            	else if(authenticate(nomTxt.getText(), String.valueOf(mdpTxt.getPassword()))){	            			
	            			loadecranMenu();
	            			dispose();
	            		}else{	            			
	            			showFailureMessage("Utilisateur inconnu");
	            		}	            		
	            	
				}
			});
	        GridBagConstraints gridBagConstraints = createGridBagConstraints();
	        addComponentOnGrid(mainPanel, nomLbl, gridBagConstraints, 1, 1, 0.15);
	        addComponentOnGrid(mainPanel, nomTxt, gridBagConstraints, 2, 1, 0.85);
	        addComponentOnGrid(mainPanel, mdpLbl, gridBagConstraints, 1, 2, 0.15);
	        addComponentOnGrid(mainPanel, mdpTxt, gridBagConstraints, 2, 2, 0.85);
	        addComponentOnGrid(mainPanel, validerConnexion, gridBagConstraints, 2, 3, 1);
	    }
	    
	    
	    private void addComponentOnGrid(
	            JPanel panel,
	            JComponent component,
	            GridBagConstraints gridBagConstraints,
	            int gridX, int gridY, double weightX) {

	        gridBagConstraints.gridx = gridX; // Place on X Axis
	        gridBagConstraints.gridy = gridY; // Place on Y Axis
	        gridBagConstraints.weightx = weightX; // Weight on X Axis

	        panel.add(component, gridBagConstraints);
	    }
	    
	    private GridBagConstraints createGridBagConstraints() {

	        GridBagConstraints gridBagConstraints = new GridBagConstraints();
	        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL; 
	        gridBagConstraints.insets = new Insets(5,5,5,5);

	        return gridBagConstraints; 
	    }
	    private JLabel createLabel(String text) {
	        JLabel label = new JLabel(text);
	        label.setFont(defaultLabelFont);

	        return label;
	    }
	    
	    private void loadecranMenu(){
	    	EcranMenu ecranMenu = new EcranMenu(this);
	    	
	    	ecranMenu.setVisible(true);
	    	ecranMenu.setLocationRelativeTo(null);	    	
	    }
	    
	    private boolean authenticate(String nom, String pass){
	    	boolean retour = false;
			try {
				retour =  managerBll.tryConnect(nom, pass);
			} catch (BLLException e) {
				e.printStackTrace();
			}
	    	return retour;
	    }

		/**
		 * @return the nomTxt
		 */
		public JTextField getNomTxt() {
			return nomTxt;
		}

//	    public  Personnel personnelConnecter(){
//	    	Personnel lePersonnel = null;
//	    	List<Personnel> lesPersonnels = new ArrayList<Personnel>();
//	    	try {
//				lesPersonnels = managerBll.toutLePersonnel();
//				for (Personnel personnel : lesPersonnels) {
//					if(personnel.getNom().equals(nomTxt)){
//						lePersonnel = new Personnel(personnel.getCodePers(),personnel.getNom(),personnel.getMotPasse(),personnel.getRole(),personnel.isArchive());
//					}
//				}
//			} catch (BLLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			return lePersonnel;
//	    	
//	    }
}
	    
