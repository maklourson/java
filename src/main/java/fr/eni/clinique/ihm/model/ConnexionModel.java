package fr.eni.clinique.ihm.model;

import java.util.List;
import java.util.Observable;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bll.exception.BLLException;


import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.bo.Race;
import fr.eni.clinique.bo.Rdv;

public class ConnexionModel extends Observable {
	private TableModelPerso tableModelPerso;
	private TableModelClientRecherche tableModelClientRecherche;
	private TableModelAnimal tableModelAnimal;
	private TableModelClient tableModelClient;
	private TableModelRace   tableModelRace;
	private TableModelAgenda tableModelAgenda;
	
	 public ConnexionModel() {
	        super();
	    }
//Personnel
	public void loadPersonnels(List<Personnel> personnels) {
		tableModelPerso = new TableModelPerso(personnels);		
	}
	
	public void addPersonnel(Personnel personnel){
		tableModelPerso.addPersonnel(personnel);
		setChanged();
        notifyObservers();
        clearChanged();
	}
	
	public void removePersonnel(int index) throws BLLException{		
		tableModelPerso.removePersonnel(index);	
		setChanged();
        notifyObservers();
        clearChanged();
	}

	public TableModelPerso getTableModel(){
		return tableModelPerso;
	}
	
	
	///Animaux 
	public void loadAnimaux(List<Animal> animaux) {
	tableModelAnimal = new TableModelAnimal(animaux);		
	}
	
	public void addAnimal(Animal animal){
		tableModelAnimal.addAnimal(animal);
		setChanged();
        notifyObservers();
        clearChanged();
	}
	
	public void removeAnimal(int index){
		tableModelAnimal.removeAnimal(index);
	}

	public TableModelAnimal getTableModelAnimal(){
		return tableModelAnimal;
	}
	
	///Clients
	public TableModelClient getTableModelClient(){
		return tableModelClient;
	}
	
	public void loadClients(List<Client> clients) {
		tableModelClient = new TableModelClient(clients);		
	}
	
	public void addClient(Client c){
		tableModelClient.addClient(c);
		setChanged();
        notifyObservers();
        clearChanged();
	}
	
	public void loadRaces(List<Race> races) {
		tableModelRace = new TableModelRace(races);		
	}
	
	//Client recherché
	
	public TableModelClientRecherche loadClientRecherche (List<Client> clients) {
		
		tableModelClientRecherche = new TableModelClientRecherche(clients);		
		
		return tableModelClientRecherche;
	}

	public void addClientRecherche(Client client){
		tableModelClientRecherche.addClient(client);
		setChanged();
        notifyObservers();
        clearChanged();
	}
	
	///rdv
	public TableModelAgenda getTableModelAgenda(){
		return tableModelAgenda;
	}
	
	public void loadAgenda(List<Rdv> agenda) {
		tableModelAgenda = new TableModelAgenda(agenda);		
	}
}
