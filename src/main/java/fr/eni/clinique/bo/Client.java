package fr.eni.clinique.bo;

import java.util.ArrayList;
import java.util.List;

public class Client {
	private Integer codeClient;
	private String nomClient;
	private String prenomClient;
	private String adresse1;
	private String adresse2;
	private String codePostal;
	private String ville;
	private String numTel;
	private String assurance;
	private String email;
	private String remarque;
	private Boolean archive;	
	private List<Animal> lesAnimaux = new ArrayList<Animal>();
	/**
	 * @param codeClient
	 * @param nomClient
	 * @param prenomClient
	 * @param adresse1
	 * @param adresse2
	 * @param ville
	 * @param numTel
	 * @param assurance
	 * @param email
	 * @param remarque
	 * @param archive
	 * @param lesAnimaux
	 */
	public Client(Integer codeClient, String nomClient, String prenomClient, String adresse1, String adresse2,String codePostal,
			String ville, String numTel, String assurance, String email, String remarque, Boolean archive,
			List<Animal> lesAnimaux) {
		super();
		this.codeClient = codeClient;
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.adresse1 = adresse1;
		this.adresse2 = adresse2;
		this.codePostal=codePostal;
		this.ville = ville;
		this.numTel = numTel;
		this.assurance = assurance;
		this.email = email;
		this.remarque = remarque;
		this.archive = archive;
		this.lesAnimaux = lesAnimaux;
	}
	
	public Client(String nomClient, String prenomClient, String adresse1, String adresse2, String codePostal,
			String ville, String numTel, String assurance, String email, String remarque, Boolean archive) {
		super();
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.adresse1 = adresse1;
		this.adresse2 = adresse2;
		this.codePostal = codePostal;
		this.ville = ville;
		this.numTel = numTel;
		this.assurance = assurance;
		this.email = email;
		this.remarque = remarque;
		this.archive = archive;
	}

	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public Integer getCodeClient() {
		return codeClient;
	}
	public void setCodeClient(Integer codeClient) {
		this.codeClient = codeClient;
	}
	public String getNomClient() {
		return nomClient;
	}
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	public String getPrenomClient() {
		return prenomClient;
	}
	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}
	public String getAdresse1() {
		return adresse1;
	}
	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}
	public String getAdresse2() {
		return adresse2;
	}
	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getNumTel() {
		return numTel;
	}
	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}
	public String getAssurance() {
		return assurance;
	}
	public void setAssurance(String assurance) {
		this.assurance = assurance;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRemarque() {
		return remarque;
	}
	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}
	public Boolean getArchive() {
		return archive;
	}
	public void setArchive(Boolean archive) {
		this.archive = archive;
	}
	public List<Animal> getLesAnimaux() {
		return lesAnimaux;
	}
	public Client() {
		super();
	}
	public void setLesAnimaux(List<Animal> lesAnimaux) {
		this.lesAnimaux = lesAnimaux;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Client [codeClient=").append(codeClient).append(", nomClient=").append(nomClient)
				.append(", prenomClient=").append(prenomClient).append(", adresse1=").append(adresse1)
				.append(", adresse2=").append(adresse2).append(", ville=").append(ville).append(", numTel=")
				.append(numTel).append(", assurance=").append(assurance).append(", email=").append(email)
				.append(", remarque=").append(remarque).append(", archive=").append(archive).append(", lesAnimaux=")
				.append(lesAnimaux).append("]");
		return builder.toString();
	}
	
	
	

}
