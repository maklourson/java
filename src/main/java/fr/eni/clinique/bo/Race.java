package fr.eni.clinique.bo;

public class Race {

	private String Race;
	private String Espece;
	
	public Race() {
		super();
	}

	public Race(String race, String espece) {
		super();
		Race = race;
		Espece = espece;
	}
	
	public String getRace() {
		return Race;
	}
	public void setRace(String race) {
		Race = race;
	}
	public String getEspece() {
		return Espece;
	}
	public void setEspece(String espece) {
		Espece = espece;
	}
	
	
	
	
	
}
