package fr.eni.clinique.bo;

public class Animal {
	private Integer codeAnimal;
	private String nomAnimal;
	private String sexe;
	private String couleur;
	private String race;
	private String espece;
	private int codeClient;
	private String tatouage;
	private String antecedents;
	private Boolean archive;
	/**
	 * @param codeAnimal
	 * @param nomAnimal
	 * @param sexe
	 * @param couleur
	 * @param race
	 * @param espece
	 * @param codeClient
	 * @param tatouage
	 * @param antecedents
	 * @param archive
	 * @param codeClient 
	 */
	public Animal(Integer codeAnimal, String nomAnimal, String sexe, String couleur, String race, String espece, String tatouage, String antecedents, Boolean archive, int codeClient) {
		super();
		this.codeAnimal = codeAnimal;
		this.nomAnimal = nomAnimal;
		this.sexe = sexe;
		this.couleur = couleur;
		this.race = race;
		this.espece = espece;
		this.codeClient = codeClient;
		this.tatouage = tatouage;
		this.antecedents = antecedents;
		this.archive = archive;
	}
	
	public Animal(Integer codeAnimal, String nomAnimal, String sexe, String couleur, String race, String espece,
			int codeClient, String tatouage, String antecedents, Boolean archive) {
		super();
		this.codeAnimal = codeAnimal;
		this.nomAnimal = nomAnimal;
		this.sexe = sexe;
		this.couleur = couleur;
		this.race = race;
		this.espece = espece;
		this.codeClient = codeClient;
		this.tatouage = tatouage;
		this.antecedents = antecedents;
		this.archive = archive;
	}

	public int getCodeClient() {
		return codeClient;
	}

	public void setCodeClient(int codeClient) {
		this.codeClient = codeClient;
	}

	public Animal() {
		super();
	}
	/**
	 * @return the codeAnimal
	 */
	public Integer getCodeAnimal() {
		return codeAnimal;
	}
	/**
	 * @param codeAnimal the codeAnimal to set
	 */
	public void setCodeAnimal(Integer codeAnimal) {
		this.codeAnimal = codeAnimal;
	}
	/**
	 * @return the nomAnimal
	 */
	public String getNomAnimal() {
		return nomAnimal;
	}
	/**
	 * @param nomAnimal the nomAnimal to set
	 */
	public void setNomAnimal(String nomAnimal) {
		this.nomAnimal = nomAnimal;
	}
	/**
	 * @return the sexe
	 */
	public String getSexe() {
		return sexe;
	}
	/**
	 * @param sexe the sexe to set
	 */
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	/**
	 * @return the couleur
	 */
	public String getCouleur() {
		return couleur;
	}
	/**
	 * @param couleur the couleur to set
	 */
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	/**
	 * @return the race
	 */
	public String getRace() {
		return race;
	}
	/**
	 * @param race the race to set
	 */
	public void setRace(String race) {
		this.race = race;
	}
	/**
	 * @return the espece
	 */
	public String getEspece() {
		return espece;
	}
	/**
	 * @param espece the espece to set
	 */
	public void setEspece(String espece) {
		this.espece = espece;
	}
	
	/**
	 * @return the tatouage
	 */
	public String getTatouage() {
		return tatouage;
	}
	/**
	 * @param tatouage the tatouage to set
	 */
	public void setTatouage(String tatouage) {
		this.tatouage = tatouage;
	}
	/**
	 * @return the antecedents
	 */
	public String getAntecedents() {
		return antecedents;
	}
	/**
	 * @param antecedents the antecedents to set
	 */
	public void setAntecedents(String antecedents) {
		this.antecedents = antecedents;
	}
	/**
	 * @return the archive
	 */
	public Boolean getArchive() {
		return archive;
	}
	/**
	 * @param archive the archive to set
	 */
	public void setArchive(Boolean archive) {
		this.archive = archive;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Animal [codeAnimal=").append(codeAnimal).append(", nomAnimal=").append(nomAnimal)
				.append(", sexe=").append(sexe).append(", couleur=").append(couleur).append(", race=").append(race)
				.append(", espece=").append(espece).append(", codeClient=").append(codeClient).append(", tatouage=")
				.append(tatouage).append(", antecedents=").append(antecedents).append(", archive=").append(archive)
				.append("]");
		return builder.toString();
	}
	
	

}
