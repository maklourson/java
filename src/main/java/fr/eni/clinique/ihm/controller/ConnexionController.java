package fr.eni.clinique.ihm.controller;



import java.util.List;

import fr.eni.clinique.bll.exception.BLLException;
import fr.eni.clinique.bll.factory.ManagerFactory;
import fr.eni.clinique.bll.manager.LoginMger;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.bo.Race;
import fr.eni.clinique.bo.Rdv;
import fr.eni.clinique.dal.exception.DaoException;
import fr.eni.clinique.ihm.model.ConnexionModel;

public class ConnexionController {
	
	private ConnexionModel connexionModel;
    private LoginMger loginManager = ManagerFactory.loginMger();
    
    public ConnexionController(ConnexionModel connexionModel) {
        super();
        this.connexionModel = connexionModel;
       try {
		this.init();
	} catch (BLLException | DaoException e) {
		e.printStackTrace();
	}
    }
    
    public void init() throws BLLException, DaoException {

        List<Personnel> personnels = loginManager.toutLePersonnel();
        connexionModel.loadPersonnels(personnels);
        
        List<Animal> animaux = loginManager.tousLesAnimaux();
        connexionModel.loadAnimaux(animaux);
        
        List<Client> clients = loginManager.tousLesClients();
        connexionModel.loadClients(clients);

        List<Race> races = loginManager.toutesLesRaces();
        connexionModel.loadRaces(races);
        
        List<Rdv> rdv = loginManager.toutLesRdv();
        connexionModel.loadAgenda(rdv);

    }
    
	public void deletePersonnel(Personnel personnel) throws BLLException{
	
		if(personnel.getCodePers() != null){
			loginManager.removePersonnel(personnel);
			connexionModel.removePersonnel(personnel.getCodePers());
		}		
	}
	
	public void AjoutPersonnel(Personnel personnel) throws BLLException{
		if(personnel.getCodePers() == null){
			loginManager.ajoutPersonnel(personnel);			
		}
		connexionModel.addPersonnel(personnel);		
	}
	
	public void AjoutClient(Client c) throws BLLException{
		if(c.getCodeClient() == 0){
			loginManager.ajoutClient(c);			
		}
		connexionModel.addClient(c);		
	}

	public void AjoutAnimal(Animal a)throws BLLException {
		
		if(a.getCodeAnimal() == null){
			loginManager.ajoutAnimal(a);			
		}
		connexionModel.addAnimal(a);		
	}
}
