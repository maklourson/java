package fr.eni.clinique.ihm.screen;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;

import fr.eni.clinique.bll.exception.BLLException;
import fr.eni.clinique.bll.factory.ManagerFactory;
import fr.eni.clinique.bll.manager.LoginMger;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.dao.ClientDAO;
import fr.eni.clinique.dal.dao.RdvDAO;
import fr.eni.clinique.dal.factory.DaoFactory;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.ihm.controller.ConnexionController;
import fr.eni.clinique.ihm.model.ConnexionModel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComboBox;
import javax.swing.JButton;

public class EcranRDV extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3455097310174582783L;
	private JPanel contentPane;
	private JComboBox<String> cbx_veterinaire;
	private ConnexionModel connexionModel;
	private ConnexionController connexionController ;
	private Integer codeCli;
	
	private LoginMger loginManager = ManagerFactory.loginMger();

	private JTable tableau;

	private JComboBox<String> createComboBox(List<Personnel> personnels, String tooltip) {

        JComboBox<String> combo = new JComboBox<>();
        combo.setToolTipText(tooltip);

        for (Personnel personnel : personnels) {
            combo.addItem(personnel.getNom());
        }
        return combo;
    }

	/**
	 * Create the frame.
	 */
	public EcranRDV() {
		setTitle("Prise de rendez-vous");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 567, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(10, 23, 177, 102);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblClient = new JLabel("Client");
		lblClient.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblClient.setBounds(10, 11, 84, 14);
		panel.add(lblClient);
		
		JLabel lblAnimal = new JLabel("Animal");
		lblAnimal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAnimal.setBounds(10, 52, 84, 14);
		panel.add(lblAnimal);
		
		//Combo Client
		JComboBox<String> cbx_Client = new JComboBox<String>();
		cbx_Client.setBounds(10, 31, 107, 20);
		panel.add(cbx_Client);
		try {
			List<Client> clients = loginManager.tousLesClients();
			for (Client client : clients) {
				cbx_Client.addItem(client.getNomClient()+" - "+client.getPrenomClient());	
			//	codeCli=client.getCodeClient();
			}
			
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		//Combo Animal
		JComboBox<String> cbx_Animal = new JComboBox<String>();
		cbx_Animal.setBounds(10, 71, 89, 20);
		panel.add(cbx_Animal);
		try {
		
			//List<Animal> animaux = loginManager.tousLesAnimauxParCodeClient(codeCli);
			List<Animal> animaux = loginManager.tousLesAnimaux();
			for (Animal animal : animaux) {
				cbx_Animal.addItem(animal.getRace()+" - "+animal.getNomAnimal());		
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		JButton Bt_ajoutClient = new JButton("+");
		Bt_ajoutClient.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Bt_ajoutClient.setBounds(127, 21, 40, 30);
		Bt_ajoutClient.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			AjoutClient ajoutClient = new AjoutClient(connexionController, connexionModel);
			ajoutClient.setVisible(true);
				
			}
		});
		panel.add(Bt_ajoutClient);
		
		JButton Bt_ajoutNom = new JButton("+");
		Bt_ajoutNom.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Bt_ajoutNom.setBounds(127, 61, 40, 30);
		panel.add(Bt_ajoutNom);
		Bt_ajoutNom.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			EcranAnimal ecranAnimal = new EcranAnimal(connexionController, connexionModel);
			ecranAnimal.setVisible(true);
				
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBounds(212, 23, 154, 102);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		//Combo Veterinaire
		List<Personnel> personnels;
		try {
			personnels = loginManager.toutLePersonnel();
			for (Personnel personnel : personnels) {
				if(personnel.getRole().equals("Vet")){
					cbx_veterinaire = createComboBox(personnels, "Selectionner un personnel");
				}
				
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}

		cbx_veterinaire.setBounds(32, 31, 100, 20);
		panel_1.add(cbx_veterinaire);
		
		JLabel lblVtrinaire = new JLabel("V\u00E9t\u00E9rinaire");
		lblVtrinaire.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblVtrinaire.setBounds(32, 11, 100, 14);
		panel_1.add(lblVtrinaire);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2.setBounds(387, 23, 154, 102);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDate.setBounds(10, 11, 46, 14);
		panel_2.add(lblDate);
 
	
		JLabel lblNewLabel = new JLabel("Heure");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 52, 46, 14);
		panel_2.add(lblNewLabel);
		
		JComboBox<?> cbx_heure = new JComboBox<Object>();
		cbx_heure.setBounds(10, 71, 33, 20);
		panel_2.add(cbx_heure);
		
		JComboBox<?> cbx_min = new JComboBox<Object>();
		cbx_min.setBounds(59, 71, 33, 20);
		panel_2.add(cbx_min);
		
		JLabel lblH = new JLabel("h");
		lblH.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblH.setBounds(48, 74, 46, 14);
		panel_2.add(lblH);
		
		JLabel lblPour = new JLabel("Pour");
		lblPour.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPour.setBounds(10, -2, 46, 29);
		contentPane.add(lblPour);
		
		JLabel lblPar = new JLabel("Par");
		lblPar.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPar.setBounds(212, -2, 46, 29);
		contentPane.add(lblPar);
		
		JLabel lblQuand = new JLabel("Quand");
		lblQuand.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQuand.setBounds(387, -2, 46, 29);
		contentPane.add(lblQuand);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_3.setBounds(10, 148, 531, 208);
		contentPane.add(panel_3);
		
		connexionModel = new ConnexionModel();
		connexionController = new ConnexionController(connexionModel);
		
		tableau = new JTable(connexionModel.getTableModelAgenda());
		panel_3.add(new JScrollPane(tableau), BorderLayout.EAST);
		
		connexionModel.addObserver(this);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSupprimer.setBounds(357, 362, 101, 23);
		contentPane.add(btnSupprimer);
		btnSupprimer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int choix = JOptionPane.showConfirmDialog(EcranRDV.this,"Êtes-vous sûr de vouloir supprimer ce client ?", "Suppression Client",JOptionPane.OK_CANCEL_OPTION);
				if (choix ==1){
					RdvDAO rdvDAO = new DaoFactory().rdvDAO();
					//rdvDAO.deleteById());
					
				}
			}
		});
		
		JButton btnValider = new JButton("Valider");
		btnValider.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnValider.setBounds(468, 362, 73, 23);
		contentPane.add(btnValider);
		btnValider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(EcranRDV.this, "Rendez-vous bien enregistré");
				
			}
		});
		
		
	
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		((AbstractTableModel)tableau.getModel()).fireTableDataChanged();
		
	}
}
