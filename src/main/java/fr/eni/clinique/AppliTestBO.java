package fr.eni.clinique;

import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.bo.Rdv;

public class AppliTestBO {

	public static void main(String[] args) {
		//animaux
		List<Animal> lesAnimaux = new ArrayList<>();
		Animal A1 = new Animal(1, "nomAnimal", "sexe", "couleur", "race", "espece",2,"tatouage", "antecedents", true);
		Animal A2 = new Animal(2, "nomAnimal2", "sexe2", "couleur2", "race2", "espece2",3,"tatouage2", "antecedents2", true);
		Animal A3 = new Animal(3, "nomAnimal3", "sexe3", "couleur3", "race3", "espece3",4,"tatouage3", "antecedents3", true);
		
		lesAnimaux.add(A3);
		lesAnimaux.add(A2);
		lesAnimaux.add(A1);
		//Client c=  new Client(1, "nomClient", "prenomClient", "adresse1", "adresse2", 37000,"ville", "numTel", "assurance", "email", "remarque", true, lesAnimaux);
		//veterinaire
		Personnel veto = new Personnel(1, "nom", "motPasse", "role", true);		
		Rdv rdv = new Rdv("26/02/2018", veto, A1);
		
		System.out.println(rdv);
		
		
	}

}
