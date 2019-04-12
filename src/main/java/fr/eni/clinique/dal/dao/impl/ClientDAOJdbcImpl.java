package fr.eni.clinique.dal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.common.util.ResourceUtil;
import fr.eni.clinique.dal.dao.ClientDAO;
import fr.eni.clinique.dal.exception.DaoException;
import fr.eni.clinique.dal.factory.MSSQLConnectionFactory;

public class ClientDAOJdbcImpl implements ClientDAO{

	private Connection connection = null;

	private  PreparedStatement statement = null;
	private ResultSet resultSet = null;
	private ResultSet res = null;
	
	private static final String SELECT_ALL_QUERY = "select * from Clients";
	private static final String INSERT_QUERY = "insert into Clients(NomClient, PrenomClient, Adresse1, Adresse2,Ville,NumTel,Assurance,Email,Remarque,Archive) values(?,?,?,?,?,?,?,?,?,?)";
	private static final String DELETE_QUERY ="DELETE FROM Clients WHERE codeClient=?";
	private static final String SELECT_BY_NOM="SELECT CodeClient, NomClient, PrenomClient, Adresse1, Adresse2,Ville,NumTel,Assurance,Email,Remarque,Archive FROM Clients WHERE NomClient=?";
	private static final String UPDATE_QUERY="UPDATE Clients SET NomClient=?, PrenomClient=?, Adresse1=?, Adresse2=?,Ville=?,Assurance=?,Email=?,Remarque=?,Archive=?,lesAnimaux=?";
	
	
	private Client getClient(ResultSet res) throws SQLException{
		
		Client client = new Client();
		
		client.setCodeClient(res.getInt("CodeClient"));
		client.setNomClient(res.getString("NomClient"));
		client.setPrenomClient(res.getString("PrenomClient"));
		client.setAdresse1(res.getString("Adresse1"));
		client.setAdresse2(res.getString("Adresse2"));
		client.setVille(res.getString("Ville"));
		client.setNumTel(res.getString("NumTel"));
		client.setAssurance(res.getString("Assurance"));
		client.setEmail(res.getString("Email"));
		client.setRemarque(res.getString("Remarque"));
		client.setArchive(res.getBoolean("Archive"));
	
		return client;
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
	public List<Client> selectALL() throws DaoException {
		ouvertureConnection();
		List<Client> clients = new ArrayList<>();
		
		try {
			Statement statement = (Statement) connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
			while(resultSet.next()){
				clients.add(getClient(resultSet));
			}
		} catch (SQLException e) {
			throw new DaoException("erreur recupération du client");
		}
	
		return clients;
	}

	@Override
	public List<Client> selectByNom(String nom) throws DaoException {
	        
	        List<Client> lesClients = new ArrayList<Client>();
	        try {
	            connection = MSSQLConnectionFactory.get();
	            statement = connection.prepareStatement(SELECT_BY_NOM);
	            statement.setString(1, nom);
	            resultSet = statement.executeQuery();

	            while (resultSet.next()) {
	            	lesClients.add(getClient(resultSet));
	            }
	            } catch(SQLException e) {
	            throw new DaoException(e.getMessage(), e);
	        } finally {
	            ResourceUtil.safeClose(connection, statement);
	        }
	        
	        return lesClients;
	}

	@Override
	public void update(Client newClient, String nomClient, String prenomClient, String adresse1, String adresse2,
			String ville, String assurance, String email, String remarque, Boolean archive, List<Animal> lesAnimaux)
			throws DaoException {
        
        try {
            connection = MSSQLConnectionFactory.get();
            statement = connection.prepareStatement(UPDATE_QUERY);
            
            statement.setString(1, newClient.getNomClient());
            statement.setString(2, newClient.getPrenomClient());
            statement.setString(3, newClient.getAdresse1());
            statement.setString(4, newClient.getAdresse2());
            statement.setString(5, newClient.getVille());
            statement.setString(6, newClient.getAssurance());
            statement.setString(7, newClient.getEmail());
            statement.setString(8, newClient.getRemarque());
            statement.setBoolean(9, newClient.getArchive());
            
            
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            ResourceUtil.safeClose(connection, statement);
        }
	}
		

	@Override
	public Client insert(Client client) throws DaoException {
	        
		try {
			  connection = MSSQLConnectionFactory.get();
			  statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
			
			  statement.setString(1,client.getNomClient());
			  statement.setString(2,client.getPrenomClient());
			  statement.setString(3,client.getAdresse1());
			  statement.setString(4,client.getAdresse2());
			  statement.setString(5,client.getVille());
			  statement.setString(6,client.getNumTel());
			  statement.setString(7,client.getAssurance());
			  statement.setString(8,client.getEmail());
			  statement.setString(9,client.getRemarque());
			  statement.setBoolean(10,client.getArchive());
			  
			  if (statement.executeUpdate() == 1) {
	                res= statement.getGeneratedKeys();
	                if (res.next()) {
	                	client.setCodeClient(res.getInt(1));
	                }
			} }
		catch (SQLException e) {
			throw new DaoException("Erreur d'insertion client",e); 
		}finally {
          ResourceUtil.safeClose(connection,statement,res);
      }
		return client;
	}
	

	@Override
	public void delete(Client newClient) throws DaoException {
	        
	        try {
	            connection = MSSQLConnectionFactory.get();
	            statement = connection.prepareStatement(DELETE_QUERY);
	            statement.setInt(1, newClient.getCodeClient());
	            statement.executeUpdate();
	            
	        } catch(SQLException e) {
	            throw new DaoException(e.getMessage(), e);
	        } finally {
	            ResourceUtil.safeClose(connection, statement);
	        }
		
	}
	@Override
	public void deleteById(int code) {
		  Connection connection = null;
	        PreparedStatement statement = null;
	        
	        try {
	            connection = MSSQLConnectionFactory.get();
	            statement = connection.prepareStatement(DELETE_QUERY);
	            statement.setInt(1, code);
	            statement.executeUpdate();
	            
	        } catch(SQLException e) {
	            try {
					throw new DaoException(e.getMessage(), e);
				} catch (DaoException e1) {
					e1.printStackTrace();
				}
	        } finally {
	            ResourceUtil.safeClose(connection, statement);
	        }
		
	}

}
