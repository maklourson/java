package fr.eni.clinique.ihm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import fr.eni.clinique.bll.exception.BLLException;
import fr.eni.clinique.bll.manager.impl.LoginMgerImpl;
import fr.eni.clinique.bo.Personnel;


public class TableModelPerso extends AbstractTableModel implements Observer{

	private static final long serialVersionUID = 5339658835698597380L;
	
	private List<Personnel> personnels = new ArrayList<>();
	
    private final String[] entetes = { "Nom", "MotPasse", "Rôle"};
 

    public TableModelPerso(List<Personnel> personnels) {
        super();
        this.personnels = personnels;
    }
    
    public void addPersonnel(Personnel personnel) {
        this.personnels.add(personnel);
    }
    
    public void removePersonnel(int index) throws BLLException {
//    	ConnexionController c = new ConnexionController(null);
//    	c.deletePersonnel(personnels.get(index));
    	
		LoginMgerImpl.getInstance().removePersonnel(personnels.get(index));
		
        this.personnels.remove(index);        
        fireTableRowsDeleted(index, index);
    }  

    @Override
    public int getRowCount() {
        return personnels.size();
    }

    @Override
    public int getColumnCount() {
        return entetes.length;
    }

    /**
     * retourne l'entete
     * 
     * @param columnIndex
     */
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	
        switch (columnIndex) {
        
        case 0:
            return personnels.get(rowIndex).getNom();
        case 1:
        	String perso = personnels.get(rowIndex).getMotPasse();
            return perso.replace(perso, "********");
        case 2:
            return personnels.get(rowIndex).getRole();
        default:
            return null;
        }

    }
	@Override
	public void update(Observable o, Object arg) {
		
	}

	
}
