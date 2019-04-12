package fr.eni.clinique.ihm.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.clinique.bo.Race;

public class TableModelRace  extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8774347421245251928L;
	private List<Race> races = new ArrayList<>();
	private final String[] entetes = {"Race", "Espèce"};
	   
	 public TableModelRace(List<Race> races) {
	        super();
	        this.races = races;
	    }
	@Override
	public int getColumnCount() {
		return entetes.length;
	}

	@Override
	public int getRowCount() {
		 return races.size();
			}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
	switch (columnIndex) {
	   case 0:
           return races.get(rowIndex).getRace();
       case 1:
           return races.get(rowIndex).getEspece();
       default:
           return null;
       }
}
	}
