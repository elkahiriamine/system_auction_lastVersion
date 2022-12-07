package web.model;

public class Product {

	private Long id;
	private String name;
	private double prix;
	private Long code;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	public Product(String name, double prix) {
		this.name = name;
		this.prix = prix;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", prix=" + prix + ", code=" + code + "]";
	}

}
