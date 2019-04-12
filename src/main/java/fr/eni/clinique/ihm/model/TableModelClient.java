package fr.eni.clinique.ihm.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.clinique.bo.Client;

public class TableModelClient extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8499603383460120724L;
	private List<Client> clients = new ArrayList<>();
    private final String[] entetes = { "Nom", "Prénom", "Code Postal", "Ville"};
   
    public TableModelClient(List<Client> clients) {
        super();
        this.clients = clients;
    }
    public void addAnimal(Client animal) {
        this.clients.add(animal);
    }
    
    public void removeAnimal(int index) {
        this.clients.remove(index);
        fireTableRowsDeleted(index, index);
    }  
	@Override
	public int getColumnCount() {
		 return entetes.length;
	}

	@Override
	public int getRowCount() {
		 return clients.size();
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
	public void addClient(Client c) {
		   this.clients.add(c);
		
	}

}
