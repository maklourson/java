package fr.eni.clinique.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique.bo.Race;
import fr.eni.clinique.common.util.ResourceUtil;
import fr.eni.clinique.dal.dao.RaceDAO;
import fr.eni.clinique.dal.exception.DaoException;
import fr.eni.clinique.dal.factory.MSSQLConnectionFactory;

public class RaceDAOJdbcImpl  implements RaceDAO{
private Connection connection = null;
	
	private static final String SELECT_ALL_QUERY = "select * from Races";
	private static final String INSERT_QUERY = "insert into Races (Race,Espece) values(?,?)";
	
	private Race getRace(ResultSet res) throws SQLException{
		Race race = new Race();
		
		race.setRace(res.getString("Race"));
		race.setEspece(res.getString("Espece"));
		
		return race;
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
	public List<Race> selectALL() throws DaoException {
		ouvertureConnection();
		List<Race> races = new ArrayList<>();
		
		try {
			Statement statement = (Statement) connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
			while(resultSet.next()){
				races.add(getRace(resultSet));
			}
		} catch (SQLException e) {
			throw new DaoException("erreur recupération des races");
		}
	
		return races;
	}

	@Override
	public Race insert(Race race) throws DaoException {
		Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
	try {
		   connection = MSSQLConnectionFactory.get();
           statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, race.getRace());
            statement.setString(2, race.getEspece());   
            
            }
	catch (SQLException e) {
		throw new DaoException("Erreur d'insertion race",e);
	}finally {
     ResourceUtil.safeClose(connection,statement,resultSet);
 }
	return race;
	}

	@Override
	public void delete(Race race) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByRace(String race, String espece) {
		// TODO Auto-generated method stub
		
	}

}
