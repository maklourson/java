package fr.eni.clinique.bo;



public class Rdv {
	
	
	private String dateRDV;	
	
	private Integer codeVet;
	private Integer codeAnimal;
	/**
	 * 
	 * @param dateRDV
	 * @param veterinaire
	 * @param animal
	 */
	public Rdv(String dateRDV, Integer veterinaire, Integer animal) {
		super();
		this.dateRDV = dateRDV;
		this.codeVet = veterinaire;
		this.codeAnimal = animal;
	}
	
	
	public Rdv() {
		super();
	}


	public Rdv(String dateRDV, Integer codeVet) {
		super();
		this.dateRDV = dateRDV;
		this.codeVet = codeVet;
	}


	/**
	 * @return the dateRDV
	 */
	public String getDateRDV() {
		return dateRDV;
	}
	/**
	 * @param dateRDV the dateRDV to set
	 */
	public void setDateRDV(String dateRDV) {
		this.dateRDV = dateRDV;
	}
	/**
	 * @return the veterinaire
	 */
	public Integer getVeterinaire() {
		return codeVet;
	}
	/**
	 * @param veterinaire the veterinaire to set
	 */
	public void setVeterinaire(Integer veterinaire) {
		this.codeVet = veterinaire;
	}
	public Integer getCodeVet() {
		return codeVet;
	}
	public void setCodeVet(Integer codeVet) {
		this.codeVet = codeVet;
	}
	public Integer getCodeAnimal() {
		return codeAnimal;
	}
	public void setCodeAnimal(Integer codeAnimal) {
		this.codeAnimal = codeAnimal;
	}
	/**
	 * @return the animal
	 */
	public Integer getAnimal() {
		return codeAnimal;
	}
	/**
	 * @param animal the animal to set
	 */
	public void setAnimal(Integer animal) {
		this.codeAnimal = animal;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Rdv [dateRDV=").append(dateRDV).append(", codeVet=").append(codeVet).append(", codeAnimal=")
				.append(codeAnimal).append("]");
		return builder.toString();
	}
	
	
	
}
