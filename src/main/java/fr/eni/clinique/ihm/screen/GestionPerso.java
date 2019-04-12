package fr.eni.clinique.ihm.screen;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import fr.eni.clinique.bll.exception.BLLException;
import fr.eni.clinique.ihm.controller.ConnexionController;
import fr.eni.clinique.ihm.model.ConnexionModel;


public class GestionPerso extends JFrame implements Observer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tableau;
	private  ConnexionModel connexionModel;
	private ConnexionController connexionController ;

	public GestionPerso() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 500);
		setResizable(false);
		connexionModel = new ConnexionModel();
		connexionController = new ConnexionController(connexionModel);
	
		tableau = new JTable(connexionModel.getTableModel());
		setTitle("Gestion du personnel");
		getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
		JPanel boutons = new JPanel();
		boutons.add(new JButton(new AddAction()));
		boutons.add(new JButton(new RemoveAction()));
		boutons.add(new JButton(new ResetAction()));
		 getContentPane().add(boutons, BorderLayout.NORTH);
		 pack();
		 
		 connexionModel.addObserver(this);
	}

	private class AddAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private AddAction() {
			super("Ajouter");
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
//			connexionModel.addPersonnel(new Personnel());
			AjoutPersonnel ajoutPersonnel = new AjoutPersonnel(connexionController, connexionModel);
			ajoutPersonnel.setVisible(true);
			ajoutPersonnel.setLocation(400, 300);
		}
	}
	private void showSuccessMessage(String message) {
		JOptionPane.showMessageDialog(GestionPerso.this, message);
	}

	private class RemoveAction extends AbstractAction{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private RemoveAction() {
			super("Supprimer");
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int[] selection = tableau.getSelectedRows();
			
			
			for (int i = selection.length - 1; i >= 0; i--) {
				try {
					connexionModel.removePersonnel(selection[i]);
					showSuccessMessage("Supprimé");
				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
}
	private class ResetAction extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private ResetAction() {
			super("Réinitialiser mot de passe");
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			ReinitMotPasse reinitMotPasse = new ReinitMotPasse(connexionModel, connexionController);
			reinitMotPasse.setVisible(true);
			
			
		}
		
	}

	@Override
	public void update(Observable o, Object arg) {
		((AbstractTableModel)tableau.getModel()).fireTableDataChanged();
		
	}

	
}