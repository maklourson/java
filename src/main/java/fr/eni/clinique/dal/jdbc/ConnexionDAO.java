package fr.eni.clinique.dal.jdbc;

import fr.eni.clinique.dal.exception.DaoException;

public interface ConnexionDAO {

	void connexion() throws DaoException;

}
