package fr.eni.clinique.bo;

public class Personnel {

	private Integer codePers;
	private String nom;
	private String motPasse;
	private String role;
	private boolean archive;
	
	
	public Personnel() {
		super();
	}

	public Personnel(Integer codePers, String nom, String motPasse, String role, boolean archive) {
		super();
		this.codePers = codePers;
		this.nom = nom;
		this.motPasse = motPasse;
		this.role = role;
		this.archive = archive;
	}
	public Personnel(String nom, String motPasse, String role, boolean archive) {
		super();
		
		this.nom = nom;
		this.motPasse = motPasse;
		this.role = role;
		this.archive = archive;
	}
	public Personnel(String nom, String motPasse) {
		super();
		this.nom = nom;
		this.motPasse = motPasse;
	}

	public Integer getCodePers() {
		return codePers;
	}

	public void setCodePers(Integer codePers) {
		this.codePers = codePers;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getMotPasse() {
		return motPasse;
	}

	public void setMotPasse(String motPasse) {
		this.motPasse = motPasse;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Personnel [codePers=").append(codePers).append(", nom=").append(nom).append(", motPasse=")
				.append(motPasse).append(", role=").append(role).append(", archive=").append(archive).append("]");
		return builder.toString();
	}


		
	
}
