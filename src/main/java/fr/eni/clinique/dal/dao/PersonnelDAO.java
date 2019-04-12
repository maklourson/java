package fr.eni.clinique.dal.dao;

import java.util.List;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.exception.DaoException;

public interface PersonnelDAO {
	
	List<Personnel> selectALL() throws DaoException;
	
	Personnel insert(Personnel newPersonnel) throws DaoException;
	void update(Personnel newPersonnel, String pass) throws DaoException;
	void delete(Personnel newPersonnel) throws DaoException;
	
	List<Personnel> selectByRole(String role) throws DaoException;
	
	// savoir si une personne existe grace à son nom et mot de passe renvoie vrai si oui faux autrement
	
	public boolean authenticate (Personnel personnel) throws DaoException;
	public Personnel connexion (String nom) throws DaoException;
}
