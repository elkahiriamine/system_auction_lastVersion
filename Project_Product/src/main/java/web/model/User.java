package web.model;

import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

public class User implements Observer {

	private Long id;
	private String firstName;
	private String lastName;
	private String sexe;
	private String phone;
	private String email;
	private String password;
	
	public User() {
		// TODO Auto-generated constructor stub
	}


	public User(String firstName, String lastName, String sexe, String phone, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.sexe = sexe;
		this.phone = phone;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSexe() {
		return sexe;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", sexe=" + sexe + ", phone="
				+ phone + ", email=" + email + ", password=" + password + "]";
	}


	public User(Long id, String firstName, String lastName, String sexe, String phone, String email, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.sexe = sexe;
		this.phone = phone;
		this.email = email;
		this.password = password;
	}
	
}
