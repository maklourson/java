package fr.eni.clinique.ihm.screen;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import fr.eni.clinique.CliniqueVeto;
import fr.eni.clinique.bll.exception.BLLException;
import fr.eni.clinique.bll.manager.impl.LoginMgerImpl;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.ihm.controller.ConnexionController;
import fr.eni.clinique.ihm.model.ConnexionModel;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class EcranMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7889761062932513110L;
	private ConnexionModel connexionModel;
	private ConnexionController connexionController;
	private String personnelNom;
	private LoginMgerImpl managerBll = LoginMgerImpl.getInstance();
	private Personnel personnelConnecter = null;
	private void showFailureMessage(String message) {
        JOptionPane.showMessageDialog(EcranMenu.this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

	/**
	 * Create the frame.
	 */
	public EcranMenu(ConnexionScreen connexionScreen) {
		setTitle("Clinique V\u00E9t\u00E9rinaire");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setLayout(new BorderLayout());
		JLabel background = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("images/fond_frame.jpg")));
		add(background);
		background.setLayout(new FlowLayout());

		setBounds(100, 100, 501, 378);
		connexionModel = new ConnexionModel();
		connexionController = new ConnexionController(connexionModel);
		personnelNom = connexionScreen.getNomTxt().getText();
		

		try {
			personnelConnecter = managerBll.connexion(personnelNom);

		} catch (BLLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (personnelConnecter.isArchive() == false) {

			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);

			JMenu mnFichier = new JMenu("Menu");
			menuBar.add(mnFichier);

			JMenuItem mntmDconnexion = new JMenuItem("D\u00E9connexion");
			mnFichier.add(mntmDconnexion);
			mntmDconnexion.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					CliniqueVeto.ecranConnexion();
				}
			});

			JMenuItem mntmFermer = new JMenuItem("Fermer");
			mnFichier.add(mntmFermer);
			mntmFermer.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();

				}
			});

			JMenu mnGestionDesRendezvous = new JMenu("Gestion des rendez-vous");
			// menuBar.add(mnGestionDesRendezvous);

			JMenuItem mntmGestionDesClients = new JMenuItem("Gestion des clients");
			// mnGestionDesRendezvous.add(mntmGestionDesClients);
			mntmGestionDesClients.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new EcranPrincipalClient().setVisible(true);

				}
			});

			JMenuItem mntmPriseDeRendezvous = new JMenuItem("Prise de rendez-vous");
			// mnGestionDesRendezvous.add(mntmPriseDeRendezvous);
			mntmPriseDeRendezvous.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new EcranRDV().setVisible(true);
				}
			});

			JMenuItem mnAgenda = new JMenuItem("Agenda");

			// menuBar.add(mnAgenda);
			mnAgenda.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					EcranAgenda ecranAgenda = new EcranAgenda();
					ecranAgenda.setVisible(true);
					ecranAgenda.setLocation(420, 300);

				}
			});

			JMenuItem mnGestionDuPersonnel = new JMenuItem("Gestion du personnel");
			// menuBar.add(mnGestionDuPersonnel);
			mnGestionDuPersonnel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					GestionPerso gestionPerso = new GestionPerso();
					gestionPerso.setVisible(true);
					gestionPerso.setLocation(420, 300);

				}
			});
			// gestion des roles
			switch (personnelConnecter.getRole()) {
			case "Sec":
				menuBar.add(mnGestionDesRendezvous);
				mnGestionDesRendezvous.add(mntmPriseDeRendezvous);
				mnGestionDesRendezvous.add(mntmGestionDesClients);
				break;
			case "Vet":
				menuBar.add(mnAgenda);
				break;
			case "Adm":
				menuBar.add(mnGestionDesRendezvous);
				mnGestionDesRendezvous.add(mntmPriseDeRendezvous);
				mnGestionDesRendezvous.add(mntmGestionDesClients);
				menuBar.add(mnAgenda);
				menuBar.add(mnGestionDuPersonnel);
				break;
			}
		}else{
			showFailureMessage("Accès non autorisé !");			
		}
	}
}
