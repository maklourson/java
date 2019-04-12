package fr.eni.clinique.dal.dao.impl;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.common.util.ResourceUtil;
import fr.eni.clinique.dal.dao.PersonnelDAO;
import fr.eni.clinique.dal.exception.DaoException;
import fr.eni.clinique.dal.factory.MSSQLConnectionFactory;


public class PersonnelDAOJdbcImpl implements PersonnelDAO{
	
	private Connection connection = null;
	
	private static final String SELECT_ALL_QUERY = "select * from Personnels where Archive not in ('true')";	
	private static final String INSERT_QUERY = "insert into Personnels(Nom, MotPasse, Role, Archive) values(?,?,?,?)";
	private static final String DELETE_QUERY ="UPDATE Personnels SET Archive=? WHERE CodePers=?";
	private static final String SELECT_BY_ROLE="SELECT * FROM Personnels WHERE Role=?";
	private static final String UPDATE_QUERY="UPDATE Personnels SET Nom=?, MotPasse=?, Role=?, Archive=? WHERE CodePers=?";
	private static final String SELECT_NOM_PASS = "select Nom, MotPasse from Personnels where Nom=? and MotPasse = ?";
	private static final String SELECT_CONNECT = "select CodePers, Nom, MotPasse,Role,Archive from Personnels where Nom=?";
	
	private Personnel getPersonnel(ResultSet res) throws SQLException{
		
		Personnel personnel = new Personnel();
		
		personnel.setCodePers(res.getInt("CodePers"));
		personnel.setNom(res.getString("Nom"));
		personnel.setMotPasse(res.getString("MotPasse"));
		personnel.setRole(res.getString("Role"));
		personnel.setArchive(res.getBoolean("Archive"));
		
		return personnel;
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
	public List<Personnel> selectALL() throws DaoException {
		ouvertureConnection();
		List<Personnel> personnels = new ArrayList<>();
		
		try {
			Statement statement = (Statement) connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
			while(resultSet.next()){
				personnels.add(getPersonnel(resultSet));
			}
		} catch (SQLException e) {
			throw new DaoException("erreur recupération du personnels");
		}
	
		return personnels;
	}

	@Override
	public Personnel insert(Personnel newPersonnel) throws DaoException {
		    Connection connection = null;
	        PreparedStatement statement = null;
	        ResultSet resultSet = null;
	        
		try {
			   connection = MSSQLConnectionFactory.get();
	           statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
	           
	            statement.setString(1, newPersonnel.getNom());
	            statement.setString(2, newPersonnel.getMotPasse());
	            statement.setString(3, newPersonnel.getRole());
	            statement.setBoolean(4, newPersonnel.isArchive());
	            
	            if (statement.executeUpdate() == 1) {
	                resultSet = statement.getGeneratedKeys();
	                if (resultSet.next()) {
	                	newPersonnel.setCodePers(resultSet.getInt(1));
	                }
			} }
		catch (SQLException e) {
			throw new DaoException("Erreur d'insertion personnel",e);
		}finally {
            ResourceUtil.safeClose(connection,statement,resultSet);
        }
		return newPersonnel;
	}

	@Override
	public void update(Personnel newPersonnel, String pass) throws DaoException {

        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = MSSQLConnectionFactory.get();
            statement = connection.prepareStatement(UPDATE_QUERY);
            
          
            statement.setString(1, newPersonnel.getNom());
            statement.setString(2, pass);
            statement.setString(3, newPersonnel.getRole());
            statement.setBoolean(4, newPersonnel.isArchive());
            statement.setInt(5, newPersonnel.getCodePers());
            statement.executeUpdate();
            
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(connection, statement);
        }
	}

	@Override
	public void delete(Personnel newPersonnel) throws DaoException {
		  Connection connection = null;
	        PreparedStatement statement = null;
	        
	        try {
	            connection = MSSQLConnectionFactory.get();
	            statement = connection.prepareStatement(DELETE_QUERY);
	            newPersonnel.setArchive(true);
	            statement.setBoolean(1, newPersonnel.isArchive());
	            statement.setInt(2, newPersonnel.getCodePers());
	            statement.executeUpdate();
	            
	        } catch(SQLException e) {
	            throw new DaoException(e.getMessage(), e);
	        } finally {
	            ResourceUtil.safeClose(connection, statement);
	        }
	}

	@Override
	public List<Personnel> selectByRole(String role) throws DaoException {
	        PreparedStatement statement = null;
	        ResultSet resultSet = null;
	        
	        List<Personnel> lesPersonnels = new ArrayList<Personnel>();
	        try {
	            connection = MSSQLConnectionFactory.get();
	            statement = connection.prepareStatement(SELECT_BY_ROLE);
	            statement.setString(1, role);
	            resultSet = statement.executeQuery();

	            while (resultSet.next()) {
	            	lesPersonnels.add((Personnel) resultSet);
	            }
	            
	        } catch(SQLException e) {
	            throw new DaoException(e.getMessage(), e);
	        } finally {
	            ResourceUtil.safeClose(connection, statement);
	        }
	        
	        return lesPersonnels;
	}

	@Override
	public boolean authenticate(Personnel personnel) throws DaoException {
		boolean retour = false;
		Connection connection = null;
		PreparedStatement statement =null;
		ResultSet resultSet = null;		
		
		try {
			connection = MSSQLConnectionFactory.get();
			statement = connection.prepareStatement(SELECT_NOM_PASS);
			statement.setString(1, personnel.getNom());
			statement.setString(2, personnel.getMotPasse());
			
			resultSet = statement.executeQuery();
			if(resultSet.next()){
				retour =true;
			}
			
			
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(),e);
		}finally {
			ResourceUtil.safeClose(connection,statement,resultSet);
		}
		
		return retour;
	}

	public Personnel connexion(String nom) throws DaoException {
		Personnel retour = null;
		Connection connection = null;
		PreparedStatement statement =null;
		ResultSet resultSet = null;		
		
		try {
			connection = MSSQLConnectionFactory.get();
			statement = connection.prepareStatement(SELECT_CONNECT);
			statement.setString(1, nom);		
			
			resultSet = statement.executeQuery();
			if(resultSet.next()){
				retour = getPersonnel(resultSet);				
			}			
			
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(),e);
		}finally {
			ResourceUtil.safeClose(connection,statement,resultSet);
		}
		return retour;
		
		
	}

}
