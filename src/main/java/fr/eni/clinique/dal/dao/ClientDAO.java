package fr.eni.clinique.dal.dao;

import java.util.List;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.dal.exception.DaoException;

public interface ClientDAO {

	List<Client> selectALL() throws DaoException;
	
	List<Client> selectByNom(String nom) throws DaoException;
	
	void update(Client newClient,String nomClient,String prenomClient,String adresse1, String adresse2,String ville,String assurance, String email, String remarque, Boolean archive,List<Animal> lesAnimaux)	 throws DaoException;
	
	Client insert(Client newClient) throws DaoException;
	
	void delete(Client newClient) throws DaoException;

	void deleteById(int parseInt);
	
	
}
