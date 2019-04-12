package fr.eni.clinique.ihm.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.clinique.bo.Animal;

public class TableModelAnimal extends AbstractTableModel  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7227623479501135868L;
	/**
	 * 
	 */
	private List<Animal> animaux = new ArrayList<>();
    private final String[] entetes = { "Numéro", "Nom", "Sexe", "Couleur","Race","Espèce","Tatouage"};
   
    public TableModelAnimal(List<Animal> animaux) {
        super();
        this.animaux = animaux;
    }
    
    public void addAnimal(Animal animal) {
        this.animaux.add(animal);
    }
    
    public void removeAnimal(int index) {
        this.animaux.remove(index);
        fireTableRowsDeleted(index, index);
    }  
	@Override
	public int getColumnCount() {
		 return entetes.length;
	}

	@Override
	public int getRowCount() {
		 return animaux.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		 switch (columnIndex) {
	        case 0:
	            return animaux.get(rowIndex).getCodeAnimal();
	        case 1:
	            return animaux.get(rowIndex).getNomAnimal();
	        case 2:
	            return animaux.get(rowIndex).getSexe();
	        case 3:
	            return animaux.get(rowIndex).getCouleur();
	        case 4:
	            return animaux.get(rowIndex).getRace();
	        case 5:
	            return animaux.get(rowIndex).getEspece();
	        case 6:
	            return animaux.get(rowIndex).getTatouage();
	        default:
	            return null;
	        }
	}

}
