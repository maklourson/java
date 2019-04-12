package fr.eni.clinique.dal.dao;

import java.util.List;

import fr.eni.clinique.bo.Race;
import fr.eni.clinique.dal.exception.DaoException;

public interface RaceDAO {
	
	List<Race> selectALL() throws DaoException;
	
	Race insert(Race race) throws DaoException;
	
	void delete(Race race) throws DaoException;

	void deleteByRace(String race,String espece);
}
