package fr.eni.clinique.ihm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import fr.eni.clinique.bo.Client;
public class TableModelClientRecherche extends AbstractTableModel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -629320565905656212L;
	
	private List<Client> clients = new ArrayList<>();
	
    private final String[] entetes = { "NomClient", "PrenomClient", "CodePostal","Ville"};
    
    public TableModelClientRecherche(List<Client> clients) {
        super();
        this.clients = clients;
    }
    
    public void addClient(Client client) {
        this.clients.add(client);
    }
    
	@Override
	public int getColumnCount() {
		return entetes.length;
	}

	@Override
	public int getRowCount() {
		
		return clients.size();
	}
	
	 public String getColumnName(int columnIndex) {
	        return entetes[columnIndex];
	    }

	 @Override
	    public Object getValueAt(int rowIndex, int columnIndex) {
	        switch (columnIndex) {
	        case 0:
	            return clients.get(rowIndex).getNomClient();
	        case 1:
	            return clients.get(rowIndex).getPrenomClient();
	        case 2:
	            return clients.get(rowIndex).getCodePostal();
	        case 3:
	            return clients.get(rowIndex).getVille();
	        default:
	            return null;
	        }
	        }

	@Override
	public void update(Observable arg0, Object arg1) {
		
	}

}
