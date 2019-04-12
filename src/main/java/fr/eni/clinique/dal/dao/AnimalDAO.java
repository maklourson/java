package fr.eni.clinique.dal.dao;

import java.util.List;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.dal.exception.DaoException;

public interface AnimalDAO {
	
	List<Animal> selectALL() throws DaoException;
	
	Animal insert(Animal newAnimal) throws DaoException;
	
	void update(Animal newAnimal, int codeClient,String tatouage,String antecedents,Boolean archive) throws DaoException;
	
	void delete(Animal newAnimal) throws DaoException;
	
	List<Animal> selectByRace(String race) throws DaoException;
	
	List<Animal> selectByCodeClient(Integer code) throws DaoException; 
	
	void deleteById(int parseInt) throws DaoException;
	
}