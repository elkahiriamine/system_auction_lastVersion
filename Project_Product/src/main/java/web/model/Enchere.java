package web.model;

import java.time.LocalDate;
import java.util.Observable;

public class Enchere extends Observable {

	private Long id;
	private Product product;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private String etat;
	private Vendeur vendeur;
	private String proprietaire;
	
	public Enchere() {
		// TODO Auto-generated constructor stub
	}

	public Enchere(Product product, LocalDate dateDebut, LocalDate dateFin, String etat) {
		this.product = product;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.etat = etat;
	}

	public Long getId() {
		return id;
	}

	public Product getProduct() {
		return product;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public String getEtat() {
		return etat;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Vendeur getVendeur() {
		return vendeur;
	}

	public void setVendeur(Vendeur vendeur) {
		this.vendeur = vendeur;
	}

	public String getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}

	@Override
	public String toString() {
		return "Enchere [id=" + id + ", product=" + product + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin
				+ ", etat=" + etat + ", vendeur=" + vendeur + ", proprietaire=" + proprietaire + "]";
	}

}
