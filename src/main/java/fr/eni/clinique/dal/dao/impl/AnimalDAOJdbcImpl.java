package fr.eni.clinique.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.common.util.ResourceUtil;
import fr.eni.clinique.dal.dao.AnimalDAO;
import fr.eni.clinique.dal.exception.DaoException;
import fr.eni.clinique.dal.factory.MSSQLConnectionFactory;

public class AnimalDAOJdbcImpl implements AnimalDAO{
	private Connection connection = null;
	
	private static final String SELECT_ALL_QUERY = "select * from Animaux";
	private static final String INSERT_QUERY = "insert into Animaux (NomAnimal, Sexe, Couleur, Race, Espece, CodeClient,Tatouage,Antecedents,Archive) values(?,?,?,?,?,?,?,?,?)";
	private static final String DELETE_QUERY ="DELETE FROM Animaux WHERE CodeAnimal=?";
	private static final String SELECT_BY_RACE="SELECT * FROM Animaux WHERE Race=?";
	private static final String UPDATE_QUERY="UPDATE Animaux SET CodeClient=?, Tatouage=?, Antecedents=?, Archive=? WHERE CodeAnimal=?";
	private static final String SELECT_BY_CodeClient="SELECT * FROM Animaux WHERE CodeClient=?";
	
	private Animal getAnimal(ResultSet res) throws SQLException{
		
		Animal animal = new Animal();
		animal.setCodeAnimal(res.getInt("CodeAnimal"));
		animal.setNomAnimal(res.getString("NomAnimal"));
		animal.setSexe(res.getString("Sexe"));
		animal.setCouleur(res.getString("Couleur"));
		animal.setRace(res.getString("Race"));
		animal.setEspece(res.getString("Espece"));
		animal.setCodeClient(res.getInt("CodeClient"));
		animal.setTatouage(res.getString("Tatouage"));
		animal.setAntecedents(res.getString("Antecedents"));
		animal.setArchive(res.getBoolean("Archive"));
		
		return animal;
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
	public List<Animal> selectALL() throws DaoException {
		ouvertureConnection();
		List<Animal> animaux = new ArrayList<>();
		
		try {
			Statement statement = (Statement) connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
			while(resultSet.next()){
				animaux.add(getAnimal(resultSet));
			}
		} catch (SQLException e) {
			throw new DaoException("Erreur recupération des animaux");
		}
	
		return animaux;
	}

	@Override
	public Animal insert(Animal newAnimal) throws DaoException {
			Connection connection = null;
	        PreparedStatement statement = null;
	        ResultSet resultSet = null;
	        
		try {
			   connection = MSSQLConnectionFactory.get();
	           statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);

	            statement.setString(1, newAnimal.getNomAnimal());
	            statement.setString(2, newAnimal.getSexe());
	            statement.setString(3, newAnimal.getCouleur());
	            statement.setString(4, newAnimal.getRace());
	            statement.setString(5, newAnimal.getEspece());
	            statement.setInt(6, newAnimal.getCodeClient());
	            statement.setString(7, newAnimal.getTatouage());
	            statement.setString(8, newAnimal.getAntecedents());
	            statement.setBoolean(9, newAnimal.getArchive());
	            
	            if (statement.executeUpdate() == 1) {
	                resultSet = statement.getGeneratedKeys();
	                if (resultSet.next()) {
	                	newAnimal.setCodeAnimal(resultSet.getInt(1));
	                }
			} }
		catch (SQLException e) {
			throw new DaoException("Erreur d'insertion personnel",e);
		}finally {
         ResourceUtil.safeClose(connection,statement,resultSet);
     }
		return newAnimal;
	}

	@Override
	public void update(Animal newAnimal, int codeClient, String tatouage, String antecedents, Boolean archive)
			throws DaoException {

        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = MSSQLConnectionFactory.get();
            statement = connection.prepareStatement(UPDATE_QUERY);
            
            statement.setString(1, newAnimal.getNomAnimal());
            statement.setString(2, newAnimal.getSexe());
            statement.setString(3, newAnimal.getCouleur());
            statement.setString(4, newAnimal.getRace());
            statement.setString(1, newAnimal.getEspece());
            statement.setInt(5, codeClient);
            statement.setString(6, tatouage);
            statement.setString(7, antecedents);
            statement.setBoolean(8, archive);
            statement.executeUpdate();
            
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(connection, statement);
        }
	}

	@Override
	public void delete(Animal newAnimal) throws DaoException {
		    Connection connection = null;
	        PreparedStatement statement = null;
	        
	        try {
	            connection = MSSQLConnectionFactory.get();
	            statement = connection.prepareStatement(DELETE_QUERY);
	            statement.setInt(1, newAnimal.getCodeAnimal());
	            statement.executeUpdate();
	            
	        } catch(SQLException e) {
	            throw new DaoException(e.getMessage(), e);
	        } finally {
	            ResourceUtil.safeClose(connection, statement);
	        }
		
	}

	@Override
	public List<Animal> selectByRace(String race) throws DaoException {
		   PreparedStatement statement = null;
	        ResultSet resultSet = null;
	        
	        List<Animal> lesAnimaux = new ArrayList<Animal>();
	        try {
	            connection = MSSQLConnectionFactory.get();
	            statement = connection.prepareStatement(SELECT_BY_RACE);
	            statement.setString(1, race);
	            resultSet = statement.executeQuery();

	            while (resultSet.next()) {
	            	lesAnimaux.add((Animal) resultSet);
	            }
	            
	        } catch(SQLException e) {
	            throw new DaoException(e.getMessage(), e);
	        } finally {
	            ResourceUtil.safeClose(connection, statement);
	        }
	        
	        return lesAnimaux;
	}
	public List<Animal> selectByCodeClient(Integer code) throws DaoException {
		   PreparedStatement statement = null;
	        ResultSet resultSet = null;
	        
	        List<Animal> lesAnimaux = new ArrayList<Animal>();
	        try {
	            connection = MSSQLConnectionFactory.get();
	            statement = connection.prepareStatement(SELECT_BY_CodeClient);
	            statement.setInt(1, code);
	            resultSet = statement.executeQuery();

	            while (resultSet.next()) {
	            	lesAnimaux.add((Animal) resultSet);
	            }
	            
	        } catch(SQLException e) {
	            throw new DaoException(e.getMessage(), e);
	        } finally {
	            ResourceUtil.safeClose(connection, statement);
	        }
	        
	        return lesAnimaux;
	}

	@Override
	public void deleteById(int code) throws DaoException {
		  Connection connection = null;
	        PreparedStatement statement = null;
	        
	        try {
	            connection = MSSQLConnectionFactory.get();
	            statement = connection.prepareStatement(DELETE_QUERY);
	            statement.setInt(1,code);
	            statement.executeUpdate();
	            
	        } catch(SQLException e) {
	            throw new DaoException(e.getMessage(), e);
	        } finally {
	            ResourceUtil.safeClose(connection, statement);
	        }
	}

}
