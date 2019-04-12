package fr.eni.clinique.dal.factory;

import fr.eni.clinique.dal.dao.AnimalDAO;
import fr.eni.clinique.dal.dao.ClientDAO;
import fr.eni.clinique.dal.dao.PersonnelDAO;
import fr.eni.clinique.dal.dao.RaceDAO;
import fr.eni.clinique.dal.dao.RdvDAO;
import fr.eni.clinique.dal.dao.impl.AnimalDAOJdbcImpl;
import fr.eni.clinique.dal.dao.impl.ClientDAOJdbcImpl;
import fr.eni.clinique.dal.dao.impl.ConnexionDAOJdbcImpl;
import fr.eni.clinique.dal.dao.impl.PersonnelDAOJdbcImpl;
import fr.eni.clinique.dal.dao.impl.RaceDAOJdbcImpl;
import fr.eni.clinique.dal.dao.impl.RdvDAOJdbcImpl;
import fr.eni.clinique.dal.jdbc.ConnexionDAO;

public class DaoFactory {

	public static ConnexionDAO connexionDAO() {

		return ConnexionDAOJdbcImpl.getInstance();
	}

	public static PersonnelDAO personnelDao() {
		PersonnelDAOJdbcImpl personnel = new PersonnelDAOJdbcImpl();
		return personnel;
	}
	
	public static AnimalDAO animalDAO() {
		AnimalDAOJdbcImpl animal = new AnimalDAOJdbcImpl();
		return animal;
	}

	public static ClientDAO clientDAO() {
		ClientDAOJdbcImpl client = new ClientDAOJdbcImpl();
		
		return client;
	}

	public static RaceDAO raceDAO() {
		RaceDAOJdbcImpl race = new RaceDAOJdbcImpl();
		return race;	
		}
	public static RdvDAO rdvDAO() {
		RdvDAOJdbcImpl rdv = new RdvDAOJdbcImpl();
		return rdv;	
		}
}
