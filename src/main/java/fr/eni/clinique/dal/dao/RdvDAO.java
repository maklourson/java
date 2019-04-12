package fr.eni.clinique.dal.dao;

import java.util.List;

import fr.eni.clinique.bo.Rdv;
import fr.eni.clinique.dal.exception.DaoException;

public interface RdvDAO {
	List<Rdv> selectALL() throws DaoException;

	void delete(Rdv rdv) throws DaoException;
	
}
