package web.model;

import java.util.Observable;

public class Vendeur extends User {
	private String typeUser;
	
	public Vendeur() {
		// TODO Auto-generated constructor stub
	}
	
	public Vendeur(String firstName, String lastName, String sexe, String phone, String email, String password, String typeUser) {
		super(firstName, lastName, sexe, phone, email, password);
		this.typeUser = typeUser;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	public String getTypeUser() {
		return typeUser;
	}

	public void setTypeUser(String typeUser) {
		this.typeUser = typeUser;
	}

}
