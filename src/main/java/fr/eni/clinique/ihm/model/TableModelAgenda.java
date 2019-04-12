package fr.eni.clinique.ihm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import fr.eni.clinique.bll.exception.BLLException;
import fr.eni.clinique.bll.manager.impl.LoginMgerImpl;
import fr.eni.clinique.bo.Rdv;

public class TableModelAgenda extends AbstractTableModel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Rdv> agenda = new ArrayList<>();
	private final String[] entetes ={"CodeVeto","DateRdv","CodeAnimal"};
	
	public TableModelAgenda(List<Rdv> agenda) {
		super();
		this.agenda = agenda;
	}
	
	public void addRDV(Rdv newRdv){
		this.agenda.add(newRdv);
	}
	 public void removeRdv(int codeVeto) throws BLLException {
			LoginMgerImpl.getInstance().removeRdv(agenda.get(codeVeto));
	        this.agenda.remove(codeVeto);        
	        fireTableRowsDeleted(codeVeto, codeVeto);
	    }  

	/**
	 * 
	 */

	@Override
	public int getColumnCount() {
		
		return entetes.length;
	}

	@Override
	public int getRowCount() {
		
		return agenda.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex)  {
		

        switch (columnIndex) {
        
        case 0:
            return agenda.get(rowIndex).getCodeVet();
        case 1:
        	return agenda.get(rowIndex).getDateRDV();
        case 2:
            return agenda.get(rowIndex).getCodeAnimal();
        default:
            return null;
        }

	}

	@Override
	public void update(Observable o, Object arg) {
	
		
	}

}
