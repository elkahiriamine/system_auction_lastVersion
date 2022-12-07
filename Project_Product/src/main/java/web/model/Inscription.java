package web.model;

public class Inscription {

	private Long id_enchere;
	private Long id_user;
	
	public Inscription() {
		// TODO Auto-generated constructor stub
	}

	public Inscription(Long i, Long j) {
		// TODO Auto-generated constructor stub
		this.id_enchere=i;
		this.id_user=j;
	}

	public Long getId_enchere() {
		return id_enchere;
	}

	public void setId_enchere(Long id_enchere) {
		this.id_enchere = id_enchere;
	}

	public Long getId_user() {
		return id_user;
	}

	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}

	@Override
	public String toString() {
		return "Inscription [id_enchere=" + id_enchere + ", id_user=" + id_user + "]";
	}
	
	

}
