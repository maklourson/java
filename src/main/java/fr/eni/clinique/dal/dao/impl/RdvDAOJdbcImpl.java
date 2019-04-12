package fr.eni.clinique.dal.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique.bo.Rdv;
import fr.eni.clinique.dal.dao.RdvDAO;
import fr.eni.clinique.dal.exception.DaoException;
import fr.eni.clinique.dal.factory.MSSQLConnectionFactory;

public class RdvDAOJdbcImpl implements RdvDAO{
	
	private Connection connection = null;
	
	private static final String SELECT_ALL_QUERY = "select * from Agendas";	
	
private Rdv getAgenda(ResultSet res) throws SQLException{
		
		Rdv agenda = new Rdv();
		
		agenda.setCodeVet(res.getInt("CodeVeto"));
		agenda.setDateRDV(res.getString("DateRdv"));
		agenda.setCodeAnimal(res.getInt("CodeAnimal"));
		
		return agenda;
	}
	
	
	
	@Override
	public List<Rdv> selectALL() throws DaoException {
		ouvertureConnection();
		List<Rdv> agenda = new ArrayList<>();
		try {
			Statement statement = (Statement) connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
			while(resultSet.next()){
				agenda.add(getAgenda(resultSet));
			}
		} catch (SQLException e) {
			throw new DaoException("Erreur recupération de l'agenda");
		}
	
		return agenda;
	}
	private void ouvertureConnection() throws DaoException{
		try {
			if (connection == null){
				connection = MSSQLConnectionFactory.get();
			}
		} catch (SQLException e) {
		throw new DaoException("Erreur connexion",e);
		}
	}



	@Override
	public void delete(Rdv rdv) throws DaoException {
		// TODO Auto-generated method stub
		
	}
}
