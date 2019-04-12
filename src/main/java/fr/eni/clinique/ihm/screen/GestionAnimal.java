package fr.eni.clinique.ihm.screen;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.eni.clinique.ihm.controller.ConnexionController;
import fr.eni.clinique.ihm.model.ConnexionModel;

public class GestionAnimal extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9073980588395866774L;
	
	
	private JTable tableau;
	private  ConnexionModel connexionModel;
	private ConnexionController connexionController ;

	public GestionAnimal() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 500);
		setResizable(true);
		 connexionModel = new ConnexionModel();
		 connexionController = new ConnexionController(connexionModel);
	
		tableau = new JTable(connexionModel.getTableModelAnimal());
		setTitle("Gestion des animaux");
		getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
		pack();
	}

}
