package fr.eni.clinique.dal;

//import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

//import javax.swing.SwingUtilities;
//import javax.swing.UIManager;

//import fr.eni.clinique.bll.manager.ihm.GestionPersonnel;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.bo.Rdv;
import fr.eni.clinique.dal.dao.PersonnelDAO;
import fr.eni.clinique.dal.dao.RdvDAO;
import fr.eni.clinique.dal.exception.DaoException;
import fr.eni.clinique.dal.factory.DaoFactory;

public class AppliTestDAL {

	public static void main(String[] args) {
//		System.out.println("b");
//		
//		List <Personnel> ps= new ArrayList<>();
//		
		PersonnelDAO pers =DaoFactory.personnelDao();
		
		RdvDAO rdv = DaoFactory.rdvDAO();
//		//select all
//		try {
//			ps = pers.selectALL();
//			for(Personnel p : ps)
//			{
//				System.out.println("a"+p);
//			}
//		} catch (DaoException e) {
//			
//			e.printStackTrace();
//		}
		//insert
//		Personnel personnel = new Personnel("TeTTPaMP", "mdpDAL","Sec",true);
//
//		try {
//			pers.insert(personnel);
//		} catch (DaoException e) {
//			e.printStackTrace();
//		} 
		
		//select bosapin
		try {
			Personnel p=pers.connexion("bosapin");
			System.out.println(p);
			
			List<Rdv> r = rdv.selectALL();
			for (Rdv rdv2 : r) {
				System.out.println(r);
			}
				
		} catch (DaoException e) {
			e.printStackTrace();
		} 
		/*
		//update
		try {
			pers.update(personnel,"OKOK");
				
		} catch (DaoException e) {
			e.printStackTrace();
		} */
//		 try {
//
//	            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//	            
//	            SwingUtilities.invokeLater(new Runnable() {
//	                @Override
//	                public void run() {
//	                    
//	                    CliniqueModel cliniqueModel = new CliniqueModel();
//	                    
//	                    CliniqueController cliniqueController = new CliniqueController(cliniqueModel);
//	                   GestionPersonnel screen = new GestionPersonnel(cliniqueController, cliniqueModel);
//	       	         
////	                    ConnexionScreen screen = new ConnexionScreen(cliniqueController, cliniqueModel);
//	         
//	                }
//	            });
//	        } catch (Exception e) {
//	            throw new TechnicalException("Erreur Technique", e);
//	        }
		
	}

}
